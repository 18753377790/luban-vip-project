package com.modules.order.util;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 李非凡
 * @Description:
 * 发送消息工具类
 * @Date 2019/7/25 20:40
 * @Version 1.0
 */
@Component
public class SendMessageUtil {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 下订单消息
     * @param order
     */
    public void placeOrderMessage(Object order){
        String s = JSON.toJSONString(order);
        send(s,"stock");
    }

    /**
     * 发送消息
     * @param message
     * @param routingKey
     */
    public void send(Object message,String routingKey){
        rabbitTemplate.convertAndSend("direct.exchange",routingKey,message);
    }
}
