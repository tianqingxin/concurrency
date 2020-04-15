package com.inspur.concurrency.dateformate;

import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description: 利用Joda-time的jar包时间处理来解决多线程时间的安全问题，之后极力推荐
 * @create: 2020-04-15 18:28
 **/
@Slf4j
@ThreadSafe
public class DateFormatExample3 {
    /**
     * 请求总数
     */
    private static final int clientTotal = 5000;
    /**
     * 线程池总数
     */
    private static final int threadTotal = 200;

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");

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
//        log.info("count:{},clientTotal:{},threadTotal:{}",count.get(),clientTotal,threadTotal);
    }

    private static void add(int i) {
        log.info("index={},date={}",i,DateTime.parse("20200415",formatter).toString());
    }
}
