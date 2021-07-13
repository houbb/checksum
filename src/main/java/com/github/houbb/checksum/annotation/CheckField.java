package com.github.houbb.checksum.annotation;

import java.lang.annotation.*;

/**
 * 参与验签的字段
 *
 * 1. 默认对字段进行验签
 * 2. 如果指定 @CheckField 且 required=false，则不作处理。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckField {

    /**
     * 是否需要当前字段
     * @return 是否
     * @since 0.0.5
     */
    boolean required() default true;

}
