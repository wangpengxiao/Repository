package com.tydic.android.usp.common;

import com.tydic.android.usp.Usp;

import android.util.Log;


/**
 * @author zhangkun
 * @version 1.0
 * @since JDK1.6, 2011-8-12
 * 2011-11-25
 */
public final class Logger {
	/**
	 * 日志开关
	 */
//	public static final boolean DEGUG = true;
	
	public static void i(String msg) {
		if(Usp.DEBUG) {
			System.out.println(msg);
		}
	}
	
	public static void i(String tag, String msg) {
		if(Usp.DEBUG) {
			Log.i(tag, msg);
		}
	}
	
	public static void v(String tag, String msg) {
		if(Usp.DEBUG) {
			Log.v(tag, msg);
		}
	}
	
	public static void d(String tag, String msg) {
		if(Usp.DEBUG) {
			Log.d(tag, msg);
		}
	}
	
	public static void w(String tag, String msg) {
		if(Usp.DEBUG) {
			Log.w(tag, msg);
		}
	}
	
	public static void e(String tag, String msg) {
		if(Usp.DEBUG) {
			Log.e(tag, msg);
		}
	}
	public static void e(String tag, String msg, Throwable tr){
		if(Usp.DEBUG) {
			Log.e(tag, msg, tr);
		}
	}
}
