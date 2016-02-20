package com.gxwsxx.restful.exception;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 异常描述，用于Exception的getMessage方法，给出提示信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface ExceptionLabel {

	/**
	 * 描述信息
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 描述信息
	 * @since 3.0
	 */
	public String value();
}
