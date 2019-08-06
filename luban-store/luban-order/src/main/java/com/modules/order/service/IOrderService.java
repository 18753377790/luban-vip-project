package com.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.modules.order.entity.Order;
import com.modules.order.util.ResponseUtil;

import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 订单业务层接口
 * @Date 2019/7/25 20:52
 * @Version 1.0
 */
public interface IOrderService extends IService<Order> {

    /**
     * 下订单（place an order）
     * @param order
     * @param count
     * @param productId
     * @return
     */
    ResponseUtil placeOrder(Order order, int count, String productId);

    /**
     * 获取订单信息
     * @param map
     * @param limit
     * @param page
     * @return
     */
    ResponseUtil getOrder(Map<String, Object> map, int limit, int page);
}
