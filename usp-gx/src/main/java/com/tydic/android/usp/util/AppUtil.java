package com.tydic.android.usp.util;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.tydic.android.usp.Usp;
import com.tydic.android.usp.UspPreferences;
import com.tydic.android.usp.common.Logger;

public class AppUtil {

	//得到客户端版本
    public static String getApkVersion(Context context){
    	try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
	/**获取渠道号
	 * @param ctx
	 * @return
	 */
	public static String getChanel(Context ctx) {
		String CHANNELID = "000000";
		try {
			ApplicationInfo ai = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(),
					PackageManager.GET_META_DATA);
			Object value = ai.metaData.get("CHANNEL");
			if (value != null) {
				CHANNELID = value.toString();
			}
		} catch (Exception e) {
			//
		}
		return CHANNELID;
	}
	
    //取得CID
	public static String getSpCid(Context context){
    	return ""+UspPreferences.getCID(context);
    }
	
	public static TelephonyManager getTelephonyManager(Context ctx){
		return (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
	}
	
    //得到网络类型
	public static String getNetWorkType(Context context){
    	TelephonyManager telMgr = getTelephonyManager(context);
    	String nt = "";
    	if(telMgr.getNetworkType()==TelephonyManager.NETWORK_TYPE_EDGE){
    		nt = "EDGE";
        } else if (telMgr.getNetworkType()==TelephonyManager.NETWORK_TYPE_GPRS){
        	nt = "GPRS";
        } else if (telMgr.getNetworkType()==TelephonyManager.NETWORK_TYPE_UMTS){
        	nt = "UMTS";
        } else if (telMgr.getNetworkType()==4){
        	nt = "HSDPA";
        } else {
        }
    	return nt;
    }
	
	/**
	 * 获得VersionCode，返回值为版本号，例如：11
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context){
			try {
				return context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
			} catch (NameNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("get versionCode Exception(RuntimeException)");
			}
	}
	/**
	 * 获得versionName，返回值为版本名称，例如：1.3.1
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context){
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("get versionCode Exception(RuntimeException)");
		}
	}
	
	/**
	 * 初始化设备信息
	 */
	public static boolean initMachineInfo(Activity act) {
		if(act==null){return false;}
		try {
			DisplayMetrics displayMetrics = new DisplayMetrics();
			act.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			Usp.MACHINE_SCALE_DENSITY=displayMetrics.scaledDensity;
			Usp.setWindowHeightInPx(displayMetrics.heightPixels);
			Usp.setWindowWidthInPx(displayMetrics.widthPixels);
			Logger.i("matrics", "displayMetrics.toString="+displayMetrics.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 安装apk
	 */
	public static void installApk(Context ctx,File apkfile) {
		if (apkfile != null&&apkfile.exists()) {
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
			ctx.startActivity(i);
		}
	}
	
	//完全退出应用
	public static void exit(final Context mContext){
		//1.先联网注销当前用户
		/**
		 * 替换为广播实现方式
		 */
		Intent intent = new Intent(Usp.EXITACTION); 
		mContext.sendBroadcast(intent);
		//跳转HOME。（防止界面异常跳转）需跟踪测试
		Intent startMain = new Intent(Intent.ACTION_MAIN);   
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		mContext.startActivity(startMain);
		
		//3. 清除掉已有的通知（当前账户的已有的通知）
		System.exit(0);
	}
	
	/**
	 * dip 转换成 px值 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	/*
	public static int dip2px(Context context, float dipValue){ 
		final float scale = context.getResources().getDisplayMetrics().density; 
		return (int)(dipValue * scale + 0.5f); 
	} 
	*/
	public static int dp2Px(Activity mContext, float dps) {
        DisplayMetrics metrics = new DisplayMetrics(); 
        float density = metrics.density;
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        density = metrics.density;
        int pixels = (int)(dps * density);
        return pixels;
	}	
	
	
	/**打电话界面
	 * @param context
	 * @param phoneNum
	 */
	public static void callNum(Context context,String phoneNum){
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:"+phoneNum));
		context.startActivity(intent);
	}
	
}
