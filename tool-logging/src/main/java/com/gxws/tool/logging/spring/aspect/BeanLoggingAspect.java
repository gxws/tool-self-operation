package com.gxws.tool.logging.spring.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;

import com.alibaba.fastjson.JSON;
import com.gxws.tool.logging.constant.LoggingMarkerConstant;

/**
 * spring bean aop记录日志信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class BeanLoggingAspect {

	private static final Logger log = LogManager.getLogger(BeanLoggingAspect.class);

	/**
	 * spring bean方法调用前的aspect before<br>
	 * 调用spring bean 方法时输入的参数，记录log中<br>
	 * 将调用的spring bean 方法相关信息，记录到log的ThreadContext中
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param jp
	 *            JoinPoint对象
	 * @since 1.0
	 */
	public void before(JoinPoint jp) {
		String location = jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
		ThreadContext.push(location);
		try {
			log.info(LoggingMarkerConstant.BEAN_ASPECT_MARKER, JSON.toJSON(jp.getArgs()));
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer("{");
			for (Object o : jp.getArgs()) {
				if (null != o) {
					sb.append(o.toString() + ",");
				}
			}
			sb.append("}");
			log.info(LoggingMarkerConstant.BEAN_ASPECT_MARKER, sb.toString());
		}
	}

	/**
	 * spring bean方法调用前的aspect after对象<br>
	 * 将调用的spring bean 方法相关信息，从log的ThreadContext中清除掉
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param jp
	 *            JoinPoint对象
	 * @since 1.0
	 */
	public void after(JoinPoint jp) {
		String location = jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
		if (location.endsWith(ThreadContext.peek())) {
			ThreadContext.pop();
		}
	}
}
