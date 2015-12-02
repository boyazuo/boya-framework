package com.github.boyaframework.wechat.contants;

import java.util.HashMap;
import java.util.Map;

public class BaseContants {
	
	public static final String APPID = "wxdfbd0db40f545fdb";
	
	public static final String APPSECRET = "a8efada49fd035f46b8994c34a2ea86b";
	
	/** 
	 * @Fields ACCESS_TOKEN_MAP : 缓存access_token
	 */ 
	public static final Map<String, Long> ACCESS_TOKEN_MAP = new HashMap<String, Long>();
	/** 
	 * @Fields ACCESS_TOKEN_MAP : 缓存 js ticket 
	 */ 
	public static final Map<String, Long> JSAPI_TICKET_MAP = new HashMap<String, Long>();
}
