package com.inspur.concurrency.cutdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程安全的countdownlatch,计数器，针对原子操作的
 */
@Slf4j
public class CutDownLatchExample {

    private static final int threadCount = 100;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    addNum(count);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void addNum(int num) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",num);
    }
}
