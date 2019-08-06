package com.luban.controller;

import com.luban.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 李非凡
 * @Description:
 * 服务二控制器层
 * @Date 2019/7/28 0:41
 * @Version 1.0
 */
@RestController
@RequestMapping("server2")
public class ServerController {

    @Autowired
    private ServerService serverService;

    @RequestMapping("test")
    public void test(){
        serverService.test();
    }
}
