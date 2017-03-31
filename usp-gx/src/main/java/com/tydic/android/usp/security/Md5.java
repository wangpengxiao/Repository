package com.tydic.android.usp.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5类 静态 Md5.getMd5Str()
 */
public class Md5 implements Security{
	@SuppressWarnings("unused")
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	       'A', 'B', 'C', 'D', 'E', 'F' };
	
	private static String toHexString(byte[] b) {  //byte to String
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();  
	}
	
	/**
	 * @param s
	 * @return MD5str
	 */
	public static String md5(String s) {
       try {
           // Create MD5 Hash
           MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
           digest.update(s.getBytes());
           byte messageDigest[] = digest.digest();
                       
           return toHexString(messageDigest);
       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
       return "";
   }

	/* (non-Javadoc)
	 * @see com.android.VanclClient.security.Security#toSecurityWord()
	 */
	@Override
	public String encryptStr(String str) {
		// TODO Auto-generated method stub
		return md5(str);
	}

	/* (non-Javadoc)
	 * @see com.android.VanclClient.security.Security#decryptWord(java.lang.String)
	 */
	@Override
	public String decryptStr(String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
