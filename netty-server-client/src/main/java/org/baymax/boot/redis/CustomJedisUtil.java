package org.baymax.boot.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class CustomJedisUtil {
    // private static JedisPool jedisPool;
    // static {
    // JedisPoolConfig config = new JedisPoolConfig();
    // // config.setMaxActive(5000);
    // config.setMaxIdle(256);
    // // config.setMaxWait(5000L);
    // config.setTestOnBorrow(true);
    // config.setTestOnReturn(true);
    // config.setTestWhileIdle(true);
    // config.setMinEvictableIdleTimeMillis(60000L);
    // config.setTimeBetweenEvictionRunsMillis(3000L);
    // config.setNumTestsPerEvictionRun(-1);
    // jedisPool = new JedisPool(config, "127.0.0.1", 6379, 60000);
    // }

    private static NettyRedisClient nettyRedisClient;
    static {
        nettyRedisClient = new NettyRedisClient("127.0.0.1", 6379);
    }



    /**
     * 获取数据
     * 
     * @param key
     * @return
     */
//    public static String get(String key) {
//        String value = nettyRedisClient.
//        return value;
//    }



//    private static void close(Jedis jedis) {
//        try {
//            jedisPool.returnResource(jedis);
//        } catch (Exception e) {
//            if (jedis.isConnected()) {
//                jedis.quit();
//                jedis.disconnect();
//            }
//        }
//    }



//    public static void set(byte[] key, byte[] value) {
//
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.set(key, value);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//    }

//    public static void set(byte[] key, byte[] value, int time) {
//
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.set(key, value);
//            jedis.expire(key, time);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//    }


//
//    public static void hset(byte[] key, byte[] field, byte[] value) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.hset(key, field, value);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//    }
//
//
//
//    public static void hset(String key, String field, String value) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.hset(key, field, value);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//    }
//
//
//
//    /**
//     * 获取数据
//     *
//     * @param key
//     * @return
//     */
//    public static String hget(String key, String field) {
//
//        String value = null;
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            value = jedis.hget(key, field);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//
//        return value;
//    }
//
//
//
//    /**
//     * 获取数据
//     *
//     * @param key
//     * @return
//     */
//    public static byte[] hget(byte[] key, byte[] field) {
//
//        byte[] value = null;
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            value = jedis.hget(key, field);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//
//        return value;
//    }
//
//
//
//    public static void hdel(byte[] key, byte[] field) {
//
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.hdel(key, field);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//    }



    /**
     * 存储REDIS队列 顺序存储
     * 
     * @param key
     *            reids键名
     * @param value
     *            键值
     */
    public static void lpush(byte[] key, byte[] value) {
        String cmd = "LPUSH " + key.toString() + " "+value.toString();
        nettyRedisClient.getChannel().writeAndFlush(cmd);
    }



    /**
     * 存储REDIS队列 反向存储
     * 
     * @param key
     *            reids键名
     * @param value
     *            键值
    public static void rpush(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            jedis.rpush(key, value);

        } catch (Exception e) {

            // 释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {

            // 返还到连接池
            close(jedis);

        }
    }
*/


    /**
     * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
     * 
     * @param key
     *            reids键名
     * @param destination
     *            键值
    public static void rpoplpush(byte[] key, byte[] destination) {

        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            jedis.rpoplpush(key, destination);

        } catch (Exception e) {

            // 释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {

            // 返还到连接池
            close(jedis);

        }
    }

*/

    /**
     * 获取队列数据
     * 
     * @param key
     *            键名
     * @return
    public static List lpopList(byte[] key) {

        List list = null;
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            list = jedis.lrange(key, 0, -1);

        } catch (Exception e) {

            // 释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {

            // 返还到连接池
            close(jedis);

        }
        return list;
    }
*/

    /**
     * 获取队列数据
     * 
     * @param key
     *            键名
     * @return
     */
    public static byte[] rpop(byte[] key) {
        String cmd = "RPOP " + key.toString();
        nettyRedisClient.getChannel().writeAndFlush(cmd).addListener(new GenericFutureListener<Future<? super Void>>() {

            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
            }
        });
        return null;
    }



//    public static void hmset(Object key, Map hash) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.hmset(key.toString(), hash);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//
//        } finally {
//            // 返还到连接池
//            close(jedis);
//
//        }
//    }
//
//
//
//    public static void hmset(Object key, Map hash, int time) {
//        Jedis jedis = null;
//        try {
//
//            jedis = jedisPool.getResource();
//            jedis.hmset(key.toString(), hash);
//            jedis.expire(key.toString(), time);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//
//        } finally {
//            // 返还到连接池
//            close(jedis);
//
//        }
//    }
//
//
//
//    public static List hmget(Object key, String... fields) {
//        List result = null;
//        Jedis jedis = null;
//        try {
//
//            jedis = jedisPool.getResource();
//            result = jedis.hmget(key.toString(), fields);
//
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//
//        } finally {
//            // 返还到连接池
//            close(jedis);
//
//        }
//        return result;
//    }
//
//
//
//    public static Set hkeys(String key) {
//        Set result = null;
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            result = jedis.hkeys(key);
//
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//
//        } finally {
//            // 返还到连接池
//            close(jedis);
//
//        }
//        return result;
//    }
//
//
//
//    public static List lrange(byte[] key, int from, int to) {
//        List result = null;
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            result = jedis.lrange(key, from, to);
//
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//
//        } finally {
//            // 返还到连接池
//            close(jedis);
//
//        }
//        return result;
//    }
//
//
//
//    public static Map hgetAll(byte[] key) {
//        Map result = null;
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            result = jedis.hgetAll(key);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//        return result;
//    }
//
//
//
//    public static void del(byte[] key) {
//
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.del(key);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//    }
//
//
//
//    public static long llen(byte[] key) {
//
//        long len = 0;
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.llen(key);
//        } catch (Exception e) {
//            // 释放redis对象
//            jedisPool.returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            // 返还到连接池
//            close(jedis);
//        }
//        return len;
//    }

}
