package com.gxws.tool.link.properties.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.web.context.ServletContextAware;

import com.gxws.tool.common.constant.ProjectConstant;
import com.gxws.tool.link.properties.core.IPropertiesCore;
import com.gxws.tool.link.properties.core.LinkPropertiesCore;
import com.gxws.tool.link.properties.core.ProjectPropertiesCore;

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

	private ProjectConstant projectConstant;

	/**
	 * 装载静态变量属性和值到spring application context和servlet context
	 * 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.beans.factory.config.ConfigurableListableBeanFactory,
	 *      java.util.Properties)
	 */
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		List<IPropertiesCore> corelist = new ArrayList<>();
		if (null == projectConstant) {
			log.error("没有在spring application context中找到ProjectConstant的bean");
			return;
		}
		// 处理项目全局变量
		corelist.add(new ProjectPropertiesCore(servletContext, projectConstant));
		// 处理项目自定义变量
		corelist.add(new LinkPropertiesCore(constantClassnames, projectConstant));
		for (IPropertiesCore core : corelist) {
			core.servletContextProperties(servletContext);
			core.springProperties(props);
		}
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

	/**
	 * 获取系统变量对象
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param projectConstant
	 *            项目系统变量对象，引用spring bean
	 * @since 2.1
	 */
	public void setProjectConstant(ProjectConstant projectConstant) {
		this.projectConstant = projectConstant;
	}

}
