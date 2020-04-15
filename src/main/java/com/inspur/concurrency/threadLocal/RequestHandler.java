package com.inspur.concurrency.threadLocal;
import com.inspur.concurrency.pojo.User;

import  java.util.Map;

/**
 * @description: threadLocal+filter实现拦截服务请求后存储登录用户信息
 * @create: 2020-04-15 13:47
 **/
public class RequestHandler {

    private final static  ThreadLocal <User> userHandler = new ThreadLocal<>();

    //过滤器过滤请求时将用户信息存到threadlocal
    public static void setUserInfo(User user){
        userHandler.set(user);
    }

    //接口调用时可随时获取信息
    public static User get(){
        return userHandler.get();
    }

    //在一次访问完成后，拦截器中进行移除，以免内存泄漏
    public static void remove (){
        userHandler.remove();
    }
}
