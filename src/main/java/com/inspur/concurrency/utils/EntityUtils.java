package com.inspur.concurrency.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @description: 枚举类实例在调用构造函数时，jvm只会调用一次
 * @create: 2020-04-14 11:06
 **/
@NoArgsConstructor
@Component
public class EntityUtils {

    @Getter
    @Value("${server.port}")
    private String serverPort;

//    @Bean("getEntityUtils")
//    public static EntityUtils getInstance() {
//        return Singleton.INSTANCE.getEntityUtils();
//    }
//
//    private enum Singleton {
//        INSTANCE;
//        private EntityUtils entityUtils;
//
//        Singleton() {
//            entityUtils = new EntityUtils();
//        }
//
//        public EntityUtils getEntityUtils() {
//            return entityUtils;
//        }
//    }
}
