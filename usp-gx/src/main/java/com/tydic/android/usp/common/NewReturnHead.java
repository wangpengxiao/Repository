package com.tydic.android.usp.common;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * 新的响应 头信息
 */
public class NewReturnHead implements Serializable{
	private static final long serialVersionUID = 1L;
	@Expose
	public String cid;
	@Expose
	public String userid;
	@Expose
	public String code;
	@Expose
	public String errormsg;
	@Expose
	public ClientUpdate clientUpdate;
	@Expose
	public String showMsg;//失败信息
	
	/**
	 * 更新信息
	 *
	 */
	public static class ClientUpdate implements Serializable{
		private static final long serialVersionUID = 1L;
		@Expose
		public String remark;
		@Expose
		public String versionId;
		@Expose
		public String time;
		@Expose
		public String isForce;
		@Expose
		public String downloadUrl;
	}
	/**
	 * 根据 服务器响应的头信息中的 code 来判断是否成功
	 * code = 1  代表成功
	 * code = 0 代表失败
	 * @return
	 */
	public boolean isSuccessByCode(){
		if(code==null)return false;
		return "1".equals(code.trim());
	}
	
	/**
	 * 根据 clientUpdate 是否 为 null来判断是否 需要更新，
	 * 如果没有更新信息，则认为 当前客户端是最新版本
	 * @return
	 */
	public boolean isAppNewest(){
		return clientUpdate == null;
	}
}
