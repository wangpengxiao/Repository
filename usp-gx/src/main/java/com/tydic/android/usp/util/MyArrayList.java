package com.tydic.android.usp.util;

import java.util.ArrayList;
import java.util.Collection;

import com.tydic.android.usp.UspPreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**添加时只能使用 public boolean add(String object) 方法
 * 添加 加在最前面。
 * 删除时，public boolean remove(Object object) 
 * 		   public String remove(int index)
 * 这两个都能用！
 *  
 * @author zhangchangsheng
 *
 */
public class MyArrayList extends ArrayList<String>{
	//本地保存的条数 ，保存 后几条（最新的）。。
	public static final int save_count = 5;
	//是否 与本地同步 的开关
	private boolean is_save_flag = true;
	
	// SEPARATOR 和 REGULAR_SEPARATOR必须对应！！！
	//保存时候用的分隔符
	public static final String SEPARATOR = "*;*";
	//获取数据时候用的分隔符，因为获取时是用的正则表达式，所以，需要转义
	public static final String REGULAR_SEPARATOR = "\\*;\\*";
	public static final String KEY_MYARRAYLIST_HISTORY_EXPRESS_NUMBER = "key_myArrayList_history_express_number"; 
	private Context mContext;
	
	private MyArrayList(Context context) {
		super();
		this.mContext = context;
	}

	public MyArrayList(Context context,Collection<String> collection) {
		super(collection);
		this.mContext = context;
	}

	private MyArrayList(Context context,int capacity) {
		super(capacity);
		this.mContext = context;
	}
	
	public void openSave(){
		this.is_save_flag = true;
	}
	public void closeSave(){
		this.is_save_flag = false;
	}
	
	@Override
	public boolean add(String object) {
		if(this.contains(object)){//包含有
			super.remove(object);
		}
		super.add(0, object);
		//如果多于 最大数目，则删除最后一个
		while(this.size()>save_count){
			this.remove(this.size()-1);
		}
		
		//同步更新 本地 的数据
		return saveToSharePreferences();
	}

	@Override
	public void add(int index, String object) {
		super.add(index, object);
		//如果多于 最大数目，则删除最后一个
		while(this.size()>save_count){
			this.remove(this.size()-1);
		}
		saveToSharePreferences();
	}

	@Override
	public boolean addAll(Collection<? extends String> collection) {
		boolean result =  super.addAll(collection);
		saveToSharePreferences();
		return result;
	}

	@Override
	public boolean addAll(int location, Collection<? extends String> collection) {
		boolean result = super.addAll(location, collection);
		saveToSharePreferences();
		return result;
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public String remove(int index) {
		String result = super.remove(index);
		saveToSharePreferences();
		return result;
	}

	@Override
	public boolean remove(Object object) {
		boolean result = super.remove(object);
		saveToSharePreferences();
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		return super.removeAll(collection);
	}
	
	/**
	 * 保存到本地 SharePreferences
	 * @return
	 */
	private boolean saveToSharePreferences(){
		if(is_save_flag){
			StringBuffer sb = new StringBuffer();
			//如果 flag为true了，则 最后肯定多一个 分隔符，所以就要去掉
			boolean flag = false;
			int length = this.size();
			String express_number = null;
			for(int i=0;((i<length) && (i<save_count));i++){
				express_number = this.get(i);
				if(express_number!=null && (!"".equals(express_number.trim()))){
					sb.append(express_number).append(SEPARATOR);
					flag = true;
				}
			}
			String resultStr = "";
			if(flag){//flag为true说明有内容
				//截取末尾的分隔符
				resultStr = sb.substring(0, sb.lastIndexOf(SEPARATOR));
			}
			if(this.mContext!=null){
				SharedPreferences sp = mContext.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, Context.MODE_WORLD_WRITEABLE);
				return sp.edit().putString(KEY_MYARRAYLIST_HISTORY_EXPRESS_NUMBER,resultStr).commit();
			}else{
				throw new RuntimeException(this.getClass().getName()+"的成员变量 mContext为null!!");
			}
		}else{
			return true;
		}
	}
	/**
	 * 从SharePreferences读取数据到 list中
	 * @return
	 */
	private static MyArrayList reLoadData(Context context,MyArrayList malist){
		if(context==null || malist==null)return null;
		
		malist.clear();
		
		malist.closeSave();

		SharedPreferences sp = context.getSharedPreferences(UspPreferences.USP_SHAREDPREFERENCES_NAME, context.MODE_WORLD_READABLE);
		String allStrExpress = sp.getString(KEY_MYARRAYLIST_HISTORY_EXPRESS_NUMBER, null);
		if(allStrExpress!=null&& (!"".equals(allStrExpress.trim()))){
			//有历史记录,使用seprator进行分割
			String[] expresses1= allStrExpress.split(REGULAR_SEPARATOR);
			int lastLocation = expresses1.length-1;
			//添加到 ArrayList中
			for(int i=lastLocation;i>=0;i--){
				if(expresses1[i]!=null && (!"".equals(expresses1[i].trim()))){
					malist.add(expresses1[i]);
				}
			}
		}else{/**没有保存的历史记录*/}
		
		malist.openSave();
		return malist;
	}
	/**
	 * 创建实例并  从 SharedPrefrences 中读取数据到 其中
	 * @param context
	 * @return
	 */
	public static MyArrayList createAndLoadData(Context context){
		if(context ==null)return null;
		MyArrayList malist = new MyArrayList(context);
		return reLoadData(context,malist);
	}
}
