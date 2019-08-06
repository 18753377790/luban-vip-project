package com.luban.feginClientService;

import com.luban.util.ResponseUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 李非凡
 * @Description:
 * 系统管理服务Feign接口
 * @Date 2019/7/23 17:25
 * @Version 1.0
 */
@FeignClient("server-admin")
public interface AdminFeignService {

    /**
     * 获取用户
     * @param username
     * @return
     */
    @RequestMapping("/user/getUser")
    ResponseUtil getUser(@RequestParam("username") String username);

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/user/register")
    ResponseUtil register(@RequestParam("username") String username, @RequestParam("password") String password);
}
