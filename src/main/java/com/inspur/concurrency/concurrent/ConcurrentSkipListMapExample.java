package com.inspur.concurrency.concurrent;

import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @description: ConcurrentSkipListMap为treemap的高并发容器,比起hashmap，tree可对键进行排序
 * @create: 2020-04-15 21:57
 **/
@Slf4j
@ThreadSafe
public class ConcurrentSkipListMapExample {
    /**
     * 请求总数
     */
    private static final int clientTotal = 5000;
    /**
     * 线程池总数
     */
    private static final int threadTotal = 200;

    private static Map<Integer,Integer> list = new ConcurrentSkipListMap<>();

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
        log.info("list.size()={}",list.size());
    }

    private static void add(int i) {
        list.put(i,i);
    }

}
