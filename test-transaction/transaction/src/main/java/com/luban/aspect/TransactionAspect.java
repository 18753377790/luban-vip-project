package com.luban.aspect;


import com.luban.annotation.LuBanTransactional;
import com.luban.transaction.Transaction;
import com.luban.transaction.TransactionManager;
import com.luban.transaction.TransactionType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author 李非凡
 * @Description:
 * 事务的切面
 * 最先被执行
 * @Date 2019/7/28 1:06
 * @Version 1.0
 */
@Order(10000)
@Aspect
@Component
public class TransactionAspect {

    /**
     * 调用
     * @param proceedingJoinPoint
     */
    @Around("@annotation(com.luban.annotation.LuBanTransactional)")
    public void invoke(ProceedingJoinPoint proceedingJoinPoint){
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        LuBanTransactional annotation = signature.getMethod().getAnnotation(LuBanTransactional.class);
        String group = "";
        if (annotation.isStart()) {
            // 创建事务组
            group = TransactionManager.createGroup();
        }else {
            // 拿到当前事务组的ID
            group = TransactionManager.getCurrent();
        }
        // 创建事务对象
        Transaction transaction = TransactionManager.createTransaction(group);
        // 执行本地逻辑
        try {
            // Spring会帮我们执行MySQL的事务，一直等待
            Object proceed = proceedingJoinPoint.proceed();
            // 提交本地事务状态 commit
            TransactionManager.commitTransaction(transaction, TransactionType.COMMIT, annotation.isEnd());
        } catch (Throwable throwable) {
            // 事务回滚
            TransactionManager.commitTransaction(transaction, TransactionType.ROLLBACK, annotation.isEnd());
            throwable.printStackTrace();
        }
    }
}
