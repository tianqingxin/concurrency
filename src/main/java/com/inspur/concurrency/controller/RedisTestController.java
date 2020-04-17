package com.inspur.concurrency.controller;

import com.inspur.concurrency.config.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@Slf4j
public class RedisTestController {

    /**
     * 获取redis实例
     */
    @Autowired
    private RedisClient redisClient;

    @PostMapping("/insert/{key}/{value}")
    public String insert(@PathVariable("key") String key,
                         @PathVariable("value") String value) {
        try {
            //向redis中插入值
//            RedisClient.getRedisClient().set(key, value);
            redisClient.set(key, value);
            log.info("插入成功!");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("failure:{}", e.toString());
            return "failure";
        } finally {

        }
    }

    @GetMapping("/getKeyValue")
    public String getKeyValue(@RequestParam("key") String key) {
        String value = "";
        try {
//            value = RedisClient.getRedisClient().get(key);
            value = redisClient.get(key);
            log.info("读取成功，value={}",value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("failure:{}", e.toString());
            return "failure";
        } finally {

        }
        return value;
    }
}
