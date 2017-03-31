package com.tydic.android.usp.security;

import java.security.MessageDigest;

import net.iharder.Base64;

import com.tydic.android.usp.common.Logger;

/**
 * 编码功能
 *
 */
public class EncoderUtil {

    private static MessageDigest messageDigest = null;

    /**
     * 对输入参数经行MD5编码
     * @param str
     * @return
     */
    public static String getMd5Encoder(String str) {
        String retValue = "";
        try {
            if (messageDigest == null) {
                messageDigest = MessageDigest.getInstance("MD5");
            }
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();
            byte[] encoded = Base64.encodeBytesToBytes(digest);
            retValue = new String(encoded);
        } catch (Exception e) {
            Logger.e("md5","MD5编码出现错误");
            Logger.e("md5",e.getMessage());
        }
        return retValue;
    }
    
    public static void main(String[] args) {
		System.out.println(EncoderUtil.getMd5Encoder("123456"));
	}
}
