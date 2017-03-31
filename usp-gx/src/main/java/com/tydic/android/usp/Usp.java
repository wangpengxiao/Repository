package com.tydic.android.usp;

import android.app.Activity;

import com.tydic.android.usp.util.AppUtil;


/**
 * 转换类，配置信息
 * @function : 
 */
public class Usp{
	/**DEBUG日志输出开关*/
	public static final boolean DEBUG = true; 
	
	public volatile static float totalKB=0f;
	
	//百度定位key
	public static String LOCATION_KEY = "d988665fd6d98bbf025e87af56d44215";
	
	//应用更新、更多应用下载 时 的超时时间 ，单位为 秒
	public static final int DOWNLOAD_APP_TIMEOUT_LENGTH = 20;
	
	//退出广播Action---
	public static final String EXITACTION = "usp.android.exit."+Usp.class.getPackage().getName();
	//注销广播Action
	public static final String LOGOUTACTION = "usp.android.logout."+Usp.class.getPackage().getName();
	//更新广播Action
	public static final String UPDATE_ACTION = "usp.android.update."+Usp.class.getPackage().getName();
	
	public static int localVersion = 1;
    public static int serverVersion = 1;
    public static final String APP_NAME = "易运营";
	//下载文件保存路径
	public static final String DOWNLOAD_DIR = "/usp_app/download";
	public static final String IMGFILE_DIR = "/usp_app/img";
	//------------------------配置信息
	//手机
	public static float MACHINE_SCALE_DENSITY;//machine_scale_density
	private static int MACHINE_WINDOW_HEIGHT_PX;//屏幕高度 px
	private static int MACHINE_WINDOW_WIDTH_PX;//屏幕宽度 px
	
	public synchronized static float getTotalKB() {
		return totalKB;
	}
	
	public synchronized static void add(float increment){
		totalKB+=increment;
	}

	/**
	 * 注册和登录时 用户名 输入框，输入@后 自动提示的邮箱后缀名集合
	 */
	public static final String[] emailEnds = { 
		"qq.com", 
		"126.com",
		"163.com",
		"sina.com", 
		"gmail.com", 
		"yahoo.com.cn",
		"hotmail.com",
		"139.com",
		"sohu.com" };

	
	/**
	 *  获取屏幕 高度 
	 * @param act 不能为null
	 * @return
	 */
	public static int getWindowHeightInPx(Activity act){
		if(act==null)return 480;
		if(MACHINE_WINDOW_HEIGHT_PX==0){
			AppUtil.initMachineInfo(act);
		}
		return MACHINE_WINDOW_HEIGHT_PX;
	}
	/**
	 * 获取屏幕 宽度
	 * @param act 不能为null
	 * @return
	 */
	public static int getWindowWidthInPx(Activity act){
		if(act==null)return 320;
		if(MACHINE_WINDOW_WIDTH_PX==0){
			AppUtil.initMachineInfo(act);
		}
		return MACHINE_WINDOW_WIDTH_PX;
	}
	
	/**
	 * 设置 屏幕 高度
	 * @param height
	 */
	public static void setWindowHeightInPx(int height){
		MACHINE_WINDOW_HEIGHT_PX = height;
	}
	/**
	 * 设置屏幕 宽度 
	 * @param width
	 */
	public static void setWindowWidthInPx(int width){
		MACHINE_WINDOW_WIDTH_PX = width;
	}
	
}
