package com.inspur.concurrency.synchronize;

import com.inspur.concurrency.annotation.ThreadNotSafe;
import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description: 在针对一个collections容器进行同时操作的两个线程下，
 * 尤其是在一个去除元素，一个对元素进行其他操作时
 * vector线程不安全
 * @create: 2020-04-15 21:30
 **/
@Slf4j
@ThreadNotSafe
public class VectorExample1 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true){
            for(int i=0;i<10;i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                public void run(){
                    for(int i=0;i<vector.size();i++){
                        vector.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread(){
                public void run(){
                    for(int i=0;i<vector.size();i++){
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
