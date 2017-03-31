package com.tydic.android.usp.common;

import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tydic.android.usp.security.Des3Encrypt;

/**请求数据   包含mobileHead为requestHead mobileBody为请求参数
 * @author zhangkune
 *
 */
public class RequestNetData {
	/**
	 * 请求数据加密开关   true-加密  false-不加密
	 */
	public static final boolean IS_ENCRYPTED=true;
	
	@SerializedName("mobileHead")@Expose
	public RequestHead mobileHead; //head
	@SerializedName("mobileBody")@Expose
	public JsonElement mobileBodyForJson; //body


	
	/**VanclNetManage中使用的 NameValuePair存储的键值对
	 * @param nameValues
	 */
	
	public static class MobileBodyNameValues extends HashMap<String,String>{
		private static final long serialVersionUID = 1L;

		/**添加请求body体
		 * @param nameValues
		 */
		public void addMobileBody(List<NameValuePair> nameValues){
			for(NameValuePair nValuePair:nameValues){
				put(nValuePair.getName(), nValuePair.getValue());
			}
		}
	}
	
	public String toJson(){

		 Logger.d("Request", "mobileHead = "+mobileHead.toString());
		 if(mobileBodyForJson!=null)Logger.d("Request", "mobileBodyForJson = "+mobileBodyForJson.toString());

		 GsonBuilder gsonBuilder = new GsonBuilder();
	     Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
	     
	     String paramStr = "";
	     if(IS_ENCRYPTED){
	    	 paramStr = new Des3Encrypt().encryptStr(gson.toJson(this));
	     }else{
	    	 paramStr= gson.toJson(this);
	     }	 
	     
	     return paramStr;
	}
}
