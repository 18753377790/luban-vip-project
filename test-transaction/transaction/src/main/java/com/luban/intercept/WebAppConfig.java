package com.luban.intercept;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author 李非凡
 * @Description:
 * web设置
 * @Date 2019/7/29 13:51
 * @Version 1.0
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    }
}
