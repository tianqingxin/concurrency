package com.inspur.concurrency.dateformate;

import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description: SimpleDateFormat 为线程不安全的对象，如果令其线程安全，
 * 则将创建SimpleDateFormat对象放到使用的方法里面
 * 因为在方法里面创建的变量都是堆栈封闭，
 * 可以认为：在多个调用方法时都会new一个SimpleDateFormat对象，每个对象互不影响，
 * 可以解决多线程调用情况。
 * @create: 2020-04-15 18:28
 **/
@Slf4j
@ThreadSafe
public class DateFormatExample2 {
    /**
     * 请求总数
     */
    private static final int clientTotal = 5000;
    /**
     * 线程池总数
     */
    private static final int threadTotal = 200;

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
//        log.info("count:{},clientTotal:{},threadTotal:{}",count.get(),clientTotal,threadTotal);
    }

    private static void add() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            format.parse("20181212");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
