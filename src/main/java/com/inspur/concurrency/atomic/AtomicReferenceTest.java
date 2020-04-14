package com.inspur.concurrency.atomic;

import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @create: 2020-04-14 14:24
 **/
@Slf4j
@ThreadSafe
public class AtomicReferenceTest {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(0);
    public static void main(String[] args) {
        atomicReference.compareAndSet(0,2);
        atomicReference.compareAndSet(0,1);
        atomicReference.compareAndSet(1,3);
        atomicReference.compareAndSet(2,4);
        atomicReference.compareAndSet(3,5);
        atomicReference.compareAndSet(4,6);
        log.info("atomicReference={}",atomicReference.get());
    }
}
