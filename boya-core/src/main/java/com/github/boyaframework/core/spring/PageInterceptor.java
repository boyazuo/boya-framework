package com.github.boyaframework.core.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.github.boyaframework.core.persistence.Page;
import com.github.pagehelper.PageHelper;


/**
 * 分页的web层拦截器
 * 
 * @author Boya
 */
public class PageInterceptor {
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		Page<?> page = null;
		Object[] args =  pjp.getArgs();
		boolean pageable = true;
		int pageSize = 10;
		for (Object arg : args) {
			if (arg instanceof Page) {
				Method method = ((MethodSignature)pjp.getSignature()).getMethod();
				Annotation[][] ass = method.getParameterAnnotations();
				for (int i = 0; i < ass.length; i++) {
					for (int j = 0; j < ass[i].length; j++) {
						Annotation a = ass[i][j];
						if(a instanceof PageConfig) {
							PageConfig pageConfig = (PageConfig) a;
							pageable = pageConfig.pageable();
							pageSize = pageConfig.pageSize();
						}
					}
				}
				if (pageable) {
					page = (Page<?>)arg;
					page.setPageSize(pageSize);
				}
				break;
			}
		}
		if (page != null) {
			PageHelper.startPage(page.getPageNo(), page.getPageSize());
			if (StringUtils.isNotBlank(page.getSortStr())) {
				PageHelper.orderBy(page.getSortStr());
			}
		}
		Object object = pjp.proceed();// 执行该方法
		return object;
	}

}
