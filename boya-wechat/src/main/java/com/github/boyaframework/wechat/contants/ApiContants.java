package com.github.boyaframework.wechat.contants;

/**
 * @ClassName: ApiContants
 * @Description: 微信接口常量
 * @author L-liang
 * @date 2015年11月18日 下午5:37:01
 * 
 */
public class ApiContants {
	/** 
	 * @Fields SNSAPI_BASE :  
	 */ 
	public static final String SNSAPI_BASE = "snsapi_base";
	/** 
	 * @Fields SNSAPI_USERINFO :  
	 */ 
	public static final String SNSAPI_USERINFO = "snsapi_userinfo";

	/** 
	 * @Fields AUTHORIZE : 获取授权接口 
	 */ 
	public static final String AUTHORIZE_API = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={{appid}}&redirect_uri="
			+ "{{REDIRECT_URI}}"
			+ "&response_type=code&scope="
			+ "{{SCOPE}}"
			+ "&state="
			+ "{{STATE}}"
			+ "#wechat_redirect";
	/** 
	 * @Fields ACCESS_TOKEN_API : 通过code获得access_token的接口 
	 */ 
	public static final String ACCESS_TOKEN_API = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
			+ "{{APPID}}"
			+ "&secret="
			+ "{{SECRET}}"
			+ "&code="
			+ "{{CODE}}"
			+ "&grant_type=authorization_code";
	/** 
	 * @Fields REFLASH_ACCESS_TOKEN_API : 刷新access_token的原始接口 
	 */ 
	public static final String REFLASH_ACCESS_TOKEN_API = "https://api.weixin.qq.com/sns/oauth2/refresh_token?"
			+ "appid="
			+ "{{APPID}}"
			+ "&grant_type=refresh_token&refresh_token="
			+ "{{REFRESH_TOKEN}}";
	/** 
	 * @Fields USER_INFO_API : 通过accesss_token获取用户信息接口 
	 */ 
	public static final String USER_INFO_API = "https://api.weixin.qq.com/sns/userinfo?access_token="
			+ "{{ACCESS_TOKEN}}"
			+ "&openid="
			+ "{{OPENID}}"
			+ "&lang=zh_CN";
	
	/** 
	 * @Title: getAuthorizeUrl 
	 * @Description: 得到请求授权的接口 
	 * @param appid
	 * @param redirectUri
	 * @param scope
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getAuthorizeUrl(String redirectUri, String scope, String state) {
		String url = AUTHORIZE_API.replace("{{appid}}", BaseContants.APPID);
		url = url.replace("{{REDIRECT_URI}}", redirectUri);
		url = url.replace("{{SCOPE}}", scope);
		url = url.replace("{{STATE}}", state);
		return url;
	} 
	/** 
	 * @Title: getAuthorizeUrl 
	 * @Description: 使用 SNSAPI_USERINFO 获取用户信息的接口
	 * @param appid
	 * @param redirectUri
	 * @param state
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getAuthorizeUrl(String redirectUri, String state) {
		String url = AUTHORIZE_API.replace("{{appid}}", BaseContants.APPID);
		url = url.replace("{{REDIRECT_URI}}", redirectUri);
		url = url.replace("{{SCOPE}}", SNSAPI_USERINFO);
		url = url.replace("{{STATE}}", state);
		return url;
	} 
	/** 
	 * @Title: getAccessTokenUrl 
	 * @Description: 得到获取access_token和openID的url 
	 * @param code
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getAccessTokenUrl(String code) {
		String url = ACCESS_TOKEN_API.replace("{{APPID}}", BaseContants.APPID);
		url = url.replace("{{SECRET}}", BaseContants.APPSECRET);
		url = url.replace("{{CODE}}", code);
		return url;
	}
	/** 
	 * @Title: getReflashTokenUrl 
	 * @Description: 得到刷新token的url 
	 * @param refreshToken
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getReflashTokenUrl(String refreshToken) {
		String url = REFLASH_ACCESS_TOKEN_API.replace("{{APPID}}", BaseContants.APPID);
		url = url.replace("{{SECRET}}", BaseContants.APPSECRET);
		url = url.replace("{{REFRESH_TOKEN}}", refreshToken);
		return url;
	}
	/** 
	 * @Title: getUserInfoUrl 
	 * @Description: 得到获取微信用户信息的接口地址，如果网页授权作用域为snsapi_userinfo 
	 * @param accessToken
	 * @param openId
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getUserInfoUrl(String accessToken, String openId) {
		String url = USER_INFO_API.replace("{{ACCESS_TOKEN}}", accessToken);
		url = USER_INFO_API.replace("{{OPENID}}", openId);
		return url;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(getAccessTokenUrl("123"));
	}
}