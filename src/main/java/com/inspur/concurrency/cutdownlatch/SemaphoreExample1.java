package com.inspur.concurrency.cutdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore是通过限制单位时间内请求访问的最大线程数来保证线程安全，不至于线程过于拥挤导致阻塞
 * 可以设置每次线程获取的许可数量，来确定单位时间内执行的线程数
 */
@Slf4j
public class SemaphoreExample1 {

    private static final int threadCount = 100;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        //semaphore指定一次请求的最大线程个数为4个
        final Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < threadCount; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(3); //一次获取3个许可，一共有三个许可，所以相当于每次仅一个线程执行
                    addNum(count);
                    semaphore.release(3);    //释放许可
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
