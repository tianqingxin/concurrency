package com.inspur.concurrency.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @description:
 * @create: 2020-04-14 14:33
 **/
@Slf4j
public class AtomicInterFieldUpdaterTest {
    private static AtomicIntegerFieldUpdater<AtomicInterFieldUpdaterTest> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicInterFieldUpdaterTest.class, "count");

    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {
        AtomicInterFieldUpdaterTest atomicInterFieldUpdaterTest = new AtomicInterFieldUpdaterTest();
        if (updater.compareAndSet(atomicInterFieldUpdaterTest, 100, 200)) {
            log.info("update success 1 ,value={}", atomicInterFieldUpdaterTest.getCount());
        }
        if (updater.compareAndSet(atomicInterFieldUpdaterTest, 100, 200)) {
            log.info("update success 2 ,value={}", atomicInterFieldUpdaterTest.getCount());
        }else{
            log.info("update failed ,value={}", atomicInterFieldUpdaterTest.getCount());
        }

    }
}
