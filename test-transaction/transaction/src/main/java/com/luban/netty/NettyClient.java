package com.luban.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 李非凡
 * @Description:
 * Netty客户端
 * @Date 2019/7/28 1:07
 * @Version 1.0
 */
@Component
public class NettyClient implements InitializingBean {

    /**
     * Netty客户端处理器
     */
    public NettyClientHandler client = null;

    /**
     * 缓存线程池
     */
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        start("localhost", 8080);
    }

    /**
     * 开启Netty服务
     * @param hostName
     * @param port
     */
    public void start(String hostName, Integer port){
        client = new NettyClientHandler();

        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringEncoder());
                        pipeline.addLast("handler", client);
                    }
                });
        try {
            bootstrap.connect(hostName,port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送通知
     * @param jsonObject
     */
    public void send(JSONObject jsonObject){
        try {
            client.call(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
