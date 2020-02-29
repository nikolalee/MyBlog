package com.myblog.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @author nikola
 * @ControllerAdvice作用:全局异常处理;全局数据绑定;全局数据预处理，拦截controller
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	/**该处理器只能处理controller中的异常
	 * Logger框架写Log三个步骤:引入loggerg类和logger工厂类,声明logger,记录日志
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception 
	 * @ExceptionHandler:异常处理方式之一，参数是某个异常类的class，代表这个方法专门处理该类异常
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(HttpServletRequest req,Exception e) throws Exception {
		
		log.error("Request Url : {}, Error : {}", req.getRequestURL(),e);
		
		if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", req.getRequestURL());
		mav.addObject("exception", e);
		mav.setViewName("error/error");
		
		return mav;
	}
}
