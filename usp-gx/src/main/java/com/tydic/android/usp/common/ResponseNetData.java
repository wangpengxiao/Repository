package com.tydic.android.usp.common;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseNetData<T>{
	/**
	 * 请求数据加密开关   true-加密  false-不加密
	 */
	public static final boolean IS_ENCRYPTED=false;
	
	@SerializedName("mobileHead")@Expose
	public NewReturnHead mobileHead; //head
	@SerializedName("mobileBody")@Expose
	public T mobileBodyForJson; //body
}
