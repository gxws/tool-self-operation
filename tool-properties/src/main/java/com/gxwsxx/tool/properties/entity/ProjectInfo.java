package com.gxwsxx.tool.properties.entity;

import com.gxwsxx.tool.properties.annotation.ToolProperties;

/**
 * 项目信息，属性值<br>
 * 替代3.0之前的com.gxws.tool.common.constant.ProjectConstant<br>
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class ProjectInfo {

	public final static String ATTRIBUTE_NAME = "PROJECT_ENTITY";

	@ToolProperties(key = "project.name", source = PropertiesSourceType.FILE_PROJECT, aliases = "projectName")
	private String name = "no_name";// 项目名

	@ToolProperties(key = "project.version", source = PropertiesSourceType.FILE_PROJECT, aliases = "projectVersion")
	private String version = "no_version";// 项目版本

	@ToolProperties(key = "project.env", source = PropertiesSourceType.JAVA_OPTS, aliases = "projectEnv")
	private String env = "dev";// 项目环境

	@ToolProperties(key = "project.port", source = PropertiesSourceType.JAVA_OPTS, aliases = "projectPort")
	private String port = "8080";// 项目运行实例端口号

	@ToolProperties(key = "project.host", source = PropertiesSourceType.JAVA_OPTS, aliases = "projectHost")
	private String host = "localhost";// 项目运行实例端口号

	@ToolProperties(key = "project.ip", source = PropertiesSourceType.MANUALLY, aliases = "projectIp")
	private String ip = "127.0.0.1";// 项目运行网卡IP地址

	@ToolProperties(key = "project.hostname", source = PropertiesSourceType.MANUALLY, aliases = "projectHostname")
	private String hostname = "hostname";// 项目运行的主机名

	@ToolProperties(key = "project.servlet.context.path", source = PropertiesSourceType.SERVLET_CONTEXT, aliases = "ctx")
	private String servletContextPath = "/";// 项目的contextPath

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getServletContextPath() {
		return servletContextPath;
	}

	public void setServletContextPath(String servletContextPath) {
		this.servletContextPath = servletContextPath;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
}
