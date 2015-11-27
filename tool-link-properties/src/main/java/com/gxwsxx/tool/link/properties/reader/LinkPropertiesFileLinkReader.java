package com.gxwsxx.tool.link.properties.reader;

import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

/**
 * 读取项目文件link.properties的reader
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@Component("linkPropertiesFileLinkReader")
public class LinkPropertiesFileLinkReader implements LinkPropertiesReader {

	// 文件名为link.properties
	private static final String LINK_FILE_NAME = "link";

	private ResourceBundle linkFile;

	/**
	 * 读取项目文件link.properties
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @since 3.0
	 */
	public LinkPropertiesFileLinkReader() {
		linkFile = ResourceBundle.getBundle(LINK_FILE_NAME);
	}

	/**
	 * @see com.gxwsxx.tool.link.properties.reader.LinkPropertiesReader#valueString(java.lang.String)
	 */
	@Override
	public String valueString(String key) {
		try {
			return linkFile.getString(System.getProperty("project.env") + "." + key);
		} catch (Exception e) {
			return null;
		}
	}
}
