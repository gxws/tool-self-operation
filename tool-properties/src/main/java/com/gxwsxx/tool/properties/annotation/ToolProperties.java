package com.gxwsxx.tool.properties.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gxwsxx.tool.properties.entity.PropertiesDestinationType;
import com.gxwsxx.tool.properties.entity.PropertiesSourceType;

/**
 * 读取配置项的注解<br>
 * 需要添加在定义为public statis String 的field上
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ToolProperties {

	/**
	 * 指定link-properties参数配置源要读取值的参数名。<br>
	 * 同时该参数名在spring properties中使用。<br>
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 参数名
	 * @since 1.0
	 */
	public String key() default "";

	/**
	 * 别名<br>
	 * 用于设置该属性在其他context中（如servletContext）的键的别名，使用别名也能够读取该属性的值。<br>
	 * 注意：不同属性设置了相同的别名，会被覆盖。<br>
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 读取键的别名
	 * @since 3.0
	 */
	public String[] aliases() default "";

	/**
	 * 属性值来源类型<br>
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 属性值来源类型
	 * @since 3.0
	 */
	public PropertiesSourceType source() default PropertiesSourceType.MANUALLY;

	/**
	 * 属性值目的类型<br>
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 属性值目的类型
	 * @since 3.0
	 */
	public PropertiesDestinationType destination() default PropertiesDestinationType.ALL;

}
