package com.inspur.concurrency.singleton;

import com.inspur.concurrency.annotation.NotRecommand;
import com.inspur.concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 懒汉模式创建单例，但是该方法线程不安全
 * @create: 2020-04-14 15:57
 **/
@Slf4j
@ThreadNotSafe
@NotRecommand
public class SingleTonExample1 {
    //私有化
    private static SingleTonExample1 singleTonExample1;

    //私有化构造方法，令外部无法直接创建实例
    private SingleTonExample1() {

    }

    public static SingleTonExample1 getInstance() {
        if (singleTonExample1 == null) {
            singleTonExample1 = new SingleTonExample1();
        }
        return singleTonExample1;
    }
}
