package com.luban.controller;

import com.luban.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 李非凡
 * @Description:
 * 服务一控制器层
 * @Date 2019/7/27 21:28
 * @Version 1.0
 */
@RestController
@RequestMapping("server1")
public class ServerController {

    @Autowired
    private ServerService serverService;

    /**
     * 测试方法
     */
    @RequestMapping("test")
    public void test(){
        serverService.test();
    }
}
