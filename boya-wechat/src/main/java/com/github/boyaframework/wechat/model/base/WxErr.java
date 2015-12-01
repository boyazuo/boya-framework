package com.github.boyaframework.wechat.model.base;

/** 
 * @ClassName: WxErr 
 * @Description: 微信返回错误信息实体类 
 * @author L-liang
 * @date 2015年12月1日 上午10:49:40 
 *  
 */
public class WxErr {
	private String errcode;
	private String errmsg;
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
