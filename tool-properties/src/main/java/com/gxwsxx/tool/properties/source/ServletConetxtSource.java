package com.gxwsxx.tool.properties.source;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * 读取当前项目servlet context相关信息的reader
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class ServletConetxtSource implements PropertiesSource, ServletContextAware {

	private final static Logger log = LoggerFactory.getLogger(ServletConetxtSource.class);

	private ServletContext servletContext;

	/**
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * @see com.gxwsxx.tool.properties.source.PropertiesSource#get(com.gxwsxx.tool.properties.entity.PropertiesEntity)
	 */
	@Override
	public void get(PropertiesEntity entity) {
		if (null == servletContext) {
			log.debug("没有获取servlet context对象");
		}
		String value = null;
		switch (entity.getKey()) {
		case "project.servlet.context.path":
			value = servletContext.getContextPath();
			break;
		default:
			break;
		}
		if (null == value) {
			log.error("没有能读取到属性" + entity.getKey());
		} else {
			entity.setValue(value);
		}
	}

}
