package com.gxwsxx.tool.properties.destination;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * 对象变量设置
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class ObjectDestination implements PropertiesDestination {

	private static final Logger log = LoggerFactory.getLogger(ObjectDestination.class);

	/**
	 * @see com.gxwsxx.tool.properties.destination.PropertiesDestination#set(com.gxwsxx.tool.properties.entity.PropertiesEntity)
	 */
	@Override
	public void set(PropertiesEntity entity) {
		String methodName = "set";
		if (1 < entity.getFieldName().length()) {
			methodName = methodName + entity.getFieldName().substring(0, 1).toUpperCase()
					+ entity.getFieldName().substring(1);
		} else {
			methodName = methodName + entity.getFieldName().substring(0, 1).toUpperCase();
		}
		try {
			Method setMethod = entity.getClazz().getMethod(methodName, String.class);
			setMethod.invoke(entity.getTarget(), entity.getValue());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			log.error("变量" + entity.getKey() + "赋值错误，类名" + entity.getClazz().getName() + "方法名" + methodName + "，设置值"
					+ entity.getValue(), e);
		}
	}

}
