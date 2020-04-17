package com.inspur.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程池executor
 */
@Slf4j
public class ThreadPoolExample1 {

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int count = i;
            //线程池执行线程的两种方法
//            EXECUTOR_SERVICE.execute(() -> log.info("count={}",count));
        EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                log.info("count={}",count);
            }
        });
        }
        EXECUTOR_SERVICE.shutdown();
    }
}
