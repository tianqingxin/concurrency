package com.inspur.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 线程学习中推荐的用法
 */
@Target(ElementType.TYPE) //作用于什么地方,type为作用于类、接口和enum声明
@Retention(RetentionPolicy.RUNTIME) //生命周期，runtime为项目运行时有效
public @interface Recommand {
    String value() default "";
}
