package com.luban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 李非凡
 * @Description:
 * zuul服务端
 * @Date 2019/7/23 16:32
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableZuulProxy
public class AppZuul {
    public static void main(String[] args) {
        SpringApplication.run(AppZuul.class);
    }
}
