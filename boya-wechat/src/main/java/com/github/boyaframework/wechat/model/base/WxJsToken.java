package com.github.boyaframework.wechat.model.base;

/** 
 * @ClassName: WxJsToken 
 * @Description: 使用微信 js-sdk需要的凭证
 * @author L-liang
 * @date 2015年12月3日 下午10:17:02 
 *  
 */
public class WxJsToken {
	private String appId;
	private String timestamp;
	private String nonceStr;
	private String signature;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
