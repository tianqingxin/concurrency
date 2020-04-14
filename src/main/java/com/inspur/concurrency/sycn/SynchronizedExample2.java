package com.inspur.concurrency.sycn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 锁修饰静态方法，则在多线程中按每个实例的执行顺序执行，多实例不会同时执行
 * @create: 2020-04-14 15:01
 **/
@Slf4j
public class SynchronizedExample2 {

    public static void test1( int k) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1  {}={}",k, i);
            }
        }
    }

    public static synchronized void test2(int k) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {}={}",k, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            synchronizedExample1.test2(1);
        });
        executorService.execute(() ->{
            synchronizedExample2.test2(2);
        });
        executorService.shutdown();
    }
}
