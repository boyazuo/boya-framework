package com.github.boyaframework.wechat.service.user;


import org.springframework.stereotype.Component;

import com.github.boyaframework.wechat.contants.ApiContants;
import com.github.boyaframework.wechat.model.base.WxErr;
import com.github.boyaframework.wechat.model.user.WxUserInfo;
import com.github.boyaframework.wechat.model.user.WxUserTokenInfo;
import com.github.boyaframework.wechat.utils.HttpUtils;
import com.google.gson.Gson;

/** 
 * @ClassName: WxUserService 
 * @Description: 获取用户信息业务类 
 * @author L-liang
 * @date 2015年12月1日 上午10:35:18 
 *  
 */
@Component
public class WxUserService {
	/** 
	 * @Title: getWxUserTokenByCode 
	 * @Description: 通过授权code 得到 用户的access_token 和 openid 得信息
	 * @param code
	 * @return WxUserTokenInfo    返回类型 
	 * @throws 
	 */
	public WxUserTokenInfo getWxUserTokenByCode(String code) {
		String api = ApiContants.getAccessTokenUrl(code);
		String result = HttpUtils.get(api);
		Gson gson = new Gson();
		//包含errcode 返回的是错误信息
		if(result.indexOf("errcode") != -1) {
			gson.fromJson(result, WxErr.class);
		}
		return gson.fromJson(result, WxUserTokenInfo.class);
	} 
	
	/** 
	 * @Title: getWxUserInfoByOpenIdAndAccessToken 
	 * @Description: 通过Openid 和 access_token 得到微信用户信息 
	 * @param accessToken
	 * @param openid
	 * @return WxUserInfo    返回类型 
	 * @throws 
	 */
	public WxUserInfo getWxUserInfoByOpenIdAndAccessToken(String accessToken, String openid) {
		String userInfoApi = ApiContants.getUserInfoUrl(accessToken, openid);
		String result = HttpUtils.get(userInfoApi);
		Gson gson = new Gson();
		//包含errcode 返回的是错误信息
		if(result.indexOf("errcode") != -1) {
			gson.fromJson(result, WxErr.class);
		}
		return gson.fromJson(result, WxUserInfo.class);
	}
	public static void main(String[] args) {
	}
}
