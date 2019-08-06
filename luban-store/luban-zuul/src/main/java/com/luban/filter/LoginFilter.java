package com.luban.filter;

import com.luban.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 李非凡
 * @Description:
 * 登录过滤器（前置过滤器）
 * @Date 2019/7/23 17:55
 * @Version 1.0
 */
@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;// 前置过滤器
    }

    /**
     * 过滤器优先级
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;// 优先级6
    }

    /**
     * 过滤器是否开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的启动方法，具体业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 被代理到的微服务
        String proxy = (String) ctx.get("proxy");
        // 请求的地址
        String requestURI = (String) ctx.get("requestURI");
        // zuul路由后的url
        System.out.println(proxy + "/" + requestURI);
        HttpServletRequest request = ctx.getRequest();
        String loginCookie = CookieUtil.getLoginCookie(request);
        ctx.addZuulRequestHeader("login_key", loginCookie);
        return null;
    }
}
