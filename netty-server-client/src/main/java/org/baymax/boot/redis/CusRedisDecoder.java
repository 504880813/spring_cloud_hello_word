package org.baymax.boot.redis;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class CusRedisDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //byte 数组解码  TODO 
        byte[] byteArray = new byte[in.readableBytes()];
        in.readBytes(byteArray);
        String result = new String(byteArray);
        System.out.println(result);
        String[] results = result.split("\r\n");
        for (int i = 0; i < results.length; i++) {
            String temp = results[i];
            if (temp.startsWith("+")) {
                temp = temp.substring(1, temp.length());
                out.add(temp);
            }
            if (temp.startsWith("$")) {
                int nextLen = Integer.valueOf(temp.substring(1, temp.length()));
                if (nextLen <= 0) {
                    out.add("null");
                } else {
                    if (results[i + 1].length() == nextLen) {
                        out.add(results[i + 1]);
                    }
                    i = i + 1;
                }

            }

        }
    }

}
