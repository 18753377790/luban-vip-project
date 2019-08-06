package com.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.modules.product.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 商品持久层接口
 * @Date 2019/7/24 20:25
 * @Version 1.0
 */
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 更新库存信息
     * @param map
     * @return
     */
    int updateStock(Map<String, Object> map);

    /**
     * 查询全部库存
     * @return
     */
    List<Map<String, Object>> selectStockAll();
}
