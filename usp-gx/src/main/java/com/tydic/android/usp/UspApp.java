package com.tydic.android.usp;

import android.app.Application;
import android.util.Log;

import com.tydic.android.rest.RestAndroidClient;
import com.tydic.android.usp.common.AsynImageLoader;

public class UspApp extends Application {

	/**
	 * Tag used for DDMS logging
	 */
	public static String TAG = "usp";

	/**
	 * Singleton pattern
	 */
	private static UspApp instance;
	public static AsynImageLoader imageLoader;

	public static UspApp getInstance() {
		return instance;
	}

	// 图片缓存

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(getClass().getName(), "UspApp::onCreate");
		// Start the service
		Log.i(getClass().getName(), "Starting UspService");
		// startService(new Intent(this, UspService.class));
		imageLoader = new AsynImageLoader();
		instance = this;
		RestAndroidClient.registerAndroidClient(getApplicationContext(), "20140929");
	}

	@Override
	public void onTerminate() {

		super.onTerminate();
	}

}
