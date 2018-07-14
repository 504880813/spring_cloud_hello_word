package org.baymax.boot.redis;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CusRedisEncoder extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        String [] msgs = msg.split(" ");
        out.writeBytes(("*"+msgs.length+"\r\n").getBytes());
        for (String temp : msgs) {
            out.writeBytes(("$"+temp.length()+"\r\n").getBytes());
            out.writeBytes((temp + "\r\n").getBytes());
        }
    }

}
