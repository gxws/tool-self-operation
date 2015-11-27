package com.gxwsxx.tool.link.properties.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 读取配置项的注解<br>
 * 需要添加在定义为public statis String 的field上
 * 
 * @author zhuwl120820@gxwsxx.com 2015年2月10日上午11:27:25
 *
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface LinkProperties {

	/**
	 * 指定link-properties参数配置源要读取值的参数名。<br>
	 * 同时该参数名在spring properties中使用。<br>
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 参数名
	 * @since 1.0
	 */
	public String value() default "";

	/**
	 * 在ServletContext对象中读取的键，即servletContext.getAttribute(attrName)中的attrName值。
	 * <br>
	 * 同时该键在jsp页面中使用el表达式获取ServletContext中的值，即${attrName}<br>
	 * 已废弃，使用aliases属性名代替。
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return ServletContext对象中读取的键
	 * @since 1.2
	 */
	@Deprecated
	public String[] servletContextAttrNames() default "";

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
	 * 属性的默认值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 属性的默认值
	 * @since 3.0
	 */
	public String defaultValue() default "";

	/**
	 * 属性读取类型<br>
	 * 默认为DEFAULT类型，默认读取顺序"系统变量"-"java启动参数"-"项目文件"-"zookeeper"<br>
	 * 由后读取到的数据覆盖前读取到的数据<br>
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 属性读取类型
	 * @since 3.0
	 */
	public LinkPropertiesType type() default LinkPropertiesType.DEFAULT;
}
