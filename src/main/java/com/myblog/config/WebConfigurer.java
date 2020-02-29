package com.myblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.myblog.interceptor.LoginInterceptor;

/**
 * 
 * @author nikola
 * q:注册拦截器
 */

@Configuration
public class WebConfigurer implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**");
	}
}
