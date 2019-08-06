package com.luban.transaction;

import com.luban.task.Task;

/**
 * @Author 李非凡
 * @Description:
 * 事务对象实体类
 * @Date 2019/7/28 1:08
 * @Version 1.0
 */
public class Transaction {

    // 事务id
    private String transactionId;
    // 事务组id
    private String groupId;
    // 当前的事务状态 commit or rollback
    private TransactionType transactionType;
    // 事务任务类
    private Task task;

    public Transaction(String transactionId, String groupId) {
        this.transactionId = transactionId;
        this.groupId = groupId;
        this.task = new Task();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
