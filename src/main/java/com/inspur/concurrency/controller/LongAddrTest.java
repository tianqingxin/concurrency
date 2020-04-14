package com.inspur.concurrency.controller;

import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description: 并发测试演示，线程安全的例子:LongAddr
 * LongAddr 相比较AtomicLong来说，LongAddr在底层通过将内存中值64位拆分成两个32位进行单独存储，
 * 读取速度更快，但是在高并发之下会确实一定精度，所以在高并发场景中使用AtomicLong最为稳妥。
 * @create: 2020-04-14 13:33
 **/
@Slf4j
@ThreadSafe
public class LongAddrTest {

    /**
     * 请求总数
     */
    private static final int clientTotal = 5000;
    /**
     * 线程池总数
     */
    private static final int threadTotal = 200;

    private static LongAdder count = new LongAdder();

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

    private static void add() {
        count.increment();
    }
}
