package com.tydic.android.usp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkInfo {
	 /**
     * 判断网络是否连接
     * @param context Context
     * @return true ---连接
     *         false --无连接
     */
    public static boolean isNetAvailable(Context context){
    	ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	if(connectivityManager==null){
    		return false;
    	}else{
    		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    		if(networkInfo!=null&& networkInfo.isConnected()){
    			if(networkInfo.getState()== NetworkInfo.State.CONNECTED) return true;
    		}
    		return false;
    	}
    }
}
