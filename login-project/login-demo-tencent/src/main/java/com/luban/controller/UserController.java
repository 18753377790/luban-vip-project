package com.luban.controller;

import com.luban.util.CookieUtil;
import com.luban.util.ResponseUtil;
import com.luban.util.StoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 用户控制器层，这是QQ的控制器层
 * @Date 2019/7/27 11:06
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private StoreUtil storeUtil;

    /**
     *
     */
    private static final String USER_KEY="user_key";

    /**
     * 登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public Object login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        String string = CookieUtil.setLoginCookie(request, response);
        storeUtil.set(string, map);
        return ResponseUtil.success();
    }

    /**
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping
    public Object login1(String username, String password, HttpServletRequest request, HttpServletResponse response){
        return null;
    }

    /**
     * 获取用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getUser")
    public Object getUser(HttpServletRequest request, HttpServletResponse response){
        Object user = request.getSession().getAttribute(USER_KEY);
        Object object = storeUtil.get(CookieUtil.getLoginCookie(request, response));
        return ResponseUtil.success();
    }

    /**
     *
     * @param request
     * @param response
     * @param token
     * @return
     */
    @RequestMapping
    public Object getLoginInfo(HttpServletRequest request, HttpServletResponse response, String token){
        return null;
    }
}
