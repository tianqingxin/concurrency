package com.inspur.concurrency.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @create: 2020-04-15 14:53
 **/
@Data
@AllArgsConstructor
@ToString
public class User {
    private String userName;
    private String password;
    private String deptNo;
}
