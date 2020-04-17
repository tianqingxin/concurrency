package com.inspur.concurrency.deadLock;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 死锁产生四个必要条件：
 * （1） 互斥条件：一个资源每次只能被一个进程使用。
 * （2） 请求与保持条件：一个进程因请求资源而阻塞时，
 *       对已获得的资源保持不放。
 * （3） 不剥夺条件:进程已获得的资源，在末使用完之前，不能强行剥夺。
 * （4） 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
 */
@Slf4j
public class DeadLockExample implements Runnable {

    private int flag;

    private final  static Object o1 = new Object(), o2 = new Object();


    @SneakyThrows
    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                Thread.sleep(500);
                log.info("lock o1......");
                synchronized (o2) {
                    log.info("execute o2");
                }
            }
        }
        if (flag == 2) {
            synchronized (o2) {
                Thread.sleep(500);
                log.info("lock o2......");
                synchronized (o1) {
                    log.info("execute o1");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLockExample deadLockExample1 = new DeadLockExample();
        DeadLockExample deadLockExample2 = new DeadLockExample();
        deadLockExample1.flag = 1;
        deadLockExample2.flag = 2;
        new Thread(deadLockExample1).start();
        new Thread(deadLockExample2).start();
    }
}
