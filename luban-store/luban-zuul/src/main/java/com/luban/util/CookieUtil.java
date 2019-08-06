package com.luban.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author 李非凡
 * @Description:
 * Cookie工具类
 * @Date 2019/7/23 18:03
 * @Version 1.0
 */
public class CookieUtil {

    /**
     * 登录Cookie
     */
    public static final String COOKIE_KEY = "LOGIN_COOKIE";

    /**
     * 获取登录Cookie
     * @param request
     * @return
     */
    public static String getLoginCookie(HttpServletRequest request){
        if (request.getCookies() != null){
            for (Cookie cookie : request.getCookies()) {
                String name = cookie.getName();
                if (COOKIE_KEY.equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 设置登录Cookie
     * @param request
     * @param response
     * @return
     */
    public static String setLoginCookie(HttpServletRequest request, HttpServletResponse response){
        String uuid = UUID.randomUUID().toString();
        Cookie cookie = new Cookie(COOKIE_KEY, uuid);
//        cookie.setHttpOnly(true);
        cookie.setPath("/");
//        cookie.setDomain("www.lll.com");
        response.addCookie(cookie);
        return uuid;
    }
}
