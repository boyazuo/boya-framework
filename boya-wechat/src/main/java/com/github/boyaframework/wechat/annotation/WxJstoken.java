package com.github.boyaframework.wechat.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName: WxJstoken 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author L-liang
 * @date 2015年12月3日 下午10:31:24 
 *  
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WxJstoken {}
