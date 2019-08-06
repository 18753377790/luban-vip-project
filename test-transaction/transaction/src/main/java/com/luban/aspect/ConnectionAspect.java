package com.luban.aspect;

import com.luban.connection.LuBanConnection;
import com.luban.transaction.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * @Author 李非凡
 * @Description:
 * 连接的切面
 * @Date 2019/7/28 1:06
 * @Version 1.0
 */
@Aspect
@Component
public class ConnectionAspect {

    /**
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Connection around(ProceedingJoinPoint proceedingJoinPoint){
        try {
//            return (Connection) proceedingJoinPoint.proceed();
            return new LuBanConnection((Connection) proceedingJoinPoint.proceed(), TransactionManager.getCurrentTransaction());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
