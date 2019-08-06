package com.luban.intercept;

import com.luban.transaction.TransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 李非凡
 * @Description:
 * 请求拦截器
 * @Date 2019/7/29 13:49
 * @Version 1.0
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String groupId = request.getHeader("groupId");
        TransactionManager.setCurrent(groupId);
        return true;
    }
}
