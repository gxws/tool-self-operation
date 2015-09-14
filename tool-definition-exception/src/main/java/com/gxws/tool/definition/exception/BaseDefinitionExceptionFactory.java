package com.gxws.tool.definition.exception;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * BaseDefinitionExceptionFactory类需要定义为spring bean。<br>
 * 使用方式，业务对象需要注入BaseDefinitionExceptionFactory对象。<br>
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 2.1
 */
public class BaseDefinitionExceptionFactory implements ApplicationContextAware {

	private ApplicationContext ac;

	/**
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ac = applicationContext;
	}

	public <T extends BaseDefinitionException> T instance(Class<? extends BaseDefinitionException> cls) {
		return (T) ac.getBean(cls);
	}
}
