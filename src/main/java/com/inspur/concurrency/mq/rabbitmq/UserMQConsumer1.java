package com.inspur.concurrency.mq.rabbitmq;

import com.inspur.concurrency.mq.rabbitmq.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 多消费者-消费者1
 */
@Slf4j
@Component
@RabbitListener(queues = QueueConstants.USERQUEUE)
public class UserMQConsumer1 {

    @RabbitHandler
    public void getUser(User user){
      log.info("consumer1.user={}",user.toString());
    }
}
