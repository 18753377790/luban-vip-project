package com.modules.order.feginSerivce;

import com.modules.order.util.ResponseUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 李非凡
 * @Description:
 * 商品服务客户端
 * @Date 2019/7/25 20:42
 * @Version 1.0
 */
@FeignClient("server-product")
public interface ProductServiceClient {

    /**
     * 获取单个商品信息
     * @param id
     * @return
     */
    @RequestMapping("/getProductById")
    ResponseUtil getProduct(@RequestParam("id") String id);

    /**
     * 更新库存信息
     * @param productId
     * @param stock
     * @return
     */
    @RequestMapping("updateStock")
    ResponseUtil updateInventory(@RequestParam("productId") String productId,@RequestParam("stock") int stock);
}
