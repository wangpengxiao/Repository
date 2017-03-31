package com.tydic.android.usp.activity.app.domain;




public class LoginUser {
	private String staffId;
	private String staffName;
	private String mobTel;
	private String password;
	
	public String getStaffId() {
		return this.staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return this.staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getMobTel() {
		return this.mobTel;
	}
	public void setMobTel(String mobTel) {
		this.mobTel = mobTel;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "loginUser info --> staffId:" + staffId + "|| staffName:"+staffName + "|| mobel: "+ mobTel;
	}
	
	
}
