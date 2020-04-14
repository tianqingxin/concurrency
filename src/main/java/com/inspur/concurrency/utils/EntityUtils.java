package com.inspur.concurrency.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @description:
 * @create: 2020-04-14 11:06
 **/
@NoArgsConstructor
@Configuration
public class EntityUtils {

    private static volatile EntityUtils entityUtils;

    @Getter
    @Value("${server.port}")
    private String serverPort;

    @Bean
    public static EntityUtils getInstance() {
        if (entityUtils == null) {
            synchronized (EntityUtils.class) {
                if (entityUtils == null) {
                    entityUtils = new EntityUtils();
                }
            }
        }
        return entityUtils;
    }
}
