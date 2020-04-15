package com.inspur.concurrency.threadLocal;

import com.inspur.concurrency.pojo.User;
import com.inspur.concurrency.threadLocal.RequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description:
 * @create: 2020-04-15 13:37
 **/
@Slf4j
@Configuration
public class HttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        获取用户信息
//        request.getSession().getAttribute("user");
        log.info("do filter {} ,{}", Thread.currentThread().getId(), request.getServletPath());
        //用户信息多次添加，后一次会覆盖前一次
        User user = new User("tqx", "tqx", "inspur");
        User user2 = new User("tqx1", "tqx2", "inspur2");
        log.info("user={}", user.toString());
        RequestHandler.setUserInfo(user);
        RequestHandler.setUserInfo(user2);
        filterChain.doFilter(request, servletResponse);
    }

    /**
     * 将filter注入到ioc容器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean zrHttpFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new HttpFilter());
        filterRegistrationBean.addUrlPatterns("/threadLocal/*");
        return filterRegistrationBean;
    }
}
