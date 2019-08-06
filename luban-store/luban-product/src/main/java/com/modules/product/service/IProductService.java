package com.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.modules.product.entity.Product;
import com.modules.product.util.ResponseUtil;

import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 商品业务层接口
 * @Date 2019/7/24 20:33
 * @Version 1.0
 */
public interface IProductService extends IService<Product> {

    /**
     * 获取全部商品信息
     * @param map
     * @return
     */
    ResponseUtil getProduct(Map<String, Object> map, int page, int limit);

    /**
     * 通过商品唯一id获取单个商品信息
     * @param id
     * @return
     */
    ResponseUtil getProductById(String id);

    /**
     * 获取商品管理
     * @param map
     * @param page
     * @param limit
     * @return
     */
    ResponseUtil getAdminProduct(Map<String, Object> map, int page, int limit);

    /**
     * 更新库存信息
     * @param map
     * @return
     * @throws Exception
     */
    ResponseUtil updateStock(Map<String, Object> map) throws Exception;
}
