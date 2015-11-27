package com.gxwsxx.tool.link.properties.datamodel;

import org.springframework.stereotype.Component;

import com.gxwsxx.tool.link.properties.annotation.LinkProperties;
import com.gxwsxx.tool.link.properties.annotation.LinkPropertiesType;

/**
 * 项目信息，属性值<br>
 * 替代3.0之前的com.gxws.tool.common.constant.ProjectConstant<br>
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@Component("projectInfo")
public class ProjectInfo {
	
	public final static String ATTRIBUTE_NAME = "PROJECT_INFO";

	@LinkProperties(value = "project.name", type = LinkPropertiesType.FILE_PROJECT, defaultValue = "name_default", aliases = "projectName")
	private String name;// 项目名

	@LinkProperties(value = "project.version", type = LinkPropertiesType.FILE_PROJECT, defaultValue = "version_default", aliases = "projectVersion")
	private String version;// 项目版本

	@LinkProperties(value = "project.zookeeper.hosts", type = LinkPropertiesType.FILE_LINK, defaultValue = "loacalhost:2181", aliases = "projectZookeeperHosts")
	private String zookeeperHosts;// zookeeper访问地址

	@LinkProperties(value = "project.env", type = LinkPropertiesType.JAVA_OPTS, defaultValue = "dev", aliases = "projectEnv")
	private String env;// 项目环境

	@LinkProperties(value = "project.port", type = LinkPropertiesType.JAVA_OPTS, defaultValue = "8080", aliases = "projectPort")
	private String port;// 项目运行实例端口号

	@LinkProperties(value = "project.ip", type = LinkPropertiesType.NONE, defaultValue = "127.0.0.1", aliases = "projectIp")
	private String ip;// 项目运行网卡IP地址

	@LinkProperties(value = "project.host", type = LinkPropertiesType.NONE, defaultValue = "hostname", aliases = "projectHost")
	private String host;// 项目运行的主机名

	@LinkProperties(value = "project.servlet.context.path", type = LinkPropertiesType.NONE, defaultValue = "/", aliases = "ctx")
	private String servletContextPath;// 项目的contextPath

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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getServletContextPath() {
		return servletContextPath;
	}

	public void setServletContextPath(String servletContextPath) {
		this.servletContextPath = servletContextPath;
	}
}
