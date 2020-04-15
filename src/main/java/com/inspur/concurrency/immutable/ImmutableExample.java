package com.inspur.concurrency.immutable;
import com.google.common.collect.Maps;
import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import  java.util.Map;
/**
 * @description: 通过utils包下面的Collections类可以创建不允许修改的map、list、set等集合，可以将某些初始化的常量放入
 * 集合中供其他地方调用，保证线程安全性
 * @create: 2020-04-15 10:45
 **/
@Slf4j
@ThreadSafe
public class ImmutableExample {
    private static final Integer a =0;
    //利用guava包下面的Maps创建的map会自动添加泛型
    private static  Map<Integer,Integer> unModifiableMap = Maps.newHashMap();

    static {
        unModifiableMap.put(1,2);
        //令集合不允许修改
        unModifiableMap = Collections.unmodifiableMap(unModifiableMap);
    }

    public static void main(String[] args) {
        log.info("intMap={}",unModifiableMap.toString());
        unModifiableMap.put(1,3);
    }
}
