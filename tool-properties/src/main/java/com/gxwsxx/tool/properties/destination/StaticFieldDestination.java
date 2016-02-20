package com.gxwsxx.tool.properties.destination;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * 静态变量设置
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class StaticFieldDestination implements PropertiesDestination {

	private static final Logger log = LoggerFactory.getLogger(StaticFieldDestination.class);

	/**
	 * @see com.gxwsxx.tool.properties.destination.PropertiesDestination#set(com.gxwsxx.tool.properties.entity.PropertiesEntity)
	 */
	@Override
	public void set(PropertiesEntity entity) {
		try {
			Field field = entity.getClazz().getDeclaredField(entity.getFieldName());
			field.set(null, entity.getValue());
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
			log.error("变量" + entity.getKey() + "赋值错误，类名" + entity.getClazz().getName() + "字段名" + entity.getFieldName()
					+ "，设置值" + entity.getValue(), e);
		}
	}

}
