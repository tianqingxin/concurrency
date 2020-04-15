package com.inspur.concurrency.singleton;

import com.inspur.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.ToString;

/**
 * @description: 使用枚举类构造方法在jvm中只调用一次的特性来生成单例
 * @create: 2020-04-14 16:29
 **/
@ThreadSafe
public class SingleTonExample6 {

    private SingleTonExample6() {

    }

    public static SingleTonExample6 getInstance() {
        return Singleton.INSTANCE.getSingleTonExample6();
    }


    private enum Singleton {


        INSTANCE;
//        SPRING("22");

        private SingleTonExample6 singleTonExample6;

        private Singleton() {
            singleTonExample6 = new SingleTonExample6();
        }

        public SingleTonExample6 getSingleTonExample6(){
            return singleTonExample6;
        }
    }
}
