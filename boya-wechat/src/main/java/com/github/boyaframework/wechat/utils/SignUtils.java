package com.github.boyaframework.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: SignUtils 
 * @Description: 签名工具类 
 * @author L-liang
 * @date 2015年12月7日 下午3:40:15 
 *  
 */
public class SignUtils {
	static Logger logger = LoggerFactory.getLogger(SignUtils.class);
	
	/** 
	 * @Title: createKeyValue 
	 * @Description: 对参数按照key=value的格式，并按照参数名ASCII字典序排序地方法
	 * @param param
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String createKeyValue(Map<String, Object> param) {
		List<String> keys = new ArrayList<String>();
		for(String key : param.keySet()) {
			keys.add(key);
		}
		//按照ASCII字典顺序排列
		Collections.sort(keys, new Comparator<String>() {
			public int compare(String key1, String key2) {
				return key1.compareTo(key2);
			}
		});
		String keyValue = "";
		for (String key : keys) {
			if(StringUtils.isNotBlank(param.get(key).toString())) {
				keyValue += key + "=" + param.get(key) + "&";
			}
		}
		if(keyValue.endsWith("&")) {
			keyValue = keyValue.substring(0, keyValue.length() - 1);
		}
		logger.info(keyValue);
		return keyValue;
	}
	
	/** 
	 * @Title: sign 
	 * @Description: 生成签名 
	 * @param keyValue
	 * @param signType
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String sign(String keyValue, String signType) {
		String signature = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance(signType);
			crypt.reset();
			crypt.update(keyValue.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("error", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("error", e);
		}
		return signature;
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
	
	/** 
	 * @Title: create_nonce_str 
	 * @Description: 随机字符串 
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String create_nonce_str() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/** 
	 * @Title: create_timestamp 
	 * @Description: 生成时间戳 
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	
	public static void main(String[] args) {
		Map<String, String> param = new HashMap<String, String>();
		
		param.put("appid", "wxd930ea5d5a258f4f");
		param.put("mch_id", "10000100");
		param.put("device_info", "1000");
		param.put("body", "test");
		param.put("nonce_str", "ibuaiVcKdpRxkhJA");
		
		
	}
}
