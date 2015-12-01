package com.github.boyaframework.wechat.filter;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.boyaframework.wechat.annotation.WxAccessToken;
import com.github.boyaframework.wechat.utils.WxAccessTokenUtils;

/** 
 * @ClassName: WxAccessTokenMethodArgumentResolver 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author L-liang
 * @date 2015年11月27日 下午10:41:27 
 *  
 */
public class WxAccessTokenMethodArgumentResolver extends HandlerMethodArgumentResolverComposite{
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if(parameter.hasParameterAnnotation(WxAccessToken.class)){
			return true;
		}
		return false;
	}
	/**
	 * 取出session中的当前登录用户
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		return WxAccessTokenUtils.getWxAccessToekn();
	}
}