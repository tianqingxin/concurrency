package com.inspur.concurrency.utils;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @create: 2020-04-14 11:06
 **/
@Configuration
@NoArgsConstructor
public class EntityUtils {

    private EntityUtils entityUtils;

    @Getter
    @Value("${server.port}")
    private String serverPort;

    @Bean
    EntityUtils getEntityUtils() {
        if (entityUtils == null) {
            entityUtils = new EntityUtils();
        }
        return entityUtils;
    }
}
