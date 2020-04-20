package com.inspur.concurrency.mq.rabbitmq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
@AllArgsConstructor
public class User implements Serializable {
    private String userName;
    private int age;
}
