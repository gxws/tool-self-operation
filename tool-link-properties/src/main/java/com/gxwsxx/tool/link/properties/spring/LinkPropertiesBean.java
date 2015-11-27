package com.gxwsxx.tool.link.properties.spring;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.web.context.ServletContextAware;

import com.gxwsxx.tool.link.properties.core.LinkPropertiesReadCore;
import com.gxwsxx.tool.link.properties.core.LinkPropertiesWriteCore;
import com.gxwsxx.tool.link.properties.datamodel.ProjectInfo;

/**
 * 以spring bean 方式读取link properties配置参数<br>
 * 对配置的constant class 赋值<br>
 * 将读取的配置信息加入servlet context<br>
 * 将读取的配置信息加入spring application context<br>
 * 将ProjectConstant对象添加到servlet context<br>
 * 将ProjectConstant对象添加到spring application context<br>
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class LinkPropertiesBean extends PropertyPlaceholderConfigurer implements ServletContextAware {

	private static final Logger log = LoggerFactory.getLogger(LinkPropertiesBean.class);

	private List<String> constantClassnames;

	private ServletContext servletContext;

	private ProjectInfo projectInfo;

	private LinkPropertiesReadCore linkPropertiesReadCore;

	private LinkPropertiesWriteCore linkPropertiesWriteCore;

	/**
	 * 装载静态变量属性和值到spring application context和servlet context
	 * 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.beans.factory.config.ConfigurableListableBeanFactory,
	 *      java.util.Properties)
	 */
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		if (null == projectInfo) {
			log.warn("没有在spring application context中找到ProjectConstant的bean");
		} else {
			ips(projectInfo);
			if (null != servletContext) {
				projectInfo.setServletContextPath(servletContext.getContextPath());
			}
		}
		Map<String, String> pimap = linkPropertiesReadCore.read(projectInfo);
		for (Entry<String, String> en : pimap.entrySet()) {
			log.debug("项目属性加载：" + en.getKey() + " = " + en.getValue());
		}
		Map<String, String> map = linkPropertiesReadCore.read(constantClassnames);
		for (Entry<String, String> en : map.entrySet()) {
			log.debug("自定义属性加载：" + en.getKey() + " = " + en.getValue());
		}
		pimap.putAll(map);
		linkPropertiesWriteCore.write(props, map);
		linkPropertiesWriteCore.write(servletContext, map);
		servletContext.setAttribute(ProjectInfo.ATTRIBUTE_NAME, projectInfo);
		super.processProperties(beanFactoryToProcess, props);
	}

	/**
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 获取静态变量的类全名
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param constantClassnames
	 *            静态变量的类全名，带包名
	 * @since 1.0
	 */
	public void setConstantClassnames(List<String> constantClassnames) {
		this.constantClassnames = constantClassnames;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	/**
	 * 获取网卡IP地址
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return ip地址，以","分隔
	 * @since 1.0
	 */
	private void ips(ProjectInfo projectInfo) {
		Enumeration<NetworkInterface> netInterfaces;
		Pattern p = Pattern.compile("^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$");
		Matcher m = null;
		String ip = null;
		StringBuffer sb = new StringBuffer();
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
			if (0 == sb.length()) {
				throw new Exception();
			} else {
				projectInfo.setIp(sb.substring(1));
			}
		} catch (Exception e) {
			log.error("读取不到当前服务器IP", e);
		}
	}

	public void setLinkPropertiesReadCore(LinkPropertiesReadCore linkPropertiesReadCore) {
		this.linkPropertiesReadCore = linkPropertiesReadCore;
	}

	public void setLinkPropertiesWriteCore(LinkPropertiesWriteCore linkPropertiesWriteCore) {
		this.linkPropertiesWriteCore = linkPropertiesWriteCore;
	}

}
