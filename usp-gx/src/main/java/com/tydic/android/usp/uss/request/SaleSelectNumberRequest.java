package com.tydic.android.usp.uss.request;

/**
 * <pre>
 * Description: 选择号码的request bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author wangxiao
 * @version V1.0
 * @since 2014-10-3
 */
public class SaleSelectNumberRequest {
	
	// Fields 
	private String teleType;// 电信类型
	private String mobSection;// 号段
	private String priceRange;// 预存话费
	private String perrtyType;// 靓号类型（3G）
	private String selectCount;// 查询号码总数

	private String jsessionid;// 当前的sessionId

	// Constructors 

	/** default constructor */
	public SaleSelectNumberRequest() {
	}



	public String getTeleType() {
		return teleType;
	}



	public void setTeleType(String teleType) {
		this.teleType = teleType;
	}



	public String getMobSection() {
		return mobSection;
	}



	public void setMobSection(String mobSection) {
		this.mobSection = mobSection;
	}



	public String getPriceRange() {
		return priceRange;
	}



	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}



	public String getPerrtyType() {
		return perrtyType;
	}



	public void setPerrtyType(String perrtyType) {
		this.perrtyType = perrtyType;
	}



	public String getSelectCount() {
		return selectCount;
	}



	public void setSelectCount(String selectCount) {
		this.selectCount = selectCount;
	}



	public String getJsessionid() {
		return jsessionid;
	}



	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

}
