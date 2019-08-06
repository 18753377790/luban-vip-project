package com.luban.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luban.transaction.Transaction;
import com.luban.transaction.TransactionManager;
import com.luban.transaction.TransactionType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author 李非凡
 * @Description:
 * Netty客户端处理器
 * ChannelInboundHandlerAdapter是ChannelInboundHandler的一个简单实现，默认情况下不会做任
 * 何处理，只是简单的将操作通过fire*方法传递到ChannelPipeline中的下一个ChannelHandler中让链
 * 中的下一个ChannelHandler去处理。
 * @Date 2019/7/28 1:07
 * @Version 1.0
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道处理器上下文
     */
    private ChannelHandlerContext context;

    /**
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    /**
     *
     * 需要注意的是信息经过channelRead方法处理之后不会自动释放（因为信息不会被自动释放所以能将
     * 消息传递给下一个ChannelHandler处理）。
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("" + msg.toString());
        JSONObject jsonObject = JSON.parseObject((String) msg);

        String groupId = jsonObject.getString("");
        String command = jsonObject.getString("");
        String transactionId = jsonObject.getString("");
        // 拿到通知的事务对象
        Transaction transaction= TransactionManager.getTransactionById(groupId, transactionId);
        System.out.println("");
        if (transaction != null){
           if ("rollback".equals(command)){
               transaction.setTransactionType(TransactionType.ROLLBACK);
           }else {
               transaction.setTransactionType(TransactionType.COMMIT);
           }
           transaction.getTask().signalTask();
        }
        // 对事务进行操作
        // 给指定事务放行

    }

    /**
     * 通知
     * @param data
     * @return
     * @throws Exception
     */
    public synchronized Object call(JSONObject data) throws Exception {
        context.writeAndFlush(data.toJSONString()).channel().newPromise();
        return null;
    }
}
