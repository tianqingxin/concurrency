package com.inspur.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *ScheduledExecutorService任务调度，可与timer同样作为定时任务
 */
@Slf4j
public class ThreadPoolExample4 {

    private final static ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) {

//        EXECUTOR_SERVICE.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.info("count={}");
//            }
//        }, 10, TimeUnit.MILLISECONDS);

        //任务调度,这是设定的每三秒执行一次任务，类似于以下的timer定时任务
        EXECUTOR_SERVICE.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("doing");
            }
        },2,3,TimeUnit.SECONDS);


        //定时任务，第一次执行是当前时间，之后每隔三秒执行一次
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                log.info("doing");
//            }
//        },new Date(),3*1000);
    }
}
