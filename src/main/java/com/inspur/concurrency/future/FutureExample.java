package com.inspur.concurrency.future;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 通过future可以获取通过callable或者runnable实现的线程的返回结果
 */
@Slf4j
public class FutureExample {

    static class myCallable implements Callable {

        @Override
        public String call() throws Exception {
            log.info("do something in call");
            Thread.sleep(5000);
            log.info("finish do something");
            return "Done";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future=executorService.submit(new myCallable());
        log.info("do something in main");
        String res=future.get();
        Thread.sleep(1000);
        log.info("res=="+res);
        executorService.shutdown();
    }
}
