package com.gxwsxx.tool.link.properties.reader;

import org.springframework.stereotype.Component;

/**
 * 读取jvm启动参数reader
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@Component("linkPropertiesJavaOptsReader")
public class LinkPropertiesJavaOptsReader implements LinkPropertiesReader {

	/**
	 * @see com.gxwsxx.tool.link.properties.reader.LinkPropertiesReader#valueString(java.lang.String)
	 */
	@Override
	public String valueString(String key) {
		return System.getProperty(key);
	}

}
