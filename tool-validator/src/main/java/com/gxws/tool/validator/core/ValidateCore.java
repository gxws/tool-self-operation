package com.gxws.tool.validator.core;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxws.tool.validator.exception.BoParamValidateException;

/**
 * 验证参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class ValidateCore {

	private static final Logger log = LoggerFactory.getLogger(ValidateCore.class);

	/**
	 * 验证bo调用输入参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param os
	 *            参数对象数组
	 * @param methodName
	 *            bo对象方法名
	 * @param targetClass
	 *            bo对象的类
	 * @throws BoParamValidateException
	 *             bo参数验证异常
	 * @since 1.0
	 */
	public void validate(Object[] os, String methodName, Class<?> targetClass) throws BoParamValidateException {
		log.debug("验证输入参数 = " + targetClass.getName() + "." + methodName);
		StringBuffer sb = new StringBuffer();
		for (Object o : os) {
			if (null == o) {
				continue;
			}
			Class<?> oClass = (Class<?>) o.getClass();
			Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
			Set<?> cv = validator.validate(oClass.cast(o), group(methodName, targetClass));
			if (0 != cv.size()) {
				sb.append("[" + oClass.getName());
				Iterator<?> it = cv.iterator();
				while (it.hasNext()) {
					sb.append("(");
					ConstraintViolation<?> c = (ConstraintViolation<?>) it.next();
					sb.append(c.getMessage());
					sb.append(")");
				}
				sb.append("]");
			}
		}
		if (sb.length() > 0) {
			BoParamValidateException e = new BoParamValidateException();
			e.setMsg(sb.toString());
			throw e;
		}
	}

	/**
	 * 获取验证参数的group
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param methodName
	 *            bo对象的方法名
	 * @param targetClass
	 *            bo对象的类
	 * @return hibernate validator指定的group类
	 * @since 1.0
	 */
	private Class<?> group(String methodName, Class<?> targetClass) {
		String groupClassName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
		for (Class<?> clz : targetClass.getInterfaces()) {
			for (Class<?> subclz : clz.getDeclaredClasses()) {
				if (subclz.getName().endsWith(groupClassName)) {
					return subclz;
				}
			}
		}
		return Default.class;
	}
}
