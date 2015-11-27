package com.gxwsxx.tool.link.properties.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通过zookeeper实现的配置源中获取配置信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
@Component("linkPropertiesZookeeperReader")
public class LinkPropertiesZookeeperReader implements LinkPropertiesReader {

	private static final Logger log = LoggerFactory.getLogger(LinkPropertiesZookeeperReader.class);
	
	/**
	 * @see com.gxwsxx.tool.link.properties.reader.LinkPropertiesReader#valueString(java.lang.String)
	 */
	@Override
	public String valueString(String propertyKey) {
		return null;
	}

}
