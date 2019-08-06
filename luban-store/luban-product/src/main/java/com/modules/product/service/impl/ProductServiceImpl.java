package com.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.modules.product.entity.Product;
import com.modules.product.mapper.ProductMapper;
import com.modules.product.service.IProductService;
import com.modules.product.util.ResponseUtil;
import com.systemConst.CacheConst;
import com.template.CacheLoadable;
import com.template.CacheTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 * 商品业务层实现类
 * @Date 2019/7/24 20:48
 * @Version 1.0
 */
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ValueOperations valueOperations;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheTemplate cacheTemplate;

    @Override
    public ResponseUtil getProduct(Map<String, Object> map ,int page ,int limit) {
        Object product = valueOperations.get("product");
        Page<Product> productPage = new Page(page, limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        map.remove("limit");
        map.remove("page");
        queryWrapper.allEq(map,false);
        IPage iPage = productMapper.selectPage(productPage, queryWrapper);
        return ResponseUtil.success("success",iPage.getRecords()).set("count",iPage.getTotal());
//        return ResponseUtil.success("success", productMapper.selectByMap(map));
    }

    @Override
    public ResponseUtil getProductById(String id) {
        System.out.println("" + CacheConst.PRODUCT_CACHE_KEY + id);
        return cacheTemplate.findCache(id, 5, TimeUnit.MINUTES, new CacheLoadable() {
            @Override
            public Object load() {
                return productMapper.selectById(id);
            }
        });
    }

    @Override
    public ResponseUtil getAdminProduct(Map<String, Object> map, int page, int limit) {
        Page<Product> productPage = new Page(page,limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        map.remove("limit");
        map.remove("page");
        queryWrapper.allEq(map,false);
        IPage iPage = productMapper.selectPage(productPage, queryWrapper);
        return ResponseUtil.success("success",iPage.getRecords()).set("count",iPage.getTotal());
    }

    @Override
    public ResponseUtil updateStock(Map<String, Object> map) throws Exception {
        Object redisObj = valueOperations.get(CacheConst.STOCK_CACHE_KEY + String.valueOf(map.get("productId")));
        if (redisObj != null){
            if (Integer.valueOf(String.valueOf(redisObj)) >= Integer.valueOf(String.valueOf(map.get("stock")))){
                System.out.println("删除的key" + CacheConst.PRODUCT_CACHE_KEY);
                redisTemplate.delete(CacheConst.PRODUCT_CACHE_KEY + String.valueOf(map.get("productId")));
                int i = productMapper.updateStock(map);
                if (i == 0){
                    throw new Exception("库存不足");
                }
//                valueOperations.increment(CacheConst.STOCK_CACHE_KEY+String.valueOf(map.get("productId")), Integer.valueOf(String.valueOf(map.get("stock"))));
                redisTemplate.execute(new RedisCallback() {
                    @Override
                    public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        redisConnection.decrBy((CacheConst.STOCK_CACHE_KEY + String.valueOf(map.get("productId"))).getBytes(),Integer.valueOf(String.valueOf(map.get("stock"))));
                        return null;
                    }
                });
                System.out.println("改了库存 ID:" + map.get("productId"));
            }else {
                return ResponseUtil.error("秒杀失败");
            }
        }else {
            throw new Exception("无预加载数据");
        }
        return ResponseUtil.success("success");
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushAll();
                return null;
            }
        });
        List<Map<String, Object>> maps = productMapper.selectStockAll();
        for (Map<String, Object> map: maps) {
            valueOperations.set(CacheConst.STOCK_CACHE_KEY + map.get("id"), map.get("stock"));
        }
    }
}
