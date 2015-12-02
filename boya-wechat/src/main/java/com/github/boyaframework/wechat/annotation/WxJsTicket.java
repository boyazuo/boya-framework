package com.github.boyaframework.wechat.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName: WxJsTicket 
 * @Description: 获取 js sdk 的门票
 * @author L-liang
 * @date 2015年12月1日 上午11:07:14 
 *  
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WxJsTicket {}
