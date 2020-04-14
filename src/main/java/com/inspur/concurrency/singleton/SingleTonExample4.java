package com.inspur.concurrency.singleton;

import com.inspur.concurrency.annotation.ThreadNotSafe;

/**
 * @description: 双重为空判断创建懒汉模式的单例，但是在synchronized锁定该类之后，创建实例之前会进行以下步骤：
 * 1、为对象分配空间
 * 2、创建对象实例
 * 3、将空间分配给对象
 * 在单线程中这三步发生指令重排没有任何影响，但是如果在多线程中，第一个线程A在按照1-3-2顺序执行到3时，线程B在判断对象是否为空时则
 * 认定已经创建对象并分配空间，则会直接返回，此时的对象实例实际还没创建.
 * 如果想线程安全，则需要使用volatile修饰变量禁止指令重排
 * @create: 2020-04-14 16:14
 **/
@ThreadNotSafe
public class SingleTonExample4 {

    private SingleTonExample4() {

    }

    private static SingleTonExample4 singleTonExample4;

    public static SingleTonExample4 getInstance() {
        if (singleTonExample4 == null) {  //双重检测
            synchronized (SingleTonExample4.class) {    //同步锁
                if (singleTonExample4 == null) {
                    singleTonExample4 = new SingleTonExample4();
                }
            }
        }
        return singleTonExample4;
    }
}
