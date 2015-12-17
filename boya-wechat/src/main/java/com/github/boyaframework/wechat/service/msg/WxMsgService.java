package com.github.boyaframework.wechat.service.msg;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.boyaframework.wechat.contants.ApiContants;
import com.github.boyaframework.wechat.utils.HttpUtils;
import com.github.boyaframework.wechat.utils.WxAccessTokenUtils;

@Service
public class WxMsgService {
	static Logger logger = LoggerFactory.getLogger(WxMsgService.class);

	/**
	 * 
	* @Title: sendMsg 
	* @Description: TODO(向用户发送信息) 
	* @param firstValue
	* @param keyword1Value
	* @param keyword2Value
	* @param keyword3Value
	* @param keyword4Value
	* @param keyword5Value
	* @param remarkValue
	* @return String    返回类型 
	* @throws
	 */
	public String sendMsg(String firstValue, String keyword1Value, String keyword2Value, String keyword3Value,
			String keyword4Value, String keyword5Value,String remarkValue,String userOpenId,String url,String templateId) {
		String accessToken = WxAccessTokenUtils.getWxAccessToekn();
		String strURL = ApiContants.getSendMsgUrl(accessToken);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> first = new HashMap<String, Object>();
		first.put("value", firstValue);
		Map<String, Object> keyword1 = new HashMap<String, Object>();
		keyword1.put("value", keyword1Value);
		Map<String, Object> keyword2 = new HashMap<String, Object>();
		keyword2.put("value", keyword2Value);
		Map<String, Object> keyword3 = new HashMap<String, Object>();
		keyword3.put("value", keyword3Value);
		Map<String, Object> keyword4 = new HashMap<String, Object>();
		keyword4.put("value", keyword4Value);
		Map<String, Object> keyword5 = new HashMap<String, Object>();
		keyword5.put("value", keyword5Value);
		Map<String, Object> remark = new HashMap<String, Object>();
		remark.put("value", remarkValue);
		map.put("touser", userOpenId);
		map.put("template_id", templateId);
		map.put("url", url);
		map.put("topcolor", "#FF0000");
		data.put("first", first);
		data.put("keyword1", keyword1);
		data.put("keyword2", keyword2);
		data.put("keyword3", keyword3);
		data.put("keyword4", keyword4);
		data.put("keyword5", keyword5);
		data.put("remark", remark);
		map.put("data", data);
		return HttpUtils.postJson(strURL, map);
	}
	
}
