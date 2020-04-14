package com.inspur.concurrency.controller;

import com.inspur.concurrency.utils.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @create: 2020-04-14 11:05
 **/
@Slf4j
@RestController
public class TestController {
    @Autowired
    private EntityUtils entityUtils;

    @GetMapping("/test")
    public String test(){
        return "test,serverPort==="+entityUtils.getServerPort();
    }
}
