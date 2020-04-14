package com.inspur.concurrency.singleton;

import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 饿汉模式创建单例，是线程安全的，但是当多线程中引用但没有使用单例会造成资源浪费，
 * 同时在类加载时进行创建实例的模式会造成加载程序缓慢
 * @create: 2020-04-14 16:03
 **/
@Slf4j
@ThreadSafe
public class SingleTonExample2 {

    private SingleTonExample2(){

    }

    private static SingleTonExample2 singleTonExample2 = new SingleTonExample2();

    public static SingleTonExample2 getInstance() {
        return singleTonExample2;
    }
}
