package com.tydic.android.usp.common;

import com.tydic.android.usp.Usp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class UspBroadcastReceiver extends BroadcastReceiver{

	private Activity activity;
	
	public UspBroadcastReceiver(Activity activity){
		this.activity = activity;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String actionname = intent.getAction();
		if(actionname.equals(Usp.EXITACTION)){
			Log.d("BroadcastReceiver", "activity "+activity.getLocalClassName()+"is exit...");
			activity.finish();
		}
		if(actionname.equals(Usp.LOGOUTACTION)){
			Log.d("BroadcastReceiver", "activity "+activity.getLocalClassName()+"is logout...");
			activity.finish();
		}
	}
	
}
