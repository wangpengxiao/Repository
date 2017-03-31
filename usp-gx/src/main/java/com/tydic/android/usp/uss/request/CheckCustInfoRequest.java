package com.tydic.android.usp.uss.request;

import java.io.Serializable;

/**
 * <pre>
 * Description: 客户资料校验请求信息check_cust_info的request bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author lichao
 * @version V1.0
 * @since 2014-10-7
 */
public class CheckCustInfoRequest implements Serializable {

	private static final long serialVersionUID = -4130421370744723745L;

	// Fields

	private String operId;// 操作员ID

	private String province;// 省份

	private String city;// 城市

	private String district;// 区县

	private String operDept;// 渠道编码

	private String channelType;// 渠道类型

	private String accessType;// 接入类型

	private String idType;// 证件类型

	private String idNumber;// 证件号码

	private String jsessionid;// 当前的sesionId

	// Constructors

	/** default constructor */
	public CheckCustInfoRequest() {
	}

	// Property accessors

	/**
	 * get操作员ID
	 */
	public String getOperId() {
		return this.operId;
	}

	/**
	 * set操作员ID
	 */
	public void setOperId(String operId) {
		this.operId = operId;
	}

	/**
	 * get省份
	 */
	public String getProvince() {
		return this.province;
	}

	/**
	 * set省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * get城市
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * set城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * get区县
	 */
	public String getDistrict() {
		return this.district;
	}

	/**
	 * set区县
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * get渠道编码
	 */
	public String getOperDept() {
		return this.operDept;
	}

	/**
	 * set渠道编码
	 */
	public void setOperDept(String operDept) {
		this.operDept = operDept;
	}

	/**
	 * get渠道类型
	 */
	public String getChannelType() {
		return this.channelType;
	}

	/**
	 * set渠道类型
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	/**
	 * get接入类型
	 */
	public String getAccessType() {
		return this.accessType;
	}

	/**
	 * set接入类型
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	/**
	 * get证件类型
	 */
	public String getIdType() {
		return this.idType;
	}

	/**
	 * set证件类型
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	/**
	 * get证件号码
	 */
	public String getIdNumber() {
		return this.idNumber;
	}

	/**
	 * set证件号码
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * get当前的sesionId
	 */
	public String getJsessionid() {
		return this.jsessionid;
	}

	/**
	 * set当前的sesionId
	 */
	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

}
