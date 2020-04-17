package com.inspur.concurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * futuretask是callable和future的结合，即在创建futuretask实例时就可以创建一个callable线程，
 * 在线程开启之后可根据futuretask对象来获取callable线程的返回结果
 */
@Slf4j
public class FutureTaskExample {

   private static  FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            log.info("finish do something");
            return "Done";
        }
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);
//        new Thread(futureTask).start();
        log.info("do something in main");
        String res = futureTask.get();
        log.info("res={}",res);
    }

}
