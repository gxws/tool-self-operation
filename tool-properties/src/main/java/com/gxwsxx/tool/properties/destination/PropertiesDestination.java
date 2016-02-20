package com.gxwsxx.tool.properties.destination;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * 配置设置目的
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public interface PropertiesDestination {

	/**
	 * 将配置值设置到目的
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param entity
	 *            配置信息
	 * @since 3.0
	 */
	public void set(PropertiesEntity entity);
}
