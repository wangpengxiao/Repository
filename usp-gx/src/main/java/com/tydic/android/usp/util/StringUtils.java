package com.tydic.android.usp.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;

/** 
 * 类说明：   字符串操作类 
 * @version 1.0
 */
public class StringUtils 
{
	/**
	 * 判断给定字符串是否空白串。<br>
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串<br>
	 * 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return boolean
	 */
	public static boolean isBlank( String input ) 
	{
		if ( input == null || "".equals( input ) )
			return true;
		
		for ( int i = 0; i < input.length(); i++ ) 
		{
			char c = input.charAt( i );
			if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
			{
				return false;
			}
		}
		return true;
	}
	
	public static String trim(String as_String) {
		return as_String == null ? "" : as_String.trim();
	}
	
	/**string.xml中替换(*)处的内容
	 * <string name="gps_out_range">暂无城市-(*)的数据</string>
	 * 
	 * @param ctx
	 * @param resouceId  字符串资源
	 * @param replaceContent 要替换的内容
	 * @return 替换好的字符换
	 */
	public static String replaceStringResource(Context ctx,int resouceId,String replaceContent){
		String content = ctx.getString(resouceId);
		content=content.replace("(*)",replaceContent);
		return content;
	}
	
	/**
	 *地区名设置 
	 */
	public static SpannableString areaNameGenerate(String name){
		if(name==null)return new SpannableString("");
		if(name.indexOf("(")==-1)return new SpannableString(name);
		StringBuilder sb = new StringBuilder(name);
		int spiltFirstBracket = sb.indexOf("(");
		int spiltLastBracket = sb.indexOf(")");
		int spiltLength = spiltLastBracket-spiltFirstBracket;
		if(spiltLength>5){
			String ellipsis = "..";
			sb.insert(spiltFirstBracket+5,ellipsis );
			sb.delete(sb.indexOf("(")+5+ellipsis.length(), sb.indexOf(")"));
		}
        sb.insert(sb.indexOf("("), "\n");
        int curBracketIndex = sb.indexOf("(");
        SpannableString spannableString = new SpannableString(sb);
        spannableString.setSpan(new AbsoluteSizeSpan(11, true),curBracketIndex , sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
	}
}
