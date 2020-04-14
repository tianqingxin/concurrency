package com.inspur.concurrency.singleton;

import com.inspur.concurrency.annotation.ThreadSafe;

/**
 * @description: synchronized修饰静态方法的懒汉模式创建单例，
 * 由于synchronized只能有一个实例进行执行，则可以保证线程安全，
 * 但是该方法会令其他线程进行等待，造成访问过慢的性能问题
 * @create: 2020-04-14 16:10
 **/
@ThreadSafe
public class SingleTonExample3 {
    private SingleTonExample3() {

    }

    private static SingleTonExample3 singleTonExample3;

    public static synchronized SingleTonExample3 getInstance() {
        if (singleTonExample3 == null) {
            singleTonExample3 = new SingleTonExample3();
        }
        return singleTonExample3;
    }
}
