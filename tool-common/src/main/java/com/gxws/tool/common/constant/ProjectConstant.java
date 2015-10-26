package com.gxws.tool.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目信息，属性值<br>
 * 2.1版本以后，需要定义为spring的bean。<br>
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class ProjectConstant {

	// /**
	// * 线上环境定义。env的值等于set内的其中一项时，将启用以下功能：<br>
	// * 远程配置读取功能，项目tool-link-properties。<br>
	// * 远程日志存储功能，项目tool-logging。<br>
	// */
	// public final static Set<String> onlineEnvSet = new HashSet<String>(
	// Arrays.asList(new String[] { "dev", "test", "real" }));

	public static final String CONTEXT_NAME = "project";

	private String name = "name_default";// 项目名

	private String env = "env_default";// 项目环境

	private String version = "version_default";// 项目版本

	private String ip = "ip_default";// 项目运行网卡IP地址

	private String port = "port_default";// 项目运行实例端口号

	private String contextPath = "contextPath_default";// 项目的contextPath

	public static final String NAME_PROJECT_NAME = "project.name";

	public static final String NAME_PROJECT_ENV = "project.env";

	public static final String NAME_PROJECT_VERSION = "project.version";

	public static final String NAME_PROJECT_IP = "project.ip";

	public static final String NAME_PROJECT_PORT = "project.port";

	public static final String NAME_PROJECT_CONTEXT_PATH = "project.context.path";

	// private Map<String, String> map = new HashMap<>();

	// private static ProjectConstant self;

	// private ProjectConstant() {
	//
	// }
	//
	// public static ProjectConstant instance() {
	// if (null == self) {
	// self = new ProjectConstant();
	// }
	// return self;
	// }

	// public void put(String key, String value) {
	// map.put(key, value);
	// }
	//
	// public String get(String key) {
	// return map.get(key);
	// }

	// public Map<String, String> getAll() {
	// return map;
	// }

	/**
	 * Map方式获取项目信息
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 返回Map键值项目信息
	 * @since 2.1
	 */
	public Map<String, String> getAll() {
		return new HashMap<String, String>() {
			private static final long serialVersionUID = 9112890344243924405L;
			{
				put(NAME_PROJECT_NAME, name);
				put(NAME_PROJECT_ENV, env);
				put(NAME_PROJECT_VERSION, version);
				put(NAME_PROJECT_IP, ip);
				put(NAME_PROJECT_PORT, port);
				put(NAME_PROJECT_CONTEXT_PATH, contextPath);
			}
		};
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (null != name && !name.isEmpty()) {
			this.name = name;
		}
		// map.put(NAME_PROJECT_NAME, this.name);
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		if (null != env && !env.isEmpty()) {
			this.env = env;
		}
		// map.put(NAME_PROJECT_ENV, this.env);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		if (null != version && !version.isEmpty()) {
			this.version = version;
		}
		// map.put(NAME_PROJECT_VERSION, this.version);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		if (null != ip && !ip.isEmpty()) {
			this.ip = ip;
		}
		// map.put(NAME_PROJECT_IP, this.ip);
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		if (null != port && !port.isEmpty()) {
			this.port = port;
		}
		// map.put(NAME_PROJECT_PORT, this.port);
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		if (null != contextPath && !contextPath.isEmpty()) {
			this.contextPath = contextPath;
		}
		// map.put(NAME_PROJECT_CONTEXT_PATH, this.contextPath);
	}

}
