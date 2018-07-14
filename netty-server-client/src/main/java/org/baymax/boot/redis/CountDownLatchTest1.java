package org.baymax.boot.redis;

import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchTest1 implements Runnable {
    final AtomicInteger number = new AtomicInteger();
    volatile boolean    bol    = false;



    @Override
    public void run() {
        number.getAndIncrement();
        try {
//            if (!bol) {
//                System.out.println(bol);
//                bol = true;
//                Thread.sleep(10000);
//            }
            byte[] bytes = CustomJedisUtil.rpop(TestRedisQueue.redisKey);
            while (bytes != null) {
                bytes = CustomJedisUtil.rpop(TestRedisQueue.redisKey);
                Message msg = (Message) ObjectUtil.bytes2Object(bytes);
                if (msg != null) {
                    if(TestRedisQueue.ids.contains(Integer.valueOf(msg.getId()))){
                        throw new Exception("并发异常--->id=="+msg.getId());
                    }
                    TestRedisQueue.ids.add(Integer.valueOf(msg.getId()));
                    System.out.println(Thread.currentThread().getName() +"---"+msg.getId() + "----" + msg.getContent());
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
