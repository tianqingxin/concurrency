package com.inspur.concurrency.cutdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * semaphore是通过限制单位时间内请求访问的最大线程数来保证线程安全，不至于线程过于拥挤导致阻塞
 * 最大应用就是在数据库连接
 */
@Slf4j
public class SemaphoreExample {

    private static final int threadCount = 100;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        //semaphore指定一次请求的最大线程个数为4个
        final Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < threadCount; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    addNum(count);
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            });
        }
        log.info("execute finish.......");
        executorService.shutdown();
    }

    private static void addNum(int num) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",num);
    }
}
