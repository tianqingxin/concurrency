package com.inspur.concurrency.lock;

import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * stampedLock的用法，线程执行之前写入锁，然后线程执行结束以后将该锁释放
 */
@Slf4j
@ThreadSafe
public class LockExample1 {

    /**
     * 请求总数
     */
    private static final int clientTotal = 5000;
    /**
     * 线程池总数
     */
    private static final int threadTotal = 200;

    private static Set<Integer> list = new HashSet<>();

    private final static StampedLock lock = new StampedLock();


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
        long key=lock.writeLock();
        try {
            list.add(i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一定要注意释放锁，使用时一般放到finally里面
            lock.unlock(key);
        }
    }
}
