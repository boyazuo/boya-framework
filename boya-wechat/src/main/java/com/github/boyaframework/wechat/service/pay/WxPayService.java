package com.github.boyaframework.wechat.service.pay;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.github.boyaframework.wechat.contants.ApiContants;
import com.github.boyaframework.wechat.contants.BaseContants;
import com.github.boyaframework.wechat.utils.HttpUtils;
import com.github.boyaframework.wechat.utils.SignUtils;
import com.github.boyaframework.wechat.utils.XmlUtils;

public class WxPayService {
	static Logger logger = Logger.getLogger(WxPayService.class);
	/** 
	 * @Title: placeAnOrder 
	 * @Description: 生成一个微信订单 
	 * @param body 商品描述
	 * @param orderNum  订单号
	 * @param totalMoney 总价
	 * @param ip ip地址
	 * @param notifyUrl 回调地址
	 * @param openid oppenId（公众号支付时，必须有此参数）
	 * @return Map<String,Object>    返回类型 
	 * @throws 
	 */
	public static Map<String,Object> placeAnOrder(String body, String orderNum, Integer totalMoney, String ip, String notifyUrl, String openid){
		Map<String, Object> param = new HashMap<String, Object>();
		//公众号ID
		param.put("appid", BaseContants.APPID);
		//商户号ID
		param.put("mch_id", BaseContants.MCH_ID);
		//终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
		param.put("device_info", "WEB");
		param.put("nonce_str", SignUtils.create_nonce_str());
		//商品或支付单简要描述
		param.put("body", body);
		//商品名称明细列表
		//param.put("detail", detail);
		//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
		//param.put("attach", attach);
		//商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
		param.put("out_trade_no", orderNum);
		//符合ISO 4217标准的三位字母代码，默认人民币：CNY
		//param.put("fee_type", "CNY");
		//订单总金额，单位为分
		param.put("total_fee", totalMoney);
		//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
		param.put("spbill_create_ip", ip);
		//接收微信支付异步通知回调地址
		param.put("notify_url", notifyUrl);
		//取值如下：JSAPI，NATIVE，APP，
		param.put("trade_type", "JSAPI");
		param.put("openid", openid);
		
		//生成签名
		String stringSignTemp = SignUtils.createKeyValue(param);
		stringSignTemp = stringSignTemp + "&key=" + BaseContants.PAY_KEY;
		stringSignTemp = SignUtils.sign(stringSignTemp, "MD5").toUpperCase();
		//放入参数
		param.put("sign", stringSignTemp);
		
		String result = HttpUtils.postXml(ApiContants.PLACE_AN_ORDER, param);
		logger.info(result);
		Map<String, Object> resultData = new HashMap<String, Object>();
		try {
			resultData = XmlUtils.xmlBody2map(result,"xml");
		} catch (DocumentException e) {
			logger.error("error", e);
		}
		return resultData;
	}
	
	
	public static void main(String[] args) {
		Map<String,Object> resultData = placeAnOrder("什么都不是", "20150806125346", 1, "123.12.12.123", "http://www.weixin.qq", "oivJ3wIr29Ob5U7-hjZqKlB7TxAQ");
		for (String key : resultData.keySet()) {
			System.out.println(key + "====" + resultData.get(key));
		}
	}
}
