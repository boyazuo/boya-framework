package com.github.boyaframework.wechat.contants;

import java.util.HashMap;
import java.util.Map;

public class BaseContants {
	
/*	public static String APPID = "wxdfbd0db40f545fdb";
	
	public static String APPSECRET = "a8efada49fd035f46b8994c34a2ea86b";
	
	public static String MCH_ID = "1287134601";
	
	public static String PAY_KEY = "N2MwMWQwNGU2ODI5YjU4MmJmNjJmOTRk";*/
	public static String APPID;
	
	public static String APPSECRET;
	
	public static String MCH_ID;
	
	public static String PAY_KEY;
	
	/** 
	 * @Fields ACCESS_TOKEN_MAP : 缓存access_token
	 */ 
	public static final Map<String, Long> ACCESS_TOKEN_MAP = new HashMap<String, Long>();
	/** 
	 * @Fields ACCESS_TOKEN_MAP : 缓存 js ticket 
	 */ 
	public static final Map<String, Long> JSAPI_TICKET_MAP = new HashMap<String, Long>();
	
	/**
	* @Fields MSG_TO_USER_TEMPLATE_ID : 向用户发送信息模板ID
	*/ 
	public static final String MSG_TO_USER_TEMPLATE_ID = "Xjd4ZxA1xeWUphqV0-HfIhTwgoyFT_2xp7354BGFJo0";
	/**
	* @Fields MSG_TO_DEALER_TEMPLATE_ID : 向商户发送信息模板ID
	*/ 
	public static final String MSG_TO_DEALER_TEMPLATE_ID = "sFEJSg4zdRs0sCUq07OsbCmcqPnqUHzD2fxT-QSqLFw";
}
