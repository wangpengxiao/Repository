package com.tydic.android.usp.security;

/**
 * 安全
 */
public interface Security{
	/**
	 * 加密
	 * @param str 加密后
	 * @return 加密前
	 */
	public abstract String encryptStr(String str);
	
	/**
	 * 解密
	 * @param str
	 * @return
	 */
	public abstract String decryptStr(String str);
}
