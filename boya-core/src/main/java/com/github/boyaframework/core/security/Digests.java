package com.github.boyaframework.core.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import com.github.boyaframework.core.utils.Encodes;
import com.github.boyaframework.core.utils.Exceptions;

/**
 * 支持SHA-1/MD5消息摘要的工具类.
 * 
 * 返回String，统一编码为Hex
 * 
 * @author calvin
 */
public class Digests {

	public static final String SHA1 = "SHA-1";
	public static final String MD5 = "MD5";
	//默认加密算法
	public static final String HASH_ALGORITHM = MD5;
	//默认迭代次数
	public static final int HASH_INTERATIONS = 2;

	/**
	 * 对输入字符串进行sha1散列.
	 */
	public static String sha1(String input) {
		return digest(input, SHA1, null, 1);
	}

	public static String sha1(String input, String salt) {
		return digest(input, SHA1, salt, HASH_INTERATIONS);
	}

	public static String sha1(String input, String salt, int iterations) {
		return digest(input, SHA1, salt, iterations);
	}
	/**
	 * 对输入字符串进行md5散列.
	 */
	public static String md5(String input) {
		return digest(input, MD5, null, 1);
	}

	public static String md5(String input, String salt) {
		return digest(input, MD5, salt, HASH_INTERATIONS);
	}

	public static String md5(String input, String salt, int iterations) {
		return digest(input, MD5, salt, iterations);
	}

	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 */
	private static String digest(String input, String algorithm, String salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(Encodes.toBytes(salt));
			}
			byte[] result = digest.digest(Encodes.toBytes(input));

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return Encodes.encodeHex(result);
		} catch (GeneralSecurityException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * 对文件进行md5散列.
	 */
	public static String md5(InputStream input) throws IOException {
		return digest(input, MD5);
	}

	/**
	 * 对文件进行sha1散列.
	 */
	public static String sha1(InputStream input) throws IOException {
		return digest(input, SHA1);
	}

	private static String digest(InputStream input, String algorithm) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 8 * 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			byte[] result = messageDigest.digest();
			return Encodes.encodeHex(result);
		} catch (GeneralSecurityException e) {
			throw Exceptions.unchecked(e);
		}
	}
	public static void main(String[] args) {
		System.out.println(Digests.md5("123456", "18688888884", Digests.HASH_INTERATIONS));
	}
}
