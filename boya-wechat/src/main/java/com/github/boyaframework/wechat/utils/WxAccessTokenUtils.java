package com.github.boyaframework.wechat.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.github.boyaframework.wechat.contants.ApiContants;
import com.github.boyaframework.wechat.contants.BaseContants;
import com.google.gson.Gson;

/** 
 * @ClassName: WxAccessTokenUtils 
 * @Description: 获取微信access_token 工具类 
 * @author L-liang
 * @date 2015年12月1日 上午11:11:16 
 *  
 */
@SuppressWarnings("unchecked")
public class WxAccessTokenUtils {
	public static Integer TIME_OUT = 60 * 1000 * 60;
	
	/** 
	 * @Title: getWxAccessToekn 
	 * @Description: 获取access_token 
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getWxAccessToekn() {
		Map<String, Long> accessToken = BaseContants.ACCESS_TOKEN_MAP;
		//如果为空或者超时，重新获取
		String token = "";
		if(accessToken.isEmpty() || checkTimeout(accessToken)) {
			String apiUrl = ApiContants.getWxAccessToken();
			String result = HttpUtils.get(apiUrl);
			Gson gson = new Gson();
			HashMap<String, Object> resultMap = gson.fromJson(result, HashMap.class);
			token = (String) resultMap.get("access_token");
			BaseContants.ACCESS_TOKEN_MAP.clear();
			BaseContants.ACCESS_TOKEN_MAP.put(token, new Date().getTime());
		} else {
			for(String key : accessToken.keySet()) {
				token = key;
			}
		}
		return token;
	}
	
	private static boolean checkTimeout(Map<String, Long> accessTokenMap) {
		Long time = 0L;
		for(String key : accessTokenMap.keySet()) {
			time = accessTokenMap.get(key);
		}
		//当前时间
		Long currentTime = new Date().getTime();
		return (currentTime - time) > (TIME_OUT);//1小时刷新一次;
	}
}
