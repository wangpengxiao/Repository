package com.tydic.android.usp.security;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.tydic.android.usp.common.Logger;


import android.util.Log;

public class Des3Encrypt implements Security {
	private static final byte[] KEY = Base64Coder.decode("c3R1dnd4YWJjZGVmZ2hpamtsbW5vcHFy");
	static byte keyiv[] = {0,0,0,0,0,0,0,0}; //CBC模式加密需要IV

	public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);

		return bOut;
	}

	/**
	 * 
	 * CBC加密
	 * 
	 * @param key
	 *            密钥
	 * 
	 * @param keyiv
	 *            IV
	 * 
	 * @param data
	 *            明文
	 * 
	 * @return Base64编码的密文
	 * 
	 * @throws Exception
	 */

	public static byte[] des3EncodeCBC(byte[] key, byte[] data)
	throws Exception {

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);

		return bOut;

	}

	/**
	 * 
	 * CBC解密
	 * 
	 * @param key
	 *            密钥
	 * 
	 * @param keyiv
	 *            IV
	 * 
	 * @param data
	 *            Base64编码的密文
	 * 
	 * @return 明文
	 * 
	 * @throws Exception
	 */

	public static byte[] des3DecodeCBC(byte[] key, byte[] data)
	throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	public static byte[] ees3DecodeECB(byte[] key, byte[] data) throws Exception {

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);

		return bOut;

	}


	@Override
	public String encryptStr(String str) {

		Log.d("encryptStr", str);
		if (str == null || str.trim().equals(""))
			return "";
		try {
			byte[] data = str.getBytes("UTF-8");
			byte[] str1 = des3EncodeCBC(KEY, data);
			byte[] str2 = des3EncodeECB(KEY, data);
			Log.d("****cccccc", new String(Base64Coder.encode(str1)));
			Log.d("****eeeeee", new String(Base64Coder.encode(str2)));
			return new String(Base64Coder.encode(str1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String decryptStr(String str) {
		if (str == null || str.trim().equals(""))
			return "";
		Logger.d("beforeDecrypt","str="+str);
		try {
			byte[] data = Base64Coder.decode(str);
			byte[] str3 = des3DecodeCBC(KEY, data);
			Log.d("decryptStr", str3.toString());
			return new String(str3, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
