package com.gxwsxx.tool.link.properties.reader;

import org.springframework.stereotype.Component;

/**
 * 读取系统环境变量reader
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@Component("linkPropertiesSystemEnvReader")
public class LinkPropertiesSystemEnvReader implements LinkPropertiesReader {

	/**
	 * @see com.gxwsxx.tool.link.properties.reader.LinkPropertiesReader#valueString(java.lang.String)
	 */
	@Override
	public String valueString(String key) {
		return System.getenv(key);
	}

}
