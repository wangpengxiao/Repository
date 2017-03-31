package com.tydic.android.rest;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class UssUtils {
	
	public static String getDeviceId(Context context) {
		String deviceId = null;
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (manager != null) {
			deviceId = manager.getDeviceId();
		}
		if (deviceId != null) {
			return "IMEI:" + deviceId;
		}
		deviceId = Secure.getString(context.getContentResolver(),
				Secure.ANDROID_ID);
		if (deviceId != null) {
			return "ANDROID:" + deviceId;
		}
		deviceId = Installation.id(context);
		return "UUID:" + deviceId;
	}
}
