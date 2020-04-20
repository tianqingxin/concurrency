package com.inspur.concurrency.mq.rabbitmq;

import com.inspur.concurrency.mq.rabbitmq.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 生产者，同样可以多个，多个的配置项相同
 */
@Component
@Slf4j
public class RabbitMQProducer {

//    @Resource
//    private AmqpTemplate rabbitTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        log.info("Producer message={}",message);
        rabbitTemplate.convertAndSend(QueueConstants.TEST, message);
    }

    public void postUser(User user){
        log.info("producer.user={}",user.toString());
        rabbitTemplate.convertAndSend(QueueConstants.USERQUEUE,user);
    }
}
