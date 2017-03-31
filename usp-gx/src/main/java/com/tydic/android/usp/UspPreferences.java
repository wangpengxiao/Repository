package com.tydic.android.usp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.tydic.android.usp.R;
import com.tydic.android.usp.activity.app.domain.LoginUser;


public class UspPreferences {
	
	private static final int ZERO = 0;
	
	public static final String USP_SHAREDPREFERENCES_NAME = "usp_sp";
	//用户UUID
	private static final String KEY_UUID = "uuid";
	//引导界面KEY
	private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
	//更新框 不再提示 值
	private static final String KEY_UPDATE_DIALOG_SHOWN = "update_dialog_shown";
	
	public static final String USERNAME = "userCode";
	public static final String PASSWORD = "password";
	public static final String REMEMBER_PASSWORD = "rememberpwd";
	public static final String AUTO_LOGIN = "auto_login";
	public static final String CID = "cid";
	public static final String USER_ID ="userID";
	
	private static LoginUser staffInfo;

	/**设置更新框 不再提示 值
	 * @param context
	 * @param shown
	 * @return
	 */
	public static boolean setShownUpdateDialog(Context context,boolean shown){
		//空值判断
		if(context==null)return false;
		
		SharedPreferences sPreferences = context.getSharedPreferences(USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_WRITEABLE);
		Editor editor = sPreferences.edit()
									.putBoolean(KEY_UPDATE_DIALOG_SHOWN, shown);
		return editor.commit();
	}
	/**
	 * 获取更新框 不再提示 值
	 * @param context
	 * @return cityName
	 */
	public static boolean isShownUpdateDialog(Context context){
		if(context==null)return true;
		SharedPreferences sharedPreferences = context.getSharedPreferences(USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE);
		return sharedPreferences.getBoolean(KEY_UPDATE_DIALOG_SHOWN, true);
	}
	
	/**
	 * 判断activity是否引导过
	 * 
	 * @param context
	 * @return  是否已经引导过 true引导过了 false未引导
	 */
	public static boolean activityIsGuided(Context context,String className){
		if(context==null || className==null||"".equalsIgnoreCase(className))return false;
		String[] classNames = context.getSharedPreferences(USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE)
		 		.getString(KEY_GUIDE_ACTIVITY, "").split("\\|");
		 for (String string : classNames) {
			if(className.equalsIgnoreCase(string)){
				return true;
			}
		}
		  return false;
	}
	
	/**设置该activity被引导过了
	 * @param context
	 * @param className
	 */
	public static void setIsGuided(Context context,String className){
		if(context==null || className==null||"".equalsIgnoreCase(className))return;
		String classNames = context.getSharedPreferences(USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE)
		 		.getString(KEY_GUIDE_ACTIVITY, "");
		StringBuilder sb = new StringBuilder(classNames).append("|").append(className);
		context.getSharedPreferences(USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE)
		.edit()
		.putString(KEY_GUIDE_ACTIVITY, sb.toString())
		.commit();
	}
	
	/**
	 * 获取 uuid
	 * 
	 * @param context
	 * @return
	 */
	public static String getUserUUID(Context context){
		if(context==null)return null;
		String uuid = context.getSharedPreferences(USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE)
		 		.getString(KEY_UUID, "");
		if("".equals(uuid)){
			uuid = java.util.UUID.randomUUID().toString();
			context.getSharedPreferences(USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_WRITEABLE)
				.edit()
				.putString(KEY_UUID, uuid)
				.commit();
		}
		return uuid;
	}
	
	/**获取userId
	 * @param context
	 * @return
	 * FIXME 偏好
	 */
	public static int getUserID(Context context){
		return context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE).getInt(USER_ID,0);
	}
	
	/**
	 * @param context
	 * @param userID
	 * @return
	 * FIXME 偏好
	 */
	public static boolean saveUserID(Context context,int userID){
		return context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_WRITEABLE)
		.edit()
		.putInt(USER_ID, userID)
		.commit();
	}
	
	/**
	 * 登录界面 状态保存
	 * 直接保存userName/rememberPwd/autoLogin，如果rememberPwd为true，则保存md5加密的密码，否则，则清除本地保存的密码
	 */
	public static void  savedState(Context context,String userName,String md5Password,boolean rememberPwd,boolean autoLogin){
		SharedPreferences spPreferences = context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_WRITEABLE);
		Editor editor = spPreferences.edit()
					.putString(USERNAME, userName)
					.putBoolean(REMEMBER_PASSWORD, rememberPwd)
					.putBoolean(AUTO_LOGIN, autoLogin);
		if(rememberPwd){
			editor.putString(PASSWORD, md5Password);
		}else{
			editor.remove(PASSWORD);
		}
					
		editor.commit();
		
	}
	/**
	 * 登录界面状态显示时读取 数据
	 * @param context
	 * @return  Map 的 key来自于 Preferences中的静态常量
	 */
	public static Map<String,Object> getState(Context context){   
		Map<String,Object> map = new HashMap<String, Object>();
		SharedPreferences spPreferences = context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE);
		map.put(USERNAME, spPreferences.getString(USERNAME, ""));
		map.put(PASSWORD, spPreferences.getString(PASSWORD, ""));
		map.put(REMEMBER_PASSWORD, spPreferences.getBoolean(REMEMBER_PASSWORD, false));
		map.put(AUTO_LOGIN, spPreferences.getBoolean(AUTO_LOGIN, false));
	
		return map;
	}
	
	public static String getUserName(Context context){
		SharedPreferences spPreferences = context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE);
		return spPreferences.getString(USERNAME, "");
		
	}
	/**
	 * 保存用户名
	 * @param context
	 * @return
	 */
	public static boolean saveUserName(Context context,String username){
		SharedPreferences spPreferences = context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_WRITEABLE);
		return spPreferences.edit().putString(USERNAME, username).commit();
	}
	
	//保存CID
	public static void saveCID(Context context,String cid){
		SharedPreferences spPreferences = context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_WRITEABLE);
		spPreferences.edit()
					.putString(CID, cid)
					.commit();
	}
	
	public static String getCID(Context context){
		SharedPreferences spPreferences = context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE);
		return spPreferences.getString(CID, "");
	}
	
	public static void clearUserState(Context context){   
	       SharedPreferences spPreferences = context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_WRITEABLE);
	       spPreferences.edit().remove(USERNAME)
	                         .remove(PASSWORD)
	                         .remove(REMEMBER_PASSWORD)
	                         .remove(AUTO_LOGIN)
	                         .commit();
	}
	
	public static LoginUser getStaffInfo(){
		return staffInfo;
	}
	
	public static void setStaffInfo(LoginUser staffInfo){
		UspPreferences.staffInfo= staffInfo; 
	}
	
	//获取List数据（套餐导购）
	public static List<Map<String, Object>> getPackageShoppingListData(){

		List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
		//1
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_activity);		// 加载图片资源
		map.put("TITLE", "社区营销活动");
		map.put("CONTENT", "社区营销活动介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchPlanActivity");
		mList.add(map);
		//2
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_channel);		// 加载图片资源
		map.put("TITLE", "渠道信息");
		map.put("CONTENT", "渠道信息介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.QueryChannelActivity");
		mList.add(map);
		//3
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_competitor);
		map.put("TITLE", "竞争者查询");
		map.put("CONTENT", "竞争者查询介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchCompetitorActivity");
		mList.add(map);		
		//4
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_competitor_edit);
		map.put("TITLE", "竞争者录入");
		map.put("CONTENT", "竞争者录入介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchGridActivity");
		mList.add(map);
		//5
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_shaker);
		map.put("TITLE", "搖一搖");
		map.put("CONTENT", "搖一搖");
		map.put("activity", "com.tydic.android.usp.activity.shake.ShakerActivity");
		mList.add(map);
		//6
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_map);
		map.put("TITLE", "移动地图");
		map.put("CONTENT", "移动地图");
		map.put("activity", "com.tydic.android.usp.activity.map.MapActivity");
		mList.add(map);
		
		
		//1
		map.put("PIC", R.drawable.icon_activity);		// 加载图片资源
		map.put("TITLE", "社区营销活动");
		map.put("CONTENT", "社区营销活动介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchPlanActivity");
		mList.add(map);
		//2
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_channel);		// 加载图片资源
		map.put("TITLE", "渠道信息");
		map.put("CONTENT", "渠道信息介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.QueryChannelActivity");
		mList.add(map);
		//3
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_competitor);
		map.put("TITLE", "竞争者查询");
		map.put("CONTENT", "竞争者查询介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchCompetitorActivity");
		mList.add(map);		
		//4
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_competitor_edit);
		map.put("TITLE", "竞争者录入");
		map.put("CONTENT", "竞争者录入介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchGridActivity");
		mList.add(map);
		//5
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_shaker);
		map.put("TITLE", "搖一搖");
		map.put("CONTENT", "搖一搖");
		map.put("activity", "com.tydic.android.usp.activity.shake.ShakerActivity");
		mList.add(map);
		//6
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_map);
		map.put("TITLE", "移动地图");
		map.put("CONTENT", "移动地图");
		map.put("activity", "com.tydic.android.usp.activity.map.MapActivity");
		mList.add(map);		
		
		
		
		
		return mList;
	}		
	
	//获取标签List数据（移动运营）
	public static List<Map<String, Object>> gettab1ListData(){

		List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
		//1
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_activity);		// 加载图片资源
		map.put("TITLE", "社区营销活动");
		map.put("CONTENT", "社区营销活动介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchPlanActivity");
		mList.add(map);
		//2
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_channel);		// 加载图片资源
		map.put("TITLE", "渠道信息");
		map.put("CONTENT", "渠道信息介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.QueryChannelActivity");
		mList.add(map);
		//3
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_competitor);
		map.put("TITLE", "竞争者查询");
		map.put("CONTENT", "竞争者查询介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchCompetitorActivity");
		mList.add(map);		
		//4
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_competitor_edit);
		map.put("TITLE", "竞争者录入");
		map.put("CONTENT", "竞争者录入介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchGridActivity");
		mList.add(map);
		//5
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_shaker);
		map.put("TITLE", "搖一搖");
		map.put("CONTENT", "搖一搖");
		map.put("activity", "com.tydic.android.usp.activity.shake.ShakerActivity");
		mList.add(map);
		//6
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_map);
		map.put("TITLE", "移动地图");
		map.put("CONTENT", "移动地图");
		map.put("activity", "com.tydic.android.usp.activity.map.MapActivity");
		mList.add(map);
		return mList;
	}	
	
	//获取标签Grid数据（移动运营）
	public static List<Map<String, Object>> gettab1GridData(){

		List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
		//1
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_activity);		// 加载图片资源
		map.put("TITLE", "社区营销活动");
		map.put("CONTENT", "社区营销活动介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.SearchPlanActivity");
		mList.add(map);
		//2
		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_channel);		// 加载图片资源
		map.put("TITLE", "渠道信息");
		map.put("CONTENT", "渠道信息介绍");
		map.put("activity", "com.tydic.android.usp.activity.app.QueryChannelActivity");
		mList.add(map);
		//3

		
		
		return mList;
	}
	

	
}
