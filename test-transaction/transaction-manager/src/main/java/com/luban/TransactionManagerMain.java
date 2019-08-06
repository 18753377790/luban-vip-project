package com.luban;

/**
 * @Author 李非凡
 * @Description:
 * 事务管理者
 * 作为事务管理者，它需要：
 * 1. 创建并保存事务组
 * 2. 保存各个子事务在对应的事务组内
 * 3. 统计并判断事务组内的各个子事务状态，以算出当前事务组的状态（提交or回滚）
 * 4. 通知各个子事务提交或回滚
 *
 * 事务管理者通知微服务是提交事务还是回滚事务
 * 两个或者多个微服务，只要有一个不是提交状态，就回滚
 *
 * 分布式事务的处理方式
 * 2PC：二阶段提交，第一阶段准备提交阶段，第二阶段确认提交阶段。
 * MQ：利用消息中间件处理分布式事务
 * TCC：最好的解决方案，但是入侵性强，代码耦合度高，业务复杂，适合大型分布式交易系统，例如支付宝。
 *
 * 拿到连接
 * 开启事务
 * 执行业务逻辑
 * 提交/回滚
 * 关闭
 * @Date 2019/7/28 14:27
 * @Version 1.0
 */
public class TransactionManagerMain {

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start("localhost", 8080);
        System.out.println("Netty启动成功！");
    }
}
