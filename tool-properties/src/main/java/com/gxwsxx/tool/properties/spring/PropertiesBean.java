package com.gxwsxx.tool.properties.spring;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.gxwsxx.tool.properties.entity.ProjectInfo;
import com.gxwsxx.tool.properties.handler.PropertiesHandler;

/**
 * 以spring bean 方式读取link properties配置参数<br>
 * 对配置的constant class 赋值<br>
 * 将读取的配置信息加入servlet context<br>
 * 将读取的配置信息加入spring application context<br>
 * 将ProjectConstant对象添加到servlet context<br>
 * 将ProjectConstant对象添加到spring application context<br>
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class PropertiesBean extends PropertyPlaceholderConfigurer {

	private static final Logger log = LoggerFactory.getLogger(PropertiesBean.class);

	private PropertiesConstantBean propertiesConstant;

	private ProjectInfo projectEntity;

	private PropertiesHandler readerHandler;

	/**
	 * 装载静态变量属性和值到spring application context和servlet context
	 * 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.beans.factory.config.ConfigurableListableBeanFactory,
	 *      java.util.Properties)
	 */
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
	}

	public void setProjectEntity(ProjectInfo ProjectEntity) {
		this.projectEntity = ProjectEntity;
	}

	public void setPropertiesConstant(PropertiesConstantBean propertiesConstant) {
		this.propertiesConstant = propertiesConstant;
	}

}
