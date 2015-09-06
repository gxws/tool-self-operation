package com.gxws.tool.link.properties.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxws.tool.common.constant.ProjectConstant;

/**
 * 处理项目全局变量
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class ProjectPropertiesCore implements IPropertiesCore {

	private static final Logger log = LoggerFactory.getLogger(ProjectPropertiesCore.class);

	private ProjectConstant pc;

	private ServletContext sc;

	/**
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param servletContext
	 *            ServletContext对象
	 * @param applicationContext
	 *            spring application context对象
	 * @since 2.1
	 */
	public ProjectPropertiesCore(ServletContext servletContext, ProjectConstant projectConstant) {
		pc = projectConstant;
		sc = servletContext;
		readPropertiesProperties();
	}

	/**
	 * env:从系统环境变量读取，需要在容器（tomcat）启动命令加上java -Dproject.env=dev。<br>
	 * name:项目目录/WEB-INF/web.xml文件，display-name标签。<br>
	 * version:项目目录/WEB-INF/web.xml文件，context-param标签。<br>
	 * ip:项目运行服务器的所有网络接口的IP地址，除127.0.0.1以外，多个ip以","分隔。<br>
	 * port:项目运行web容器(tomcat)启动参数-Dproject.port。<br>
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @since 1.1
	 */
	private void readPropertiesProperties() {
		pc.setEnv(System.getProperty(ProjectConstant.NAME_PROJECT_ENV));
		if (null != sc) {// web项目
			Properties p = new Properties();
			InputStream is;
			try {
				is = new FileInputStream(mavenPropertiesPath());
				p.load(is);
				pc.setName(p.getProperty("artifactId"));
				pc.setVersion(p.getProperty("version"));
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			pc.setContextPath(sc.getContextPath());
		} else {// 非web项目

		}
		pc.setIp(ips());
		pc.setPort(System.getProperty(ProjectConstant.NAME_PROJECT_PORT));
		for (Entry<String, String> en : pc.getAll().entrySet()) {
			log.debug("项目全局变量加载 " + en.getKey() + " = " + en.getValue());
		}
	}

	/**
	 * @see com.gxws.tool.link.properties.core.IPropertiesCore#springProperties(java.util.Properties)
	 */
	@Override
	public void springProperties(Properties props) {
		props.putAll(pc.getAll());
	}

	/**
	 * @see com.gxws.tool.link.properties.core.IPropertiesCore#servletContextProperties(javax.servlet.ServletContext)
	 */
	@Override
	public void servletContextProperties(ServletContext servletContext) {
		if (null != servletContext) {
			servletContext.setAttribute(ProjectConstant.CONTEXT_NAME, pc);
			servletContext.setAttribute("ctx", pc.getContextPath());
		}
	}

	/**
	 * 获取网卡IP地址
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return ip地址，以","分隔
	 * @since 1.0
	 */
	private String ips() {
		Enumeration<NetworkInterface> netInterfaces;
		Pattern p = Pattern.compile("^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$");
		Matcher m = null;
		String ip = null;
		StringBuilder sb = new StringBuilder();
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface nif = netInterfaces.nextElement();
				Enumeration<InetAddress> iparray = nif.getInetAddresses();
				while (iparray.hasMoreElements()) {
					ip = iparray.nextElement().getHostAddress();
					m = p.matcher(ip);
					if (m.matches() && !ip.startsWith("127")) {
						sb.append("," + ip);
					}
				}
			}
		} catch (SocketException e) {
			log.error("读取不到当前服务器IP" + e.getMessage(), e);
			return sb.toString();
		}
		if (0 == sb.length()) {
			return sb.toString();
		} else {
			return sb.substring(1);
		}
	}

	/**
	 * 从maven信息文件获取项目名称和项目版本
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return maven信息文件
	 * @since 1.1
	 */
	private File mavenPropertiesPath() {
		File f = new File(sc.getRealPath("/") + "META-INF/maven");
		f = f.listFiles()[0].listFiles()[0];
		f = f.listFiles((dir, name) -> ("pom.properties".equals(name) ? true : false))[0];
		log.debug("读取项目maven信息的路径 " + f.getAbsolutePath());
		return f;
	}
}
