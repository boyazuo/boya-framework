package com.github.boyaframework.core.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageConfig {
	/** 
	 * @Title: pageable 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return boolean    返回类型 
	 * @throws 
	 */
	boolean pageable() default true;
	
	/**
	 * @return
	 */
	int pageSize() default 10;
}
