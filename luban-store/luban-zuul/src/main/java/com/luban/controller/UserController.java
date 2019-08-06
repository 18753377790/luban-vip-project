package com.luban.controller;

import com.luban.feginClientService.AdminFeignService;
import com.luban.util.CookieUtil;
import com.luban.util.RedisUtil;
import com.luban.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 用户控制器层
 * @Date 2019/7/23 16:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AdminFeignService adminFeignService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public Object login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(username)){
            return ResponseUtil.error("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)){
            return ResponseUtil.error("密码不能为空");
        }
        ResponseUtil user = adminFeignService.getUser(username);
        if (user == null){
            return ResponseUtil.error("用户名不存在");
        }
        Map<String, String> mapUser = (Map<String, String>) user.get(ResponseUtil.DATA_KEY);
        String userPassword = mapUser.get("password");
        if (!password.equals(userPassword)){
            return ResponseUtil.error("密码不正确");
        }
        String string = CookieUtil.setLoginCookie(request, response);
        redisUtil.set(string, mapUser);
//        request.getSession().setAttribute(USER_KEY, map);
        return ResponseUtil.success();
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/register")
    public Object register(String username, String password, HttpServletRequest request, HttpServletResponse response){
        if (StringUtils.isEmpty(username)){
            return ResponseUtil.error("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)){
            return ResponseUtil.error("密码不能为空");
        }
        return adminFeignService.register(username, password);
    }
}
