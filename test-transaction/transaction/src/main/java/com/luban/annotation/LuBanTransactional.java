package com.luban.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 李非凡
 * @Description:
 * 分布式事务开启注解
 * 作用域，方法级别
 * 生命周期，运行时
 *
 * 从Spring手中拿到本地事务的控制权
 * @Date 2019/7/27 21:49
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LuBanTransactional {

    /**
     * 事务是否开始
     * @return
     */
    boolean isStart() default false;

    /**
     * 事务是否结束
     * @return
     */
    boolean isEnd() default false;
}
