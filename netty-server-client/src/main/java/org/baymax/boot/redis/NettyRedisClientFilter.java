package org.baymax.boot.redis;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyRedisClientFilter extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        // /*
        // * 解码和编码，应和服务端一致
        // * */
        // ph.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
        // //
        // 配置Protobuf解码处理器，消息接收到了就会自动解码，ProtobufDecoder是netty自带的，Message是自己定义的Protobuf类
        // ph.addLast("protobufDecoder", new
        // ProtobufDecoder(ReqBody.getDefaultInstance()));
        // // 用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
        // ph.addLast("frameEncoder", new
        // ProtobufVarint32LengthFieldPrepender());
        // // 配置Protobuf编码器，发送的消息会先经过编码
        // ph.addLast("protobufEncoder", new ProtobufEncoder());
//        ph.addLast(new LineBasedFrameEn);
        ph.addLast(new CusRedisDecoder());
//        ph.addLast(new StringEncoder());
        ph.addLast(new CusRedisEncoder());
        ph.addLast("handler", new NettyRedisClientHandler()); // 客户端的逻辑
    }
}
