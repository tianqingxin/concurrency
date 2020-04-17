package com.inspur.concurrency.lock;

import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock替代synchronized锁的使用方法，但是一定要注意，在一个线程结束以后
 * 将锁释放，以免造成死锁
 */
@Slf4j
@ThreadSafe
public class LockExample {

    /**
     * 请求总数
     */
    private static final int clientTotal = 5000;
    /**
     * 线程池总数
     */
    private static final int threadTotal = 200;

    private static Set<Integer> list = new HashSet<>();

    private static Lock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int finalI = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(finalI);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("list.size()={}", list.size());
    }

    private static void add(int i) {
//        list.add(i);
        try {
            lock.lock();
            list.add(i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一定要注意释放锁，使用时一般放到finally里面
            lock.unlock();
        }
    }
}
