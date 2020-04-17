package com.inspur.concurrency.config;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * 创建redis客户端实例，同时提供redis对数据的处理
 */
@Component
@NoArgsConstructor
public class RedisClient {

    @Resource(name = "jedisPool")
    private JedisPool jedisPool;

    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String reply=jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String get(String key) throws  Exception{
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

//    /**
//     * 提供redis客户端单例调用方法
//     * @return
//     */
//    @Bean("getRedisClient")
//    public static RedisClient getInstance(){
//        return rsClient.INSTANCE.getInstance();
//    }
//
//    private  enum rsClient{
//        INSTANCE;
//        private  RedisClient redisClient;
//        rsClient(){
//            redisClient=new RedisClient();
//        }
//        public  RedisClient getInstance(){
//            return redisClient;
//        }
//    }
}
