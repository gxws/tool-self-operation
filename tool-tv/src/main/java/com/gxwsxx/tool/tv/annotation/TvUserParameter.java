package com.gxwsxx.tool.tv.annotation;

import java.lang.annotation.*;

/**
 * webtv类型项目要求的参数
 *
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME) @Documented @Inherited
public @interface TvUserParameter {

    /**
     * 参数名
     *
     * @return 参数名
     * @since 1.1
     */
    String[] name() default "";

    /**
     * 描述
     *
     * @return 描述说明
     * @since 1.1
     */
    String description() default "";

    /**
     * 是否必要参数
     *
     * @return 是否
     * @since 1.1
     */
    boolean require() default true;
}
