package com.gxwsxx.tool.properties.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.gxwsxx.tool.properties.annotation.ToolProperties;
import com.gxwsxx.tool.properties.destination.ObjectDestination;
import com.gxwsxx.tool.properties.destination.PropertiesDestination;
import com.gxwsxx.tool.properties.destination.StaticFieldDestination;
import com.gxwsxx.tool.properties.entity.PropertiesEntity;
import com.gxwsxx.tool.properties.source.PropertiesSource;

/**
 * 带注解的配置项相关信息读取
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class PropertiesHandler implements InitializingBean, ServletContextAware {

	private static final Logger log = LoggerFactory.getLogger(PropertiesHandler.class);

	private ServletContext servletContext;

	private Set<PropertiesSource> propertiesSources;

	private Map<Class<? extends PropertiesSource>, PropertiesSource> sourcesMap;

	private Set<PropertiesDestination> propertiesDestination;

	private Map<Class<? extends PropertiesDestination>, PropertiesDestination> destinationMap;

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		sourcesMap = new HashMap<>();
		destinationMap = new HashMap<>();
		if (null != propertiesSources) {
			for (PropertiesSource ps : propertiesSources) {
				sourcesMap.put(ps.getClass(), ps);
			}
		}
		if (null != propertiesDestination) {
			for (PropertiesDestination pd : propertiesDestination) {
				destinationMap.put(pd.getClass(), pd);
			}
		}
	}

	/**
	 * 读取传入的静态变量类信息，转换成PropertiesEntity对象
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param classnames
	 *            静态变量类名
	 * @return PropertiesEntity对象
	 * @since 3.0
	 */
	public Set<PropertiesEntity> getEntities(List<String> classnames, Properties props) {
		Set<PropertiesEntity> set = new HashSet<>();
		PropertiesEntity pe = null;
		ToolProperties lp = null;
		if (null != classnames && !classnames.isEmpty()) {// 常量类列表
			Class<?> cls = null;
			ClassLoader cl = this.getClass().getClassLoader();
			for (String classname : classnames) {// 常量类
				try {
					cls = Class.forName(classname, false, cl);
				} catch (ClassNotFoundException e) {
					log.warn("没有找到类'" + classname + "'");
					continue;
				}
				for (Field field : cls.getFields()) {// 获取字段
					lp = field.getAnnotation(ToolProperties.class);
					if (null != lp && Modifier.isStatic(field.getModifiers())) {// 常量字段有注解&&是static
						pe = new PropertiesEntity();
						pe.setAliases(lp.aliases());
						pe.setKey(lp.key());
						pe.setSource(sourcesMap.get(lp.source().getTypeClass()));
						for (Class<? extends PropertiesDestination> tpyeCls : lp.destination().getTypeClasses()) {
							pe.addDestination(destinationMap.get(tpyeCls));
						}
						pe.setServletContext(servletContext);
						pe.setSpringProps(props);

						pe.addDestination(destinationMap.get(StaticFieldDestination.class));
						pe.setClazz(cls);
						pe.setFieldName(field.getName());
						set.add(pe);
					} else {// 常量字段没有注解||不是static
						continue;
					}
				}
			}
		}
		return set;
	}

	/**
	 * 读取非静态变量类信息，转换成PropertiesEntity对象
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param obj
	 *            非静态变量类对象
	 * @return PropertiesEntity对象
	 * @since 3.0
	 */
	public Set<PropertiesEntity> getEntities(Object obj, Properties props) {
		Set<PropertiesEntity> set = new HashSet<>();
		PropertiesEntity pe = null;
		ToolProperties lp = null;
		for (Field field : obj.getClass().getDeclaredFields()) {// 获取定义的字段
			lp = field.getAnnotation(ToolProperties.class);
			if (null != lp) {// 有注解
				pe = new PropertiesEntity();
				pe.setAliases(lp.aliases());
				pe.setKey(lp.key());
				pe.setSource(sourcesMap.get(lp.source().getTypeClass()));
				for (Class<? extends PropertiesDestination> tpyeCls : lp.destination().getTypeClasses()) {
					pe.addDestination(destinationMap.get(tpyeCls));
				}
				pe.setServletContext(servletContext);
				pe.setSpringProps(props);

				pe.addDestination(destinationMap.get(ObjectDestination.class));
				pe.setFieldName(field.getName());
				pe.setTarget(obj);
				set.add(pe);
			}
		}
		return set;
	}

	/**
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setPropertiesSources(Set<PropertiesSource> propertiesSources) {
		this.propertiesSources = propertiesSources;
	}

	public void setPropertiesDestination(Set<PropertiesDestination> propertiesDestination) {
		this.propertiesDestination = propertiesDestination;
	}

}
