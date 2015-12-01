package com.github.boyaframework.wechat.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



/**
 * @ClassName: HttpUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author L-liang
 * @date 2015年10月28日 下午9:48:28
 * 
 */
public class HttpUtils {
	/**
	 * @Fields ENCODING : 编码格式。发送编码格式统一用UTF-8
	 */
	private static String ENCODING = "UTF-8";

	public static String get(String url) {  
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		// 实例化HTTP方法  
		HttpGet request = new HttpGet(url);  
		try (CloseableHttpResponse response = client.execute(request);){
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return responseText;
    } 
	
	
	/**
	 * @Title: post
	 * @Description: post请求
	 * @param url
	 *            提交的URL
	 * @param paramsMap
	 *            提交<参数，值>Map
	 * @return String 返回类型
	 * @throws
	 */
	public static String post(String url, Map<String, String> paramsMap) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost method = new HttpPost(url);
			if (paramsMap != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : paramsMap.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(),
							param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return responseText;
	}
	
	public static void main(String[] args) {
		String str = get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=123&secret=132&code=123&grant_type=authorization_code");
		System.out.println(str);
	}
}
