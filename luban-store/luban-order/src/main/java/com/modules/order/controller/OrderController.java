package com.modules.order.controller;

import com.modules.order.entity.Order;
import com.modules.order.feginSerivce.ProductServiceClient;
import com.modules.order.service.IOrderService;
import com.modules.order.util.RedisUtil;
import com.modules.order.util.ResponseUtil;
import com.modules.order.util.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 订单控制器层
 * @Date 2019/7/25 20:56
 * @Version 1.0
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private SendMessageUtil sendMessageUtil;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 下订单（place an order）
     * @param productId
     * @param count
     * @param request
     * @return
     */
    @RequestMapping("/placeOrder")
    public ResponseUtil placeOrder(String productId, int count, HttpServletRequest request){
        String login_key = request.getHeader("login_key");
        if (StringUtils.isEmpty(login_key)){
            return ResponseUtil.error("用户未登录");
        }
        Map<String, Object> user = (Map<String, Object>)redisUtil.get(login_key);
        if (user == null){
            return ResponseUtil.error("用户未登录");
        }

        ResponseUtil result = productServiceClient.getProduct(productId);
        Map<String, Object> map =(Map<String, Object>)result.get(ResponseUtil.DATA_KEY);
        Order order = new Order();
        double nowPrice = (double) map.get("nowPrice");
        order.setAmount(nowPrice*count);
        order.setFee(5D);
        order.setQuantity(count+"");
        order.setCreatedate(new Date());
        order.setAccount((String) user.get("username"));
        return orderService.placeOrder(order, count, productId);
    }

    /**
     * 获取订单信息
     * @param map
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getOrder")
    public ResponseUtil getOrder(@RequestParam Map<String,Object> map, int limit, int page){
        return orderService.getOrder(map, limit, page);
    }

    /**
     * 测试发送消息
     * @return
     */
    @RequestMapping("/test")
    public ResponseUtil testSend(){
        sendMessageUtil.send("test", "stock");
        return ResponseUtil.success("success");
    }
}
