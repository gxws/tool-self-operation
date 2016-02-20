package com.gxwsxx.tool.properties.entity;

import com.gxwsxx.tool.properties.source.FileSource;
import com.gxwsxx.tool.properties.source.JavaOptsSource;
import com.gxwsxx.tool.properties.source.PropertiesSource;
import com.gxwsxx.tool.properties.source.ServletConetxtSource;
import com.gxwsxx.tool.properties.source.SystemEnvSource;
import com.gxwsxx.tool.properties.source.ZookeeperSource;

/**
 * 指定配置读取范围类型
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public enum PropertiesSourceType {

	MANUALLY(null, "不读取"),

	SYSTEM_ENV(SystemEnvSource.class, "操作系统环境变量"),

	JAVA_OPTS(JavaOptsSource.class, "jvm启动参数"),

	FILE_PROJECT(FileSource.class, "文件classpath:project.properties"),

	SERVLET_CONTEXT(ServletConetxtSource.class, "项目的ServletConetxt信息"),

	ZOOKEEPER(ZookeeperSource.class, "zookeeper服务器");

	private String description;

	private Class<? extends PropertiesSource> typeClass;

	private PropertiesSourceType(Class<? extends PropertiesSource> typeClass, String description) {
		this.typeClass = typeClass;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Class<? extends PropertiesSource> getTypeClass() {
		return typeClass;
	}

}
