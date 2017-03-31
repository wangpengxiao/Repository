package com.tydic.android.usp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

public class UspService extends Service {

	private final IBinder mBinder = new UspBinder();
	// Wakelock
	PowerManager.WakeLock wakelock = null;
	// Client states
	private static int STATE_RAW = -1;
	private static int STATE_INITIALIZED = 0;
	private static int CLIENT_STATE = STATE_RAW;

	private static boolean authorized = true;

	public class UspBinder extends Binder {
		public UspService getService() {
			return UspService.this;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.d(getClass().getName(), "UspService.onBind");
		return mBinder;
	}

	@Override
	public void onCreate() {
		Log.i(getClass().getName(), "UspService.onCreate");
		super.onCreate();
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakelock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass()
				.getName());
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// super.onStart(intent, startId);
		Log.i(getClass().getName(), "UspService.onStart");
		Log.i(getClass().getName(), "Client state: "
				+ UspService.CLIENT_STATE);
		// Was the service already started?
		if (UspService.CLIENT_STATE != STATE_RAW) {
			Log.i(getClass().getName(),
					"UspService already started once, so just return");
			return;
		} else {
			Log.i(getClass().getName(), "UspService is initializing");
			// Service has been started once and considered initialized
			UspService.CLIENT_STATE = STATE_INITIALIZED;

		}
	}

	public static void setAuthorized(boolean authorized) {
		UspService.authorized = authorized;
	}

	public static boolean isAuthorized() {
		return authorized;
	}
	

}
