package com.modules.product.controller;

import com.modules.product.service.IProductService;
import com.modules.product.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 商品控制器层
 * @Date 2019/7/24 20:52
 * @Version 1.0
 */
@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 获取全部商品信息
     * @param map
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getProduct")
    public ResponseUtil getProduct(Map<String, Object> map, int page ,int limit){
        return productService.getProduct(map, page, limit);
    }

    /**
     * 通过商品唯一id获取单个商品信息
     * @param id
     * @return
     */
    @RequestMapping("/getProductById")
    public ResponseUtil getProductById(String id){
        return productService.getProductById(id);
    }

    /**
     * 获取商品管理
     * @param map
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getAdminProduct")
    public ResponseUtil getAdminProduct(Map<String, Object> map, int page ,int limit){
        return productService.getAdminProduct(map, page, limit);
    }

    /**
     * 更新库存信息
     * @param map
     * @return
     */
    @RequestMapping("/updateStock")
    public ResponseUtil updateStock(Map<String, Object> map) throws Exception {
        return productService.updateStock(map);
    }
}
