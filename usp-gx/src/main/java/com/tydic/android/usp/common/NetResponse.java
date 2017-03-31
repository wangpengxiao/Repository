package com.tydic.android.usp.common;



public class NetResponse{
	private boolean result;
	private String data;
	private String reason;
	private ReturnHead returnHead;
	
	public void setResult(boolean result) {
		this.result = result;
	}
	public boolean isResultSuc() {
		return result;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReason() {
		return reason;
	}
	public ReturnHead getReturnHead() {
		return returnHead;
	}
	public void setReturnHead(ReturnHead returnHead) {
		this.returnHead = returnHead;
	}
	
	
	
}
