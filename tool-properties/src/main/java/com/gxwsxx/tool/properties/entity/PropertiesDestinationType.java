package com.gxwsxx.tool.properties.entity;

import com.gxwsxx.tool.properties.destination.PropertiesDestination;
import com.gxwsxx.tool.properties.destination.ServletContextDestination;
import com.gxwsxx.tool.properties.destination.SpringPropertiesDestination;

/**
 * 配置值目的类型
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@SuppressWarnings("unchecked")
public enum PropertiesDestinationType {

	ALL(new Class[] { SpringPropertiesDestination.class, ServletContextDestination.class }),

	SPRING_PROPERTIES(new Class[] { SpringPropertiesDestination.class }),

	SERVLET_CONTEXT(new Class[] { ServletContextDestination.class });

	private Class<? extends PropertiesDestination>[] typeClasses;

	private PropertiesDestinationType(Class<? extends PropertiesDestination>[] typeClasses) {
		this.typeClasses = typeClasses;
	}

	public Class<? extends PropertiesDestination>[] getTypeClasses() {
		return typeClasses;
	}
}
