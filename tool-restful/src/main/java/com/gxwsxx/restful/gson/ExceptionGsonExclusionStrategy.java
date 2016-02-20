package com.gxwsxx.restful.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * 排除Exception中的<br>
 * suppressedExceptions<br>
 * stackTrace<br>
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class ExceptionGsonExclusionStrategy implements ExclusionStrategy {

	/**
	 * @see com.google.gson.ExclusionStrategy#shouldSkipField(com.google.gson.FieldAttributes)
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return ("suppressedExceptions".equals(f.getName()) || "stackTrace".equals(f.getName()));
	}

	/**
	 * @see com.google.gson.ExclusionStrategy#shouldSkipClass(java.lang.Class)
	 */
	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return (StackTraceElement[].class.equals(clazz));
	}

}
