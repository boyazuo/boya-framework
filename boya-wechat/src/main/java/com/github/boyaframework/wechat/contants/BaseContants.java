package com.github.boyaframework.wechat.contants;

import java.util.HashMap;
import java.util.Map;

public class BaseContants {
	
	public static final String APPID = "wx63535708a8a49faf";
	
	public static final String APPSECRET = "712e6c6d561009d9de4d72ed9cd66d56";
	
	/** 
	 * @Fields ACCESS_TOKEN_MAP : 缓存access_token
	 */ 
	public static final Map<String, Long> ACCESS_TOKEN_MAP = new HashMap<String, Long>();
	/** 
	 * @Fields ACCESS_TOKEN_MAP : 缓存 js ticket 
	 */ 
	public static final Map<String, Long> JSAPI_TICKET_MAP = new HashMap<String, Long>();
}
