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
	 * @Fields WX_ACCESS_TOKEN_API : 获取公众号的access_token 
	 */ 
	public static final String WX_ACCESS_TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ "{{APPID}}"
			+ "&secret="
			+ "{{APPSECRET}}";

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
	public static final String SEND_MSG_API = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
				+"{{ACCESS_TOKEN}}";
	
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
		url = url.replace("{{OPENID}}", openId);
		return url;
	}
	
	/** 
	 * @Title: getWxAccessToken 
	 * @Description: 得到获取微信号access_token的接口 
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getWxAccessToken() {
		String url = WX_ACCESS_TOKEN_API.replace("{{APPID}}", BaseContants.APPID);
		url = url.replace("{{APPSECRET}}", BaseContants.APPSECRET);
		return url;
	}
	
	
	public static final String JSAPI_TICKET_API = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={{ACCESS_TOKEN}}&type=jsapi";
	
	/** 
	 * @Title: getJsapiTicketUrl 
	 * @Description: 通过微信access_token 得到 js sdk ticket 
	 * @param accessToken
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getJsapiTicketUrl(String accessToken) {
		String url = JSAPI_TICKET_API.replace("{{ACCESS_TOKEN}}", accessToken);
		return url;
	}
	
	public static final String DOWNLOAD_MEDIA_API = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token={{ACCESS_TOKEN}}&media_id={{MEDIA_ID}}";

	/** 
	 * @Title: getDownloadMediaUrl 
	 * @Description: 得到下载多媒体文件的接口 
	 * @param accessToken
	 * @param mediaId
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getDownloadMediaUrl(String accessToken, String mediaId) {
		String url = DOWNLOAD_MEDIA_API.replace("{{ACCESS_TOKEN}}", accessToken);
		url = url.replace("{{MEDIA_ID}}", mediaId);
		return url;
	}
	
	/** 
	 * @Fields PLACE_AN_ORDER : 微信统一下单 
	 */ 
	public static final String PLACE_AN_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	public static String getSendMsgUrl(String accessToken) {
		String url = SEND_MSG_API.replace("{{ACCESS_TOKEN}}", accessToken);
		return url;
	}
	
	public static void main(String[] args) {
		System.out.println(getUserInfoUrl("OezXcEiiBSKSxW0eoylIeP0i103D4eoEDUkFv75zt1epAnLyR67l-rZnkMhKigVr9AzNPObVz3FU2C_cfCmW1JWf754PIcZSEeYwJcF8MKA53QvAXwhYA0eHbx6u5IDOpLOsto3MV7OZgX8hxdH5FQ", "123"));
	}
}
