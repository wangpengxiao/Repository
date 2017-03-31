package com.tydic.android.usp.uss;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验类
 * 
 * @author lichao
 *
 */
public class ValidatorUtils {

	/**
	 * 校验邮箱
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
