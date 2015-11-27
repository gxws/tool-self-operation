package com.gxwsxx.tool.link.properties.datamodel;

import com.gxwsxx.tool.link.properties.annotation.LinkPropertiesType;

/**
 * @author zhuwl120820@gxwsxx.com
 * @param <T>
 * @since 3.0
 */
public class LinkPropertiesModel {
	// 读取的配置名
	private String key;

	// 配置类字段名
	private String fieldName;

	// 配置对象的Class
	private Class<?> clazz;

	// 默认值
	private String defaultValue;

	// 配置对象
	private Object target;

	// 配置键别名
	private String[] aliases;

	// 配置读取类型
	private LinkPropertiesType type;

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

	public LinkPropertiesType getType() {
		return type;
	}

	public void setType(LinkPropertiesType type) {
		this.type = type;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
