package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author 李非凡
 * @Description:
 * 商品管理启动类
 * @Date 2019/7/24 10:03
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
public class AppProduct {
    public static void main(String[] args) {
        SpringApplication.run(AppProduct.class);
    }
}
