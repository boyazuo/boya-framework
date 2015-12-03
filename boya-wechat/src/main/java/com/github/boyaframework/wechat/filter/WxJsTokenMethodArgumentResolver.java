package com.github.boyaframework.wechat.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.boyaframework.wechat.annotation.WxJstoken;
import com.github.boyaframework.wechat.contants.BaseContants;
import com.github.boyaframework.wechat.model.base.WxJsToken;
import com.github.boyaframework.wechat.utils.WxAccessTokenUtils;
import com.github.boyaframework.wechat.utils.WxJsTicketUtils;

/** 
 * @ClassName: WxJsTokenMethodArgumentResolver 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author L-liang
 * @date 2015年12月3日 下午10:18:32 
 *  
 */
public class WxJsTokenMethodArgumentResolver extends HandlerMethodArgumentResolverComposite{
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if(parameter.hasParameterAnnotation(WxJstoken.class)){
			return true;
		}
		return false;
	}
	/**
	 * 取出session中的当前登录用户
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContiner, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		String token = WxAccessTokenUtils.getWxAccessToekn();
		String ticket = WxJsTicketUtils.getJsapiTicket(token);
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);  
		String url = getRealUrl(request);
		Map<String, String> wx = WxJsTicketUtils.sign(ticket, url);
		WxJsToken jsToken = new WxJsToken();
		jsToken.setAppId(BaseContants.APPID);
		jsToken.setNonceStr(wx.get("nonceStr"));
		jsToken.setSignature(wx.get("signature"));
		jsToken.setTimestamp(wx.get("timestamp"));
		return jsToken;
	}
	
	/** 
	 * @Title: getRealUrl 
	 * @Description: 获得真是的url
	 * @param request
	 * @return String    返回类型 
	 * @throws 
	 */
	private String getRealUrl(HttpServletRequest request) {
		String preUrl = request.getRequestURL().toString();
		String fixUrl = request.getQueryString();
		if(StringUtils.isNotBlank(fixUrl)) {
			return preUrl + "?" + fixUrl;
		}
		return preUrl;
	}
}