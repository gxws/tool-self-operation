package com.gxwsxx.tool.properties.source;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * 配置读取源
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public interface PropertiesSource {

	/**
	 * 从配置源读取数据
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param entity
	 *            配置信息
	 * @since 3.0
	 */
	public void get(PropertiesEntity entity);
}
