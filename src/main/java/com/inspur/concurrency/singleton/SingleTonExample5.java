package com.inspur.concurrency.singleton;

import com.inspur.concurrency.annotation.ThreadSafe;

/**
 * @description: 使用volatile+双重检测机制实现线程安全的懒汉模式单例
 * @create: 2020-04-14 16:29
 **/
@ThreadSafe
public class SingleTonExample5 {

    //使用volatile关键字修饰变量，禁止内存在变量的读写过程中发生指令重排操作
    private volatile static SingleTonExample5 singleTonExample5;
    private SingleTonExample5(){

    }

    public static SingleTonExample5 getInstance(){
        if(singleTonExample5 == null){
            synchronized (SingleTonExample5.class){ //双重检测
                if(singleTonExample5 == null){
                    singleTonExample5 = new SingleTonExample5();
                }
            }
        }
        return singleTonExample5;
    }
}
