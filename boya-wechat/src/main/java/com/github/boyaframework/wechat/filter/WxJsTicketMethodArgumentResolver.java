package com.github.boyaframework.wechat.filter;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.boyaframework.wechat.annotation.WxJsTicket;
import com.github.boyaframework.wechat.utils.WxAccessTokenUtils;
import com.github.boyaframework.wechat.utils.WxJsTicketUtils;

/** 
 * @ClassName: WxJsTicketMethodArgumentResolver 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author L-liang
 * @date 2015年12月1日 上午11:13:01 
 *  
 */
public class WxJsTicketMethodArgumentResolver extends HandlerMethodArgumentResolverComposite{
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if(parameter.hasParameterAnnotation(WxJsTicket.class)){
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
		String token = WxAccessTokenUtils.getWxAccessToekn();
		return WxJsTicketUtils.getJsapiTicket(token);
	}
}