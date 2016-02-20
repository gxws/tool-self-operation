package com.gxwsxx.tool.properties.entity;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import com.gxwsxx.tool.properties.destination.PropertiesDestination;
import com.gxwsxx.tool.properties.source.PropertiesSource;

/**
 * 配置项相关信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class PropertiesEntity {
	// 读取的配置名
	private String key;

	// 配置类字段名
	private String fieldName;

	// 配置对象的Class
	private Class<?> clazz;

	// 默认值
	private String value;

	// 配置对象
	private Object target;

	// 配置键别名
	private String[] aliases;

	// servlet context对象
	private ServletContext servletContext;

	// spring 配置引用的properties对象
	private Properties springProps;

	// 配置值来源类型
	private PropertiesSource source;

	// 配置值目标
	private Set<PropertiesDestination> destinations;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public String[] getAliases() {
		return aliases;
	}

	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isStatic() {
		if (null == target) {
			return true;
		} else {
			return false;
		}
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public Properties getSpringProps() {
		return springProps;
	}

	public void setSpringProps(Properties springProps) {
		this.springProps = springProps;
	}

	public PropertiesSource getSource() {
		return source;
	}

	public void setSource(PropertiesSource source) {
		this.source = source;
	}

	public Set<PropertiesDestination> getDestinations() {
		return destinations;
	}

	public void addDestination(PropertiesDestination dest) {
		if (null == destinations) {
			destinations = new HashSet<>();
		}
		destinations.add(dest);
	}

}
