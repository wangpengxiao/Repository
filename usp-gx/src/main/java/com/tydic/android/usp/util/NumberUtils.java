/**
 * Project:		bqhm-v1
 * Package:		com.newsmobi.util
 * FileName:	NumberUtils.java
 * Created:		2011-12-23
 * CreatedBy:	<a href="mailto:13901155280@139.com">ZhangPeng</a>
 */
package com.tydic.android.usp.util;

import java.text.DecimalFormat;

import com.tydic.android.usp.common.Logger;



/**
 * 数字工具类
 * 
 * @version 1.0
 */
public class NumberUtils {
	/** 大写数字 */
	private static final String[] NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍",
			"陆", "柒", "捌", "玖" };
	/** 整数部分的单位 */
	private static final String[] IUNIT = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰",
			"仟", "万", "拾", "佰", "仟" };
	/** 小数部分的单位 */
	private static final String[] DUNIT = { "角", "分", "厘" };
	// 整数部分
	// private String integerPart;
	// 小数部分
	// private String floatPart;

	// 将数字转化为汉字的数组,因为各个实例都要使用所以设为静态
	private static final char[] cnNumbers = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };

	// 供分级转化的数组,因为各个实例都要使用所以设为静态
	private static final char[] series = { '元', '拾', '百', '仟', '万', '拾', '百', '仟', '亿' };

	/**
	 * 取得大写形式的字符串
	 * 
	 * @return
	 */
	public String getCnString(String original) {
		String integerPart = null;
		String floatPart = null;
		if (original.contains(".")) {
			// 如果包含小数点
			int dotIndex = original.indexOf(".");
			integerPart = original.substring(0, dotIndex);
			floatPart = original.substring(dotIndex + 1);
		} else {
			// 不包含小数点
			integerPart = original;
		}

		// 因为是累加所以用StringBuffer
		StringBuffer sb = new StringBuffer();

		// 整数部分处理
		for (int i = 0; i < integerPart.length(); i++) {
			int number = getNumber(integerPart.charAt(i));

			sb.append(cnNumbers[number]);
			sb.append(series[integerPart.length() - 1 - i]);
		}

		// 小数部分处理
		if (floatPart.length() > 0) {
			sb.append("点");
			for (int i = 0; i < floatPart.length(); i++) {
				int number = getNumber(floatPart.charAt(i));

				sb.append(cnNumbers[number]);
			}
		}

		// 返回拼接好的字符串
		return sb.toString();
	}

	/**
	 * 将字符形式的数字转化为整形数字 因为所有实例都要用到所以用静态修饰
	 * 
	 * @param c
	 * @return
	 */
	private static int getNumber(char c) {
		String str = String.valueOf(c);
		return Integer.parseInt(str);
	}

	/**
	 * 得到大写金额。
	 */
	public static String toChinese(String str) {
		str = str.replaceAll(",", "");// 去掉","
		String integerStr;// 整数部分数字
		String decimalStr;// 小数部分数字

		// 初始化：分离整数部分和小数部分
		if (str.indexOf(".") > 0) {
			integerStr = str.substring(0, str.indexOf("."));
			decimalStr = str.substring(str.indexOf(".") + 1);
		} else if (str.indexOf(".") == 0) {
			integerStr = "";
			decimalStr = str.substring(1);
		} else {
			integerStr = str;
			decimalStr = "";
		}
		// integerStr去掉首0，不必去掉decimalStr的尾0(超出部分舍去)
		if (!integerStr.equals("")) {
			integerStr = Long.toString(Long.parseLong(integerStr));
			if (integerStr.equals("0")) {
				integerStr = "";
			}
		}
		// overflow超出处理能力，直接返回
		if (integerStr.length() > IUNIT.length) {
			Logger.i(str + ":超出处理能力");
			return str;
		}

		int[] integers = toArray(integerStr);// 整数部分数字
		boolean isMust5 = isMust5(integerStr);// 设置万单位
		int[] decimals = toArray(decimalStr);// 小数部分数字
		return getChineseInteger(integers, isMust5)
				+ getChineseDecimal(decimals);
	}

	/**
	 * 整数部分和小数部分转换为数组，从高位至低位
	 */
	private static int[] toArray(String number) {
		int[] array = new int[number.length()];
		for (int i = 0; i < number.length(); i++) {
			array[i] = Integer.parseInt(number.substring(i, i + 1));
		}
		return array;
	}

	/**
	 * 得到中文金额的整数部分。
	 */
	private static String getChineseInteger(int[] integers, boolean isMust5) {
		StringBuffer chineseInteger = new StringBuffer("");
		int length = integers.length;
		for (int i = 0; i < length; i++) {
			// 0出现在关键位置：1234(万)5678(亿)9012(万)3456(元)
			// 特殊情况：10(拾元、壹拾元、壹拾万元、拾万元)
			String key = "";
			if (integers[i] == 0) {
				if ((length - i) == 13)// 万(亿)(必填)
					key = IUNIT[4];
				else if ((length - i) == 9)// 亿(必填)
					key = IUNIT[8];
				else if ((length - i) == 5 && isMust5)// 万(不必填)
					key = IUNIT[4];
				else if ((length - i) == 1)// 元(必填)
					key = IUNIT[0];
				// 0遇非0时补零，不包含最后一位
				if ((length - i) > 1 && integers[i + 1] != 0)
					key += NUMBERS[0];
			}
			chineseInteger.append(integers[i] == 0 ? key
					: (NUMBERS[integers[i]] + IUNIT[length - i - 1]));
		}
		return chineseInteger.toString();
	}

	/**
	 * 得到中文金额的小数部分。
	 */
	private static String getChineseDecimal(int[] decimals) {
		StringBuffer chineseDecimal = new StringBuffer("");
		for (int i = 0; i < decimals.length; i++) {
			// 舍去3位小数之后的
			if (i == 3)
				break;
			chineseDecimal.append(decimals[i] == 0 ? ""
					: (NUMBERS[decimals[i]] + DUNIT[i]));
		}
		return chineseDecimal.toString();
	}

	/**
	 * 判断第5位数字的单位"万"是否应加。
	 */
	private static boolean isMust5(String integerStr) {
		int length = integerStr.length();
		if (length > 4) {
			String subInteger = "";
			if (length > 8) {
				// 取得从低位数，第5到第8位的字串
				subInteger = integerStr.substring(length - 8, length - 4);
			} else {
				subInteger = integerStr.substring(0, length - 4);
			}
			return Integer.parseInt(subInteger) > 0;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param input
	 * @return int类型的数字
	 */
	public static int parseInt(String input) {
		return parseInt(input, 0);
	}

	/**
	 * 
	 * @param input
	 * @param defaultValue
	 * @return int类型的数字
	 */
	public static int parseInt(String input, int defaultValue) {
		int li_Return = 0;
		input = StringUtils.trim(input);
		if (input.equals(""))
			return defaultValue;
		try {
			li_Return = Integer.parseInt(input);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return li_Return;

	}

	/**
	 * 
	 * @param input
	 * @return long类型的数字
	 */
	public static long parseLong(String input) {
		return parseLong(input, 0);
	}

	/**
	 * 
	 * @param input
	 * @param defaultValue
	 * @return long类型的数字
	 */
	public static long parseLong(String input, long defaultValue) {
		long ll_Return = 0;
		input = StringUtils.trim(input);
		if (input.equals(""))
			return defaultValue;
		try {
			ll_Return = Long.parseLong(input);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return ll_Return;
	}

	/**
	 * 
	 * @param input
	 * @return double类型的数字
	 */
	public static double parseDouble(String input) {
		return parseDouble(input, 0);
	}

	/**
	 * 
	 * @param input
	 * @param defaultValue
	 * @return double类型的数字
	 */
	public static double parseDouble(String input, long defaultValue) {
		double ldb_Return = 0;
		input = StringUtils.trim(input);
		if (input.equals(""))
			return defaultValue;
		try {
			ldb_Return = Double.parseDouble(input);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return ldb_Return;
	}

	public static String formatNumber(double input, String pattern) {
		DecimalFormat ldecimalformat_Format;
		String ls_Formatted;

		try {
			ldecimalformat_Format = new DecimalFormat(pattern);
			ls_Formatted = ldecimalformat_Format.format(input);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.i("NumberUtil.formatNumber(): " + e.getMessage());
			ls_Formatted = Double.toString(input);
		}

		return ls_Formatted;
	}

	public static String formatNumber(int input, String pattern) {
		DecimalFormat ldecimalformat_Format;
		String ls_Formatted;

		try {
			ldecimalformat_Format = new DecimalFormat(pattern);
			ls_Formatted = ldecimalformat_Format.format(input);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.i("NumberUtil.formatNumber(): " + e.getMessage());
			ls_Formatted = Double.toString(input);
		}

		return ls_Formatted;
	}

	public static String formatNumber(long input, String pattern) {
		DecimalFormat ldecimalformat_Format;
		String ls_Formatted;

		try {
			ldecimalformat_Format = new DecimalFormat(pattern);
			ls_Formatted = ldecimalformat_Format.format(input);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.i("NumberUtil.formatNumber(): " + e.getMessage());
			ls_Formatted = Double.toString(input);
		}

		return ls_Formatted;
	}

	public static boolean isNumber(String input) {
		String ls_String = "";
		char[] larrs_String;
		int i;

		if (input == null) {
			return false;
		}

		// get rid of the "," in the string
		larrs_String = input.toCharArray();
		for (i = 0; i < larrs_String.length; i++) {
			if (larrs_String[i] != ',') {
				ls_String += larrs_String[i];
			}
		}
		try {
			Double.parseDouble(ls_String);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
