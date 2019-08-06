package com.luban;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * Netty服务端处理器
 * ChannelInboundHandlerAdapter是ChannelInboundHandler的一个简单实现，默认情况下不会做任
 * 何处理，只是简单的将操作通过fire*方法传递到ChannelPipeline中的下一个ChannelHandler中让链
 * 中的下一个ChannelHandler去处理。
 * @Date 2019/7/27 20:03
 * @Version 1.0
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     *
     */
    private static Map<String, List<JSONObject>> transactionTypeMap = new HashMap<>();

    /**
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    /**
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    /**
     *
     * @param result
     */
    private void sendResult(List<JSONObject> result){

    }
}
