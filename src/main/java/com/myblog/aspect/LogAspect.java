package com.myblog.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogAspect {
	
	@Pointcut("execution(* com.myblog.web.*.*(..))")
	public void log() {}
	
	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		//获取Request
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = attr.getRequest();
		String url = req.getRequestURL().toString();
		String ip = req.getRemoteAddr();
		String className = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		
		Logger log = LoggerFactory.getLogger(this.getClass());
		
		RequestInfo info = new RequestInfo(url,ip,className,args);
		
		log.info("Request : {}", info);
		
	}
	
	class RequestInfo{
		private String url;
		private String ip;
		private String className;
		private Object[] args;
		public RequestInfo(String url, String ip, String className, Object[] args) {
			super();
			this.url = url;
			this.ip = ip;
			this.className = className;
			this.args = args;
		}
		@Override
		public String toString() {
			return "RequestInfo {url=" + url + ", ip=" + ip + ", className=" + className + ", args="
					+ Arrays.toString(args) + "}";
		}
		
	}
	
}
