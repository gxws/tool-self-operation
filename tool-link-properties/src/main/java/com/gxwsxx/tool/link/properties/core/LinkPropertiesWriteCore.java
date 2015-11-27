package com.gxwsxx.tool.link.properties.core;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 配置写入
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@Component("linkPropertiesWriteCore")
public class LinkPropertiesWriteCore {

	private static final Logger log = LoggerFactory.getLogger(LinkPropertiesWriteCore.class);

	/**
	 * 将所有配置的键值对，写入Properties对象中
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param props
	 *            spring Properties对象
	 * @param map
	 *            所有配置的键值对
	 * @since 3.0
	 */
	public void write(Properties props, Map<String, String> map) {
		if (null == props) {
			log.warn("spring Properties对象为null");
			return;
		}
		for (Entry<String, String> en : map.entrySet()) {
			props.setProperty(en.getKey(), en.getValue());
		}
	}

	/**
	 * 将所有配置的键值对，写入servlet context对象中
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param sc
	 *            servlet context对象
	 * @param map
	 *            所有配置的键值对
	 * @since 3.0
	 */
	public void write(ServletContext sc, Map<String, String> map) {
		if (null == sc) {
			log.warn("servlet context对象为null");
			return;
		}
		for (Entry<String, String> en : map.entrySet()) {
			sc.setAttribute(en.getKey(), en.getValue());
		}
	}

}
