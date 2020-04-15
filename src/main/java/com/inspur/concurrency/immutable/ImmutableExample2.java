package com.inspur.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @description: 通过guava包提供的immutable方法创建不可修改的集合
 * @create: 2020-04-15 10:45
 **/
@Slf4j
public class ImmutableExample2 {

    private static ImmutableList list = ImmutableList.of("1","2","3");

    private static ImmutableSet set =ImmutableSet.copyOf(list);

    private static ImmutableMap<String,Object> immutableMap =
            ImmutableMap.of("1",2,"2",3);

    private static ImmutableMap<String,Object> immutableMap2 =
            ImmutableMap.<String,Object>builder().put("1",2).put("2",3).build();

    public static void main(String[] args) {
//        list.add("6");
//        set.add("5");
//        immutableMap.put("3",6);
//        immutableMap2.put("4",8);
        log.info(""+list.get(0));
    }
}
