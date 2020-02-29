package com.myblog.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.myblog.entity.User;

public class LoginInterceptor implements HandlerInterceptor{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest req,HttpServletResponse res,Object handler) {
		
		User user = (User)req.getSession().getAttribute("USER_SESSION");
		
		logger.info(req.getRequestURI().toString());
		
		if(user == null) {
			logger.info("请先登录");
			try {
				res.sendRedirect("/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		return true;
	}
}
