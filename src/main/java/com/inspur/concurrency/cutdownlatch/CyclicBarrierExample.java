package com.inspur.concurrency.cutdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 *
 */
@Slf4j
public class CyclicBarrierExample {

    //定义一个cyclicBarrier，设定五个线程
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i =0 ; i < 10; i++){
            final int count = i;
            executorService.execute(() ->{
                try {
                    race(count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void race (int count) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        log.info("{} ready",count);
        barrier.await();
        log.info("{} go",count);
    }
}
