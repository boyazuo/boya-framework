package com.github.boyaframework.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.github.boyaframework.wechat.contants.ApiContants;
import com.github.boyaframework.wechat.contants.BaseContants;
import com.google.gson.Gson;

/** 
 * @ClassName: WxJsTicketUtils 
 * @Description: 微信 js 工具类 
 * @author L-liang
 * @date 2015年12月1日 上午11:16:45 
 *  
 */
public class WxJsTicketUtils {
	private static Logger logger = Logger.getLogger(WxJsTicketUtils.class);
	public static Integer TIME_OUT = 60 * 1000 * 60;
	/**
	 * @Title: getJsapiTicket
	 * @Description: 得到js ticket
	 * @param accseeToken
	 * @return String 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static String getJsapiTicket(String accessToken) {
		Map<String, Long> jsapiTicket = BaseContants.JSAPI_TICKET_MAP;
		// 如果为空或者超时，重新获取
		String ticket = "";
		if (jsapiTicket.isEmpty() || checkTimeout(jsapiTicket)) {
			String apiUrl = ApiContants.getJsapiTicketUrl(accessToken);
			String result = HttpUtils.get(apiUrl);
			Gson gson = new Gson();
			HashMap<String, Object> resultMap = gson.fromJson(result,
					HashMap.class);
			ticket = (String) resultMap.get("ticket");
			BaseContants.JSAPI_TICKET_MAP.clear();
			BaseContants.JSAPI_TICKET_MAP.put(ticket, new Date().getTime());
		} else {
			for (String key : jsapiTicket.keySet()) {
				ticket = key;
			}
		}
		return ticket;
	}

	private static boolean checkTimeout(Map<String, Long> accessTokenMap) {
		Long time = 0L;
		for (String key : accessTokenMap.keySet()) {
			time = accessTokenMap.get(key);
		}
		// 当前时间
		Long currentTime = new Date().getTime();
		return (currentTime - time) > (TIME_OUT);// 1小时刷新一次;
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		logger.info(string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("error", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("error", e);
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}
	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
