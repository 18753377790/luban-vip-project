package com.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.modules.order.entity.Order;
import com.modules.order.feginSerivce.ProductServiceClient;
import com.modules.order.mapper.OrderMapper;
import com.modules.order.service.IOrderService;
import com.modules.order.util.ResponseUtil;
import com.modules.order.util.SendMessageUtil;
import com.systemConst.CacheConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 订单业务层实现类
 * @Date 2019/7/25 20:54
 * @Version 1.0
 */
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SendMessageUtil sendMessageUtil;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private ValueOperations valueOperations;

    @Override
    public ResponseUtil placeOrder(Order order, int count, String productId) {
        Object redisObj = valueOperations.get(CacheConst.STOCK_CACHE_KEY + productId);
        if (Integer.valueOf(String.valueOf(redisObj)) >= count){
            orderMapper.insert(order);
            // 发送消息
            order.setId(Integer.parseInt(productId));
            sendMessageUtil.placeOrderMessage(order);
            return ResponseUtil.success("下单成功");
        }
        return ResponseUtil.error("库存不足");
    }

    @Override
    public ResponseUtil getOrder(Map<String, Object> map, int limit, int page) {
        Page<Order> pageObj = new Page<>(page, limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        map.remove("limit");
        map.remove("page");
        //
        queryWrapper.allEq(map,false);
        queryWrapper.orderByDesc("id");
        IPage<Order> orderIPage = orderMapper.selectPage(pageObj,queryWrapper);
        return ResponseUtil.success("success", orderIPage.getRecords()).set("count", orderIPage.getTotal());
    }
}
