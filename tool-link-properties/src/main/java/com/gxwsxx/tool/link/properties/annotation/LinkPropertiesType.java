package com.gxwsxx.tool.link.properties.annotation;

/**
 * 指定配置读取范围类型
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public enum LinkPropertiesType {

	DEFAULT(null, "默认方式"),

	NONE(null, "不读取"),

	SYSTEM_ENV("linkPropertiesSystemEnvReader", "系统环境变量"),

	JAVA_OPTS("linkPropertiesJavaOptsReader", "jvm启动参数"),

	FILE_PROJECT("linkPropertiesFileProjectReader", "文件project.properties"),

	FILE_LINK("linkPropertiesFileLinkReader", "文件link.properties"),

	ZOOKEEPER("linkPropertiesZookeeperReader", "zookeeper服务器");

	private String readerName;
	private String description;

	private LinkPropertiesType(String readerName, String description) {
		this.readerName = readerName;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getReaderName() {
		return readerName;
	}

}
