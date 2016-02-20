package com.gxwsxx.tool.properties.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * 读取jvm启动参数reader
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class JavaOptsSource implements PropertiesSource {

	private final static Logger log = LoggerFactory.getLogger(JavaOptsSource.class);

	/**
	 * @see com.gxwsxx.tool.properties.source.PropertiesSource#get(com.gxwsxx.tool.properties.entity.PropertiesEntity)
	 */
	@Override
	public void get(PropertiesEntity entity) {
		String value = System.getProperty(entity.getKey());
		if (null == value) {
			log.error("没有能读取到属性" + entity.getKey());
		}
	}

}
