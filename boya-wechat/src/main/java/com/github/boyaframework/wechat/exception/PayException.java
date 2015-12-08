package com.github.boyaframework.wechat.exception;

/** 
 * @ClassName: PayException 
 * @Description: 支付业务相关异常 
 * @author L-liang
 * @date 2015年12月8日 上午10:01:24 
 *  
 */
@SuppressWarnings("serial")
public class PayException extends Exception{
	public PayException(){
	}
	public PayException(String message){
		super(message);
	}
}
