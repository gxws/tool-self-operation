package com.gxwsxx.tool.properties.destination;

import java.util.HashMap;
import java.util.Map;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * Destination 的抽象功能
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public abstract class AbstractDestination {

	/**
	 * 根据Entity 对象的信息，转换为Map
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param entity
	 *            配置信息
	 * @return key value map
	 * @since 3.0
	 */
	protected Map<String, String> keyvalues(PropertiesEntity entity) {
		Map<String, String> map = new HashMap<>();
		if (null == entity.getValue()) {
			return map;
		}
		map.put(entity.getKey(), entity.getValue());// 按定义的key
		if (null != entity.getAliases() || 0 != entity.getAliases().length) {// 按定义的aliases
			for (String alias : entity.getAliases()) {
				map.put(alias, entity.getValue());
			}
		}
		return map;
	}
}
