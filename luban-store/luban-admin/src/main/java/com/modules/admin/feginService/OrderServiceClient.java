package com.modules.admin.feginService;

import com.modules.admin.util.ResponseUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 李非凡
 * @Description:
 * 订单服务客户端
 * @Date 2019/7/25 18:14
 * @Version 1.0
 */
@FeignClient("server-order")
public interface OrderServiceClient {

    /**
     * 获取订单信息
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getOrder")
    ResponseUtil getOrder(@RequestParam("page") Object page, @RequestParam("limit") Object limit);
}
