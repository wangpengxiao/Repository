/**
 * 
 */
package com.tydic.android.usp.util;

/**
 * @author liuyq
 *
 */
public class ReflectUtil {
	
	private static String GET_MEHOD = "get";
	private static String SET_MEHOD = "set";
	
	public static String getMethodGetStr(String fieldName) {
		if(fieldName == null){
			return null;
		}
		return GET_MEHOD + convertStr(fieldName);
	}
	
	public static String getMethodSetStr(String fieldName) {
		if(fieldName == null){
			return null;
		}
		return SET_MEHOD + convertStr(fieldName);
	}
	
	private static String convertStr(String fieldName){
		//第二个字母大写情况
		if(fieldName.length() > 1 && Character.isUpperCase(fieldName.charAt(1))){
			return fieldName;
		}
		return fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
	}
	
	

}
