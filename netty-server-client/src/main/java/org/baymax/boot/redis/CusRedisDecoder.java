package org.baymax.boot.redis;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class CusRedisDecoder extends ByteToMessageDecoder {
    private static final byte DOLLAR_BYTE   = '$';
    private static final byte ASTERISK_BYTE = '*';
    private static final byte PLUS_BYTE     = '+';
    private static final byte MINUS_BYTE    = '-';
    private static final byte COLON_BYTE    = ':';



    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        byte b = in.readByte();
//        if (b == MINUS_BYTE) {
//            parseError(in);
//        } else if (b == ASTERISK_BYTE) {
//            return parseMultiBulkReply(in);
//        } else if (b == COLON_BYTE) {
//            return parseInteger(in);
//        } else if (b == DOLLAR_BYTE) {
//            return parseBulkReply(in);
//        } else if (b == PLUS_BYTE) {
//            return parseStatusCodeReply(in);
//        } else {
//            throw new Exception("Unknown reply: " + (char) b);
//        }
//        //byte 数组解码  TODO 
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



    private static String readLine(ByteBuf byteBuf) {
        int b;
        byte c;
        StringBuilder sb = new StringBuilder();

        while (true) {
            b = byteBuf.readByte();
            if (b == '\r') {

                c = byteBuf.readByte();
                if (c == '\n') {
                    break;
                }
                sb.append((char) b);
                sb.append((char) c);
            } else {
                sb.append((char) b);
            }
        }
        String reply = sb.toString();
        if (reply.length() == 0) {
//            throw new Exception("It seems like server has closed the connection.");
        }
        return reply;
    }

}
