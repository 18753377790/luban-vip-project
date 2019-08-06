package com.modules.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 李非凡
 * @Description:
 * 权限注解
 * 目标对象 方法级别
 * 保留期 运行时
 * @Date 2019/7/30 12:01
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Power {

}
