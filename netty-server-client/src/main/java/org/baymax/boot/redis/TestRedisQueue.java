package org.baymax.boot.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRedisQueue {
    public static byte[] redisKey = "key".getBytes();
    public static Set<Integer> ids = new HashSet<Integer>();
    static {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init() throws IOException {
        System.out.println("--开始初始化-");
        for (int i = 0; i < 10; i++) {
            Message message = new Message(i, "这是第" + i + "个内容");
            CustomJedisUtil.lpush(redisKey, ObjectUtil.object2Bytes(message));
        }
        System.out.println("--结束初始化-");
    }

    public static void main(String[] args) {
        try {
            ExecutorService pool = Executors.newCachedThreadPool();
            CountDownLatchTest1 test = new CountDownLatchTest1();
            for (int i = 0; i < 30; i++) {
                pool.execute(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
