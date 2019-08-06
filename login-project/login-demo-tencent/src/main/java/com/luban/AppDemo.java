package com.luban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author 李非凡
 * @Description:
 * 登录演示启动类
 * 模拟QQ服务器，对应端口5000
 * @Date 2019/7/26 10:05
 * @Version 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AppDemo {

    public static void main(String[] args) {
        SpringApplication.run(AppDemo.class);
    }
}
