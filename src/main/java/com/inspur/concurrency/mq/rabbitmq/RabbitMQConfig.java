package com.inspur.concurrency.mq.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue(){
        return new Queue(QueueConstants.TEST);
    }
    @Bean
    public Queue userQueue(){
        return new Queue(QueueConstants.USERQUEUE);
    }
}
