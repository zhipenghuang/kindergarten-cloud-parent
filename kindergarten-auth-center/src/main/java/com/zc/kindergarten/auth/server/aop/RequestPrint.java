package com.zc.kindergarten.auth.server.aop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.joda.time.DateTime;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Aspect
@Slf4j
@Order(value = 1)
@Component
public class RequestPrint {
	/**
	 * 请求地址
	 */
	private String requestPath = null;
	/**
	 * 请求头
	 */
	private String requestHeader = null;
	/**
	 * 用户名
	 */
	private String userName = null;
	/**
	 * 传入参数
	 */
	private Map<?, ?> inputParamMap = null;
	/**
	 * 存放输出结果
	 */
	private Map<String, Object> outputParamMap = null;
	/**
	 * 开始时间
	 */
	private long startTimeMillis = 0;
	/**
	 * 结束时间
	 */
	private long endTimeMillis = 0;

	/**
	 * 方法调用前触发 记录开始时间
	 */
	@Before("execution(* com.zc.kindergarten.auth.server.controller..*.*(..))")
	public void doBeforeInServiceLayer(JoinPoint joinPoint) {
		// 记录方法开始执行的时间
		startTimeMillis = System.currentTimeMillis();
	}

	/**
	 * 方法调用后触发 记录结束时间
	 */
	@After("execution(* com.zc.kindergarten.auth.server.controller..*.*(..))")
	public void doAfterInServiceLayer(JoinPoint joinPoint) {
		// 记录方法执行完成的时间
		endTimeMillis = System.currentTimeMillis();
		printOptLog();
	}

	/**
	 * 环绕触发
	 */
	@Around("execution(* com.zc.kindergarten.auth.server.controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		/**
		 * 1.获取request信息 2.根据request获取session 3.从session中取出登录用户信息
		 */
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		Enumeration<String> headers = request.getHeaderNames();
		List<String> headersList = new ArrayList<>();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			headersList.add(header + "=" + request.getHeader(header));
		}
		//请求头
		requestHeader = headersList.toString();
		// 获取输入参数
		inputParamMap = request.getParameterMap();
		// 获取请求地址
		requestPath = request.getRequestURL().toString();
		// 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
		outputParamMap = new HashMap<String, Object>();
		// result的值就是被拦截方法的返回值
		Object result = pjp.proceed();
		outputParamMap.put("result", result);
		return result;
	}

	/**
	 * 输出日志
	 */
	private void printOptLog() {
		// 需要用到google的gson解析包
		Gson gson = new Gson();
		DateTime dateTime = new DateTime(startTimeMillis);
		log.info("\n请求地址：" + requestPath + "\n请求头：" + requestHeader + "\n请求时间：" +
				dateTime.toString("yyyy-MM-dd HH:mm:ss.sss") + "\n花费时间：" + (endTimeMillis - startTimeMillis)
				+ "ms" + "\n请求参数：" + gson.toJson(inputParamMap) + "\n请求结果：" + gson.toJson(outputParamMap));
	}
}
