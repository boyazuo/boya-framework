package com.github.boyaframework.wechat.utils;

public class WxMediaUtils {
	/** 
	 * @Title: getExe 
	 * @Description: 通过 contentType得到 文件类型
	 * @param contentType
	 * @return String    返回类型 
	 * @throws 
	 */
	public static String getExe(String contentType) {
		switch (contentType) {
		case "image/jpeg":
			return ".jpg";
		case "application/x-png":
			return ".png";
		case "image/gif":
			return ".gif";
		case "application/x-bmp":
			return ".bmp";
		default:
			return "";
		}
	}
}
 