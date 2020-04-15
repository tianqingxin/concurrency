package com.inspur.concurrency;

import com.inspur.concurrency.threadLocal.HttpFilter;
import com.inspur.concurrency.threadLocal.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ConcurrencyApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}
}
