package com.gxwsxx.tool.link.properties.reader;

import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

/**
 * 读取项目文件link.properties的reader
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@Component("linkPropertiesFileProjectReader")
public class LinkPropertiesFileProjectReader implements LinkPropertiesReader {

	private static final String PROJECT_FILE_NAME = "project";

	private ResourceBundle projectFile;

	/**
	 * @see com.gxwsxx.tool.link.properties.reader.LinkPropertiesReader#valueString(java.lang.String)
	 */
	@Override
	public String valueString(String key) {
		projectFile = ResourceBundle.getBundle(PROJECT_FILE_NAME);
		try {
			return projectFile.getString(key);
		} catch (Exception e) {
			return null;
		}
	}
}
