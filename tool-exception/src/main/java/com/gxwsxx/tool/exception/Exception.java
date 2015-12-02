package com.gxwsxx.tool.exception;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.gxwsxx.tool.exception.annotation.ExceptionDescription;

/**
 * 处理Exception提示信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class Exception extends java.lang.Exception {

	private static final long serialVersionUID = 664689461085555270L;

	/**
	 * 异常显示信息
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		ExceptionDescription ed = this.getClass().getAnnotation(ExceptionDescription.class);
		if (null == ed) {
			return super.getMessage();
		}
		Field[] fs = fields(this.getClass());
		String msg = field2String(fs);
		if (null == msg) {
			return ed.value();
		} else {
			return ed.value() + msg;
		}
	}

	/**
	 * 获取带有注解的字段
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param cls
	 *            异常类
	 * @return 注解字段
	 * @since 3.0
	 */
	private Field[] fields(Class<?> cls) {
		Field[] selffs = cls.getDeclaredFields();
		Class<?> scls = cls.getSuperclass();
		if (scls.equals(Exception.class)) {
			return selffs;
		} else {
			Field[] superfs = fields(scls);
			Field[] allfs = new Field[selffs.length + superfs.length];
			for (int i = 0; i < selffs.length; i++) {
				allfs[i] = selffs[i];
			}
			for (int i = 0; i < superfs.length; i++) {
				allfs[selffs.length + i] = superfs[i];
			}
			return allfs;
		}
	}

	/**
	 * 解析注解字段，以string格式输出
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param fields
	 *            注解字段
	 * @return 以key=value的string格式
	 * @since 3.0
	 */
	private String field2String(Field[] fields) {
		StringBuilder sb = new StringBuilder();
		String key = "";
		String value = "";
		String methodName = "";
		Method gm = null;
		for (Field f : fields) {
			ExceptionDescription ed = f.getAnnotation(ExceptionDescription.class);
			if (null == ed) {
				continue;
			}
			key = ed.value();
			methodName = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
			try {
				gm = this.getClass().getMethod(methodName);
				Object o = gm.invoke(this);
				if (null == o) {
					value = "null";
				} else {
					value = o.toString();
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				continue;
			}
			sb.append(key);
			sb.append("=");
			sb.append(value);
			sb.append(",");
		}
		if (0 == sb.length()) {
			return null;
		} else {
			return sb.substring(0, sb.length() - 1);
		}
	}

}
