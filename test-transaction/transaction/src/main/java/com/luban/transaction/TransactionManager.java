package com.luban.transaction;

import com.alibaba.fastjson.JSONObject;
import com.luban.netty.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author 李非凡
 * @Description:
 * 事务管理者
 * @Date 2019/7/28 1:09
 * @Version 1.0
 */
@Component
public class TransactionManager {

    /**
     * 存储事务组和事务的键值对，事务组就是一个Map
     */
    private static final Map<String, Map<String, Transaction>> GROUP_MAP = new HashMap<>();

    /**
     * 本地的线程局部变量
     */
    private static ThreadLocal<String> current = new ThreadLocal<>();

    /**
     * 本地事务对象的线程局部变量
     */
    private static ThreadLocal<Transaction> currentTransaction = new ThreadLocal<>();

    /**
     * Netty客户端
     */
    private static NettyClient nettyClient;

    /**
     *
     * @param nettyClient
     */
    @Autowired
    public void setNettyClient(NettyClient nettyClient){
        this.nettyClient = nettyClient;
    }

    /**
     * 创建事务组
     * @return
     */
    public static String createGroup(){
        String id = UUID.randomUUID().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupId", id);
        jsonObject.put("command", "create");
        // 发送给Netty
        current.set(id);
        GROUP_MAP.put(id, new HashMap<>());
        nettyClient.send(jsonObject);
        return id;
    }

    /**
     * 创建事务对象
     * @param groupId
     * @return
     */
    public static Transaction createTransaction(String groupId){
        String uuid = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(uuid, groupId);
        if (GROUP_MAP.get(groupId) == null){
            GROUP_MAP.put(groupId, new HashMap<>());
        }
        currentTransaction.set(transaction);
        GROUP_MAP.get(groupId).put(uuid, transaction);
        return transaction;
    }

    /**
     * 提交本地事务
     * @param transaction
     * @param isEnd
     * @param transactionType
     */
    public static void commitTransaction(Transaction transaction, TransactionType transactionType, boolean isEnd){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupId", transaction.getGroupId());
        jsonObject.put("transactionId", transaction.getTransactionId());
        jsonObject.put("transactionType", transactionType);
        jsonObject.put("command", "add");
        jsonObject.put("isEnd", isEnd);
        nettyClient.send(jsonObject);
        System.out.println("执行了添加事务");
    }

    /**
     * 通过事务id获取事务对象
     * @param groupId
     * @param transactionId
     * @return
     */
    public static Transaction getTransactionById(String groupId, String transactionId){
        return GROUP_MAP.get(groupId).get(transactionId);
    }

    /**
     * 获取当前本地事务对象的线程局部变量
     * @return
     */
    public static Transaction getCurrentTransaction(){
        return currentTransaction.get();
    }

    /**
     * 获取本地的线程局部变量
     * @return
     */
    public static String getCurrent(){
        return current.get();
    }

    /**
     * 设置本地的线程局部变量
     * @param groupId
     */
    public static void setCurrent(String groupId){
        current.set(groupId);
    }
}
