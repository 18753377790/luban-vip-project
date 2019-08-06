package com.modules.product.listener;

import com.alibaba.fastjson.JSON;
import com.modules.product.service.IProductService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 库存监听器
 * @Date 2019/7/24 11:50
 * @Version 1.0
 */
@Component
public class StockListener {

    @Autowired
    private IProductService productService;

    /**
     * 获取库存信息
     * @param message
     * @param channel
     */
    @RabbitListener(queues = "stock_queue", containerFactory = "simpleRabbitListenerContainerFactory")
    public void getStockMessage(Message message, Channel channel) throws Exception {
        System.out.println("接受到了消息" + new String(message.getBody(),"UTF-8"));
        Map<String, Object> map = JSON.parseObject(new String(message.getBody(),"UTF-8"), Map.class);
        map.put("stock", map.get(""));
        map.put("", map.get("id"));
        try {
            productService.updateStock(map);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            e.printStackTrace();
        }
    }
}
