package com.gxwsxx.restful.gson;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 创建gson对象
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class RestfulGsonFactoryBean implements FactoryBean<Gson>, InitializingBean {

	private Gson gson;

	private String dateFormatPattern;

	private Map<Class<?>, Object> specificTypeAdapter;

	private Map<Class<?>, Object> hierarchyTypeAdapter;

	private ExclusionStrategy[] exclusionStrategies;

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		GsonBuilder gb = new GsonBuilder();
		if (null != dateFormatPattern) {
			gb.setDateFormat(dateFormatPattern);
		}
		if (null != specificTypeAdapter && !specificTypeAdapter.isEmpty()) {
			for (Entry<Class<?>, Object> en : specificTypeAdapter.entrySet()) {
				gb.registerTypeAdapter(en.getKey(), en.getValue());
			}
		}
		if (null != hierarchyTypeAdapter && !hierarchyTypeAdapter.isEmpty()) {
			for (Entry<Class<?>, Object> en : hierarchyTypeAdapter.entrySet()) {
				gb.registerTypeHierarchyAdapter(en.getKey(), en.getValue());
			}
		}
		if (null != exclusionStrategies && 0 != exclusionStrategies.length) {
			gb.setExclusionStrategies(exclusionStrategies);
		}
		this.gson = gb.create();
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public Gson getObject() throws Exception {
		return this.gson;
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return Gson.class;
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}

	public void setSpecificTypeAdapter(Map<Class<?>, Object> specificTypeAdapter) {
		this.specificTypeAdapter = specificTypeAdapter;
	}

	public void setHierarchyTypeAdapter(Map<Class<?>, Object> hierarchyTypeAdapter) {
		this.hierarchyTypeAdapter = hierarchyTypeAdapter;
	}

	public void setExclusionStrategies(ExclusionStrategy[] exclusionStrategies) {
		this.exclusionStrategies = exclusionStrategies;
	}

}
