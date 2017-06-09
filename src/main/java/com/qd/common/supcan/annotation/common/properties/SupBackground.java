/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.common.supcan.annotation.common.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 硕正Background注解
 * 
 * @author WangZhen
 * @version 2013-11-12
 */
@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupBackground {

	/**
	 * 背景颜色
	 * 
	 * @return
	 */
	String bgColor() default "";

}
