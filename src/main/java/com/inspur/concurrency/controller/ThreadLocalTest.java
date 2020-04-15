package com.inspur.concurrency.controller;

import com.inspur.concurrency.pojo.User;
import com.inspur.concurrency.threadLocal.RequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @create: 2020-04-15 14:21
 **/
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalTest {

    @RequestMapping("/getUser")
    public User getUser (){
        return RequestHandler.get();
    }
}
