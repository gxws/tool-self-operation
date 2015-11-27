package com.gxwsxx.tool.link.properties.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.gxwsxx.tool.link.properties.annotation.LinkProperties;
import com.gxwsxx.tool.link.properties.annotation.LinkPropertiesType;
import com.gxwsxx.tool.link.properties.datamodel.LinkPropertiesModel;
import com.gxwsxx.tool.link.properties.reader.LinkPropertiesReader;

/**
 * 配置读取
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@Component("linkPropertiesReadCore")
public class LinkPropertiesReadCore implements ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(LinkPropertiesReadCore.class);

	private ApplicationContext ac;

	/**
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
	}

	/**
	 * 设置静态属性类
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param cls
	 *            静态属性的类
	 * @return model集合
	 * @since 3.0
	 */
	private Set<LinkPropertiesModel> putStatic(Class<?> cls) {
		Set<LinkPropertiesModel> set = new HashSet<>();
		LinkProperties lp = null;
		for (Field field : cls.getFields()) {// 常量字段
			lp = field.getAnnotation(LinkProperties.class);
			if (null != lp && Modifier.isStatic(field.getModifiers())) {// 常量字段有注解&&是static
				set.add(put(null, cls, field, lp));
			} else {// 常量字段没有注解||不是static
				continue;
			}
		}
		return set;
	}

	/**
	 * 设置非静态属性对象
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param o
	 *            非静态属性对象
	 * @return model集合
	 * @since 3.0
	 */
	private Set<LinkPropertiesModel> putObject(Object o) {
		Set<LinkPropertiesModel> set = new HashSet<>();
		LinkProperties lp = null;
		for (Field field : o.getClass().getDeclaredFields()) {
			lp = field.getAnnotation(LinkProperties.class);
			if (null != lp) {
				set.add(put(o, o.getClass(), field, lp));
			}
		}
		return set;
	}

	/**
	 * 设置相应model的属性
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param target
	 *            非静态属性对象
	 * @param cls
	 *            属性类
	 * @param field
	 *            属性字段
	 * @param lp
	 *            属性注解
	 * @return 相应model
	 * @since 3.0
	 */
	private LinkPropertiesModel put(Object target, Class<?> cls, Field field, LinkProperties lp) {
		LinkPropertiesModel model = new LinkPropertiesModel();
		model.setClazz(cls);
		model.setFieldName(field.getName());
		if (lp.value().isEmpty()) {// 注解没有设置value
			model.setKey(field.getName());
		} else {// 注解设置value
			model.setKey(lp.value());
		}
		model.setType(lp.type());
		model.setAliases(lp.aliases());
		model.setTarget(target);
		model.setDefaultValue(lp.defaultValue());
		return model;
	}

	/**
	 * 读取静态属性类，通过注解读取相应配置信息
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param constantClassnames
	 *            常量类列表
	 * @return 所有配置项键值对
	 * @since 3.0
	 */
	public Map<String, String> read(List<String> constantClassnames) {
		Map<String, String> map = new HashMap<>();
		if (null != constantClassnames && !constantClassnames.isEmpty()) {// 常量类列表
			Class<?> cls = null;
			ClassLoader cl = this.getClass().getClassLoader();
			for (String classname : constantClassnames) {// 常量类
				try {
					cls = Class.forName(classname, false, cl);
				} catch (ClassNotFoundException e) {
					log.warn("没有找到类'" + classname + "'");
					continue;
				}
				map.putAll(value(putStatic(cls)));
			}
		}
		return map;
	}

	/**
	 * 读取非静态属性类对象，通过注解读取相应配置信息
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param o
	 *            非静态属性对象
	 * @return 所有配置项键值对
	 * @since 3.0
	 */
	public Map<String, String> read(Object o) {
		return value(putObject(o));
	}

	/**
	 * 根据注解配置内容，读取相应配置的值，并填入相应对象或类中
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param models
	 *            model集合
	 * @return 所有配置项键值对
	 * @since 3.0
	 */
	private Map<String, String> value(Set<LinkPropertiesModel> models) {
		Map<String, String> map = new HashMap<>();
		LinkPropertiesReader reader = null;
		Field field = null;
		Class<?> cls = null;
		for (LinkPropertiesModel model : models) {
			String value = null;
			String valueTmp = null;
			cls = model.getClazz();
			try {
				field = cls.getDeclaredField(model.getFieldName());
			} catch (NoSuchFieldException | SecurityException e1) {
				log.warn("字段'" + model.getFieldName() + "'在类'" + cls.getName() + "'中没有找到");
				continue;
			}
			switch (model.getType()) {
			case DEFAULT:// 默认读取，注解没有指定读取范围
				LinkPropertiesType[] types = LinkPropertiesType.values();
				for (int i = 2; i < types.length; i++) {
					reader = (LinkPropertiesReader) ac.getBean(types[i].getReaderName());
					valueTmp = reader.valueString(model.getKey());
					if (null != valueTmp) {// 覆盖之前读取的值
						value = valueTmp;
					}
				}
				break;
			case NONE:// 不读取
				break;
			default:// 注解指定读取范围
				reader = (LinkPropertiesReader) ac.getBean(model.getType().getReaderName());
				value = reader.valueString(model.getKey());
			}
			if (null == value) {// 设置默认值
				log.warn("没有读取到注解指定读取范围的属性：" + model.getKey());
				value = model.getDefaultValue();
			}
			if (null == model.getTarget()) {// 静态类常量
				try {
					field.set(null, value);
				} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
					log.warn("字段'" + cls.getName() + "." + field.getName() + "'设置值" + value + "错误", e);
					continue;
				}
			} else {// 非静态常量对象
				String methodName = "set";
				if (1 < field.getName().length()) {
					methodName = methodName + field.getName().substring(0, 1).toUpperCase()
							+ field.getName().substring(1);
				} else {
					methodName = methodName + field.getName().substring(0, 1).toUpperCase();
				}
				try {
					Method setMethod = cls.getMethod(methodName, String.class);
					setMethod.invoke(model.getTarget(), value);
				} catch (NoSuchMethodException | SecurityException e) {
					log.warn("字段set方法'" + methodName + "'在类'" + cls.getName() + "'中没有找到", e);
					continue;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					log.warn("字段set方法'" + methodName + "'在类'" + cls.getName() + "'中赋值错误", e);
					continue;
				}
			}
			map.put(model.getKey(), value);
			map.put(field.getName(), value);
			map.put(cls.getName() + "." + field.getName(), value);
			for (String s : model.getAliases()) {
				if (!"".equals(s)) {
					map.put(s, value);
				}
			}
		}
		return map;
	}

}
