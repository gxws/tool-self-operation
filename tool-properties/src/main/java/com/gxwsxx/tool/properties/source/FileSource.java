package com.gxwsxx.tool.properties.source;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * 读取项目文件project.properties的reader
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class FileSource implements PropertiesSource {

	private final static Logger log = LoggerFactory.getLogger(FileSource.class);

	private static final String PROJECT_FILE_NAME = "project";

	private ResourceBundle projectFile;

	/**
	 * @author zhuwl120820@gxwsxx.com
	 * @since 3.0
	 */
	public FileSource() {
		projectFile = ResourceBundle.getBundle(PROJECT_FILE_NAME);
	}

	/**
	 * @see com.gxwsxx.tool.properties.source.PropertiesSource#get(com.gxwsxx.tool.properties.entity.PropertiesEntity)
	 */
	@Override
	public void get(PropertiesEntity entity) {
		String value = null;
		try {
			value = projectFile.getString(entity.getKey());
		} catch (Exception e) {
			log.error("没有能读取到属性" + entity.getKey());
			log.error(e.getMessage(), e);
		}
		if (null == value) {
			log.error("没有能读取到属性" + entity.getKey());
		} else {
			entity.setValue(value);
		}
	}

}
