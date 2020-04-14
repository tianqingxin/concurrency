package com.inspur.concurrency.sycn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 锁修饰代码块或方法，作用于该锁括号下
 * @create: 2020-04-14 15:01
 **/
@Slf4j
public class SynchronizedExample1 {

    public void test1( int k) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1  {}={}",k, i);
            }
        }
    }

    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2={}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            synchronizedExample1.test1(1);
        });
        executorService.execute(() ->{
            synchronizedExample2.test1(2);
        });
        executorService.shutdown();
    }
}
