package com.github.boyaframework.wechat.service.media;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.github.boyaframework.wechat.contants.ApiContants;
import com.github.boyaframework.wechat.utils.WxMediaUtils;

/**
 * @ClassName: WxMediaService
 * @Description: 多媒体操作类
 * @author L-liang
 * @date 2015年12月1日 下午3:59:47
 * 
 */
@Component
public class WxMediaService {
	static Logger logger = Logger.getLogger(WxMediaService.class);

	/**
	 * 获取媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param media_id
	 *            媒体文件id
	 * @param savePath
	 *            文件在服务器上的存储路径
	 * */
	public static HttpURLConnection downloadMedia(String accessToken,
			String mediaId) {
		// 拼接请求地址
		String apiurl = ApiContants.getDownloadMediaUrl(accessToken, mediaId);
		logger.info(apiurl);
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(apiurl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
		} catch (MalformedURLException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		return conn;
	}
	/** 
	 * @Title: downloadMedia2Path 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param accessToken
	 * @param mediaId
	 * @param filePath
	 * @param newName
	 * @return HttpURLConnection    返回类型 
	 * @throws 
	 */
	public static Map<String, Object> downloadMedia2Path(String accessToken,
			String mediaId, String filePath, String newName) {
		Map<String, Object> data = new HashMap<String, Object>();
		// 拼接请求地址
		String apiurl = ApiContants.getDownloadMediaUrl(accessToken, mediaId);
		logger.info(apiurl);
		URL url = null;
		HttpURLConnection conn = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		try {
			url = new URL(apiurl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			String exe = WxMediaUtils.getExe(conn.getContentType());
			data.put("exe", exe);
			data.put("newName", newName + exe);
			File file = new File(filePath);
			//不存在创建文件夹
			if(!file.exists()){
				file.mkdirs();
			}
			file = new File(filePath + File.separator + newName + exe);
			fos = new FileOutputStream(file);
			bis = new BufferedInputStream(conn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				fos.write(buf, 0, size);
			}
		} catch (MalformedURLException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if(fos != null) {
					fos.close();
				}
				if(bis != null) {
					bis.close();
				}
				fos = null;
				bis = null;
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return data;
	}

	public static void main(String[] args) throws IOException {
		downloadMedia2Path(
				"GsBzvA1qTa-UCvB8hnqEsOe1AjH-cWjHoERgQ1oU_Nk_I1XmWKrVDtBahthuEPDpzCNVa0nw4iIdJ54yIWUyk7fg-xoRMpHKcccR5BtuWVkXIWfAIAEPD",
				"0UIls6N93oYxKiKyiLkAGUEw_RHqRBdJHQZo0znrGc75FNB89nDOL9pcYRiUiyhq","d:/", "eee");
	}
}
