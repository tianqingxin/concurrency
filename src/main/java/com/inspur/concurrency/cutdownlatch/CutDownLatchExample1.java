package com.inspur.concurrency.cutdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程安全的countdownlatch,计数器，可以设定给线程执行等待的时间，
 * 如果超过时间则计数器继续执行，但是正在执行的线程仍会继续执行直到全部执行完毕
 */
@Slf4j
public class CutDownLatchExample1 {

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
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("execute finish.......");
        executorService.shutdown();
    }

    private static void addNum(int num) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",num);
    }
}
