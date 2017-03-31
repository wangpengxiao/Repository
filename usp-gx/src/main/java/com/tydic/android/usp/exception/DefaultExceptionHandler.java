package com.tydic.android.usp.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.content.Context;

/**
 * 未捕获异常处理
 */
public class DefaultExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
	private final Context myContext;

	public DefaultExceptionHandler(Context context) {
		myContext = context;
	}

	public void uncaughtException(Thread thread, Throwable exception) {
		StringWriter stackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTrace));
		System.err.println(stackTrace);

		
//		
//		Intent intent = new Intent(//跳转到出错HTML文件 目前无
//			"android.fbreader.action.CRASH",
//			new Uri.Builder().scheme(exception.getClass().getSimpleName()).build()
//		);
//		try {
//			myContext.startActivity(intent);
//		} catch (ActivityNotFoundException e) {
//			intent = new Intent(myContext, VanclActivityGroup.class);
//			intent.putExtra(VanclActivityGroup.INTENT_TYPE, VanclActivityGroup.FORCE_EXIT_ID);
//			myContext.startActivity(intent);
//		}
//		if (myContext instanceof Activity) {
//			((Activity)myContext).finish();
//		}
//
//
//		Process.killProcess(Process.myPid()); //linux方法，关掉进程，但android管理，会重启原有activity
		System.exit(10);
	}
}
