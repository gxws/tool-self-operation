package com.gxwsxx.tool.properties.destination;

import java.util.Map;
import java.util.Map.Entry;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * spring配置文件的配置值设置
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class SpringPropertiesDestination extends AbstractDestination implements PropertiesDestination {

	/**
	 * @see com.gxwsxx.tool.properties.destination.PropertiesDestination#set(com.gxwsxx.tool.properties.entity.PropertiesEntity)
	 */
	@Override
	public void set(PropertiesEntity entity) {
		Map<String, String> map = keyvalues(entity);
		for (Entry<String, String> en : map.entrySet()) {
			entity.getSpringProps().setProperty(en.getKey(), en.getValue());
		}
	}

}
