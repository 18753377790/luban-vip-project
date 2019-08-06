package com.luban.task;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 李非凡
 * @Description:
 * 任务类
 * @Date 2019/7/28 8:58
 * @Version 1.0
 */
public class Task {

    /**
     *
     */
    private Lock lock = new ReentrantLock();

    /**
     *
     */
    private Condition condition = lock.newCondition();

    /**
     * 等待任务
     */
    public void waitTask(){
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * （发信号，用信号通知）唤醒任务
     */
    public void signalTask(){
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
