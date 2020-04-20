package com.inspur.concurrency.mq.rabbitmq;

import com.inspur.concurrency.mq.rabbitmq.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RabbitMQTestController {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @RequestMapping("/mqTest/{message}")
    public void mqTest(@PathVariable("message") String message) {
        log.info("controller message={}", message);
        rabbitMQProducer.send(message);
    }

    @RequestMapping("/getUser/{userName}/{age}")
    String getUser(@PathVariable("userName") String userName,
                   @PathVariable("age") int age) {
        User user = new User(userName,age);
        rabbitMQProducer.postUser(user);

        return user.toString();
    }
}
