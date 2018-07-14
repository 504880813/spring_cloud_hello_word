package org.baymax.boot.redis;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyRedisClient {

    private Channel channel = null;



    /**
     * Netty创建全部都是实现自AbstractBootstrap。 客户端的是Bootstrap，服务端的则是 ServerBootstrap。
     **/
    public NettyRedisClient(String host, int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.handler(new NettyRedisClientFilter());
        // 连接服务端
        try {
            channel = b.connect(host, port).sync().channel();
            channel.writeAndFlush("ping");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public Channel getChannel() {
        return channel;
    }



    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    // public static void star() throws IOException{
    // ch.writeAndFlush("ping");
    // ch.writeAndFlush("set hello helloword123");
    // ch.writeAndFlush("get hello");
    //// ch.writeAndFlush("del hello");
    //// ch.writeAndFlush("get hello");
    //// for (int i = 0; i < 10; i++) {
    //// ch.writeAndFlush("LPUSH hello1 "+i);
    //// }
    //// for (int i = 0; i < 10; i++) {
    //// ch.writeAndFlush("RPOP hello1");
    //// }
    // }

}