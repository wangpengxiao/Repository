package com.tydic.android.usp.uss.request;

/**
 * <pre>
 * Description: 选择套餐的request bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author wangxiao
 * @version V1.0
 * @since 2014-10-3
 */
public class SaleSelectedCodeRequest {
	// Fields 
	private String ofrSubType;// 套餐类型
	private String monthFee;// 月消费金额
	private String monthCallDuration;// 月通话时间
	private String monthNetDuration;// 月上网流量
	private String ofrName;// 套餐名称
	private String teleType;// 电信类型
	private String deviceNumber;// 设备号码

	private String jsessionid;// 当前的sessionId

	// Constructors 

	/** default constructor */
	public SaleSelectedCodeRequest() {
	}

	public String getOfrSubType() {
		return ofrSubType;
	}

	public void setOfrSubType(String ofrSubType) {
		this.ofrSubType = ofrSubType;
	}

	public String getMonthFee() {
		return monthFee;
	}

	public void setMonthFee(String monthFee) {
		this.monthFee = monthFee;
	}

	public String getMonthCallDuration() {
		return monthCallDuration;
	}

	public void setMonthCallDuration(String monthCallDuration) {
		this.monthCallDuration = monthCallDuration;
	}

	public String getMonthNetDuration() {
		return monthNetDuration;
	}

	public void setMonthNetDuration(String monthNetDuration) {
		this.monthNetDuration = monthNetDuration;
	}

	public String getOfrName() {
		return ofrName;
	}

	public void setOfrName(String ofrName) {
		this.ofrName = ofrName;
	}

	public String getTeleType() {
		return teleType;
	}

	public void setTeleType(String teleType) {
		this.teleType = teleType;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

}
