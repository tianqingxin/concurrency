package com.inspur.concurrency.controller;

import com.inspur.concurrency.annotation.ThreadNotSafe;
import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description: 并发测试演示，线程不安全的例子,当在计数的静态方法上添加锁synchronized，该线程变为安全，
 * 因为当锁修饰静态方法时，只能按实例顺序执行，同一时间只能执行一个实例
 * @create: 2020-04-14 13:33
 **/
@Slf4j
@ThreadSafe
public class ThreadNotSafeConcurrencyTest {

    /**
     * 请求总数
     */
    private static final int clientTotal = 5000;
    /**
     * 线程池总数
     */
    private static final int threadTotal = 200;

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{},clientTotal:{},threadTotal:{}",count,clientTotal,threadTotal);
    }

    private synchronized static void add() {
        count++;
    }
}
