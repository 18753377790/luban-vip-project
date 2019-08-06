package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 李非凡
 * @Description:
 * 系统管理启动类
 * @Date 2019/7/25 10:26
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AppAdmin {

    public static void main(String[] args) {
        SpringApplication.run(AppAdmin.class);
    }
}
