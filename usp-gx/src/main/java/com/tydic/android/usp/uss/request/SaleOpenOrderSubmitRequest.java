package com.tydic.android.usp.uss.request;

import java.io.Serializable;

/**
 * <pre>
 * Description: 订单提交信息sale_open_order_submit的request bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author lichao
 * @version V1.0
 * @since 2014-10-7
 */
public class SaleOpenOrderSubmitRequest implements Serializable {

	private static final long serialVersionUID = -5398336649326660062L;

	// Fields

	private String orderNum;// 订单号

	private String idType;// 证件类型

	private String idNumber;// 证件号码

	private String authEndDate;// 证件到期时间

	private String authAdress;// 证件地址

	private String customerName;// 客户名称

	private String custSex;// 客户性别

	private String bornDate;// 出生日期

	private String custPost;// 客户邮编

	private String custEmail;// 客户Email

	private String remarkDesc;// 备注

	private String contactName;// 联系人姓名

	private String contactPhone;// 联系人电话

	private String contactAdress;// 联系人地址

	private String devolopName; // 发展人姓名

	private String devolopPost;// 发展人编码

	private String devolopPhone;// 发展人手机号

	private String devolopChannelId;// 发展渠道编码

	private String devolopChannelName; // 发展渠道名称

	private String creditFirst;// 初始信用度

	private String handlerName;// 经办人姓名

	private String handlerIdType;// 经办人证件类型

	private String handlerIdNumber;// 证件号码

	private String handlerRemarkDesc;// 备注

	private String billSend;// 账单寄送

	private String logisticsType;// 寄送方式

	private String sendContent;// 寄送内容

	private String teleType;// 电信类型

	private String accNbr;// 设备号码

	private String accNbrFee;// 号预存

	private String ofrId;// 套餐ID

	private String terminalType;// 终端机型

	private String fee;// 预存话费

	private String activityId;// 活动ID

	private String paymentType;// 付费类型(2G)

	private String ofrInfo; // 套餐名称

	private String customerId;// 客户ID

	private String jsessionid;// 当前的sesionId

	// Constructors

	/** default constructor */
	public SaleOpenOrderSubmitRequest() {
	}

	// Property accessors

	/**
	 * get订单号
	 */
	public String getOrderNum() {
		return this.orderNum;
	}

	/**
	 * set订单号
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
	 * get证件到期时间
	 */
	public String getAuthEndDate() {
		return this.authEndDate;
	}

	/**
	 * set证件到期时间
	 */
	public void setAuthEndDate(String authEndDate) {
		this.authEndDate = authEndDate;
	}

	/**
	 * get证件地址
	 */
	public String getAuthAdress() {
		return this.authAdress;
	}

	/**
	 * set证件地址
	 */
	public void setAuthAdress(String authAdress) {
		this.authAdress = authAdress;
	}

	/**
	 * get客户名称
	 */
	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * set客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * get客户性别
	 */
	public String getCustSex() {
		return this.custSex;
	}

	/**
	 * set客户性别
	 */
	public void setCustSex(String custSex) {
		this.custSex = custSex;
	}

	/**
	 * get出生日期
	 */
	public String getBornDate() {
		return this.bornDate;
	}

	/**
	 * set出生日期
	 */
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

	/**
	 * get客户邮编
	 */
	public String getCustPost() {
		return this.custPost;
	}

	/**
	 * set客户邮编
	 */
	public void setCustPost(String custPost) {
		this.custPost = custPost;
	}

	/**
	 * get客户Email
	 */
	public String getCustEmail() {
		return this.custEmail;
	}

	/**
	 * set客户Email
	 */
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	/**
	 * get备注
	 */
	public String getRemarkDesc() {
		return this.remarkDesc;
	}

	/**
	 * set备注
	 */
	public void setRemarkDesc(String remarkDesc) {
		this.remarkDesc = remarkDesc;
	}

	/**
	 * get联系人姓名
	 */
	public String getContactName() {
		return this.contactName;
	}

	/**
	 * set联系人姓名
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * get联系人电话
	 */
	public String getContactPhone() {
		return this.contactPhone;
	}

	/**
	 * set联系人电话
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * get联系人地址
	 */
	public String getContactAdress() {
		return this.contactAdress;
	}

	/**
	 * set联系人地址
	 */
	public void setContactAdress(String contactAdress) {
		this.contactAdress = contactAdress;
	}

	/**
	 * get发展人姓名
	 */
	public String getDevolopName() {
		return this.devolopName;
	}

	/**
	 * set发展人姓名
	 */
	public void setDevolopName(String devolopName) {
		this.devolopName = devolopName;
	}

	/**
	 * get发展人编码
	 */
	public String getDevolopPost() {
		return this.devolopPost;
	}

	/**
	 * set发展人编码
	 */
	public void setDevolopPost(String devolopPost) {
		this.devolopPost = devolopPost;
	}

	/**
	 * get发展人手机号
	 */
	public String getDevolopPhone() {
		return this.devolopPhone;
	}

	/**
	 * set发展人手机号
	 */
	public void setDevolopPhone(String devolopPhone) {
		this.devolopPhone = devolopPhone;
	}

	/**
	 * get发展渠道编码
	 */
	public String getDevolopChannelId() {
		return this.devolopChannelId;
	}

	/**
	 * set发展渠道编码
	 */
	public void setDevolopChannelId(String devolopChannelId) {
		this.devolopChannelId = devolopChannelId;
	}

	/**
	 * get发展渠道名称
	 */
	public String getDevolopChannelName() {
		return this.devolopChannelName;
	}

	/**
	 * set发展渠道名称
	 */
	public void setDevolopChannelName(String devolopChannelName) {
		this.devolopChannelName = devolopChannelName;
	}

	/**
	 * get初始信用度
	 */
	public String getCreditFirst() {
		return this.creditFirst;
	}

	/**
	 * set初始信用度
	 */
	public void setCreditFirst(String creditFirst) {
		this.creditFirst = creditFirst;
	}

	/**
	 * get经办人姓名
	 */
	public String getHandlerName() {
		return this.handlerName;
	}

	/**
	 * set经办人姓名
	 */
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	/**
	 * get经办人证件类型
	 */
	public String getHandlerIdType() {
		return this.handlerIdType;
	}

	/**
	 * set经办人证件类型
	 */
	public void setHandlerIdType(String handlerIdType) {
		this.handlerIdType = handlerIdType;
	}

	/**
	 * get证件号码
	 */
	public String getHandlerIdNumber() {
		return this.handlerIdNumber;
	}

	/**
	 * set证件号码
	 */
	public void setHandlerIdNumber(String handlerIdNumber) {
		this.handlerIdNumber = handlerIdNumber;
	}

	/**
	 * get备注
	 */
	public String getHandlerRemarkDesc() {
		return this.handlerRemarkDesc;
	}

	/**
	 * set备注
	 */
	public void setHandlerRemarkDesc(String handlerRemarkDesc) {
		this.handlerRemarkDesc = handlerRemarkDesc;
	}

	/**
	 * get账单寄送
	 */
	public String getBillSend() {
		return this.billSend;
	}

	/**
	 * set账单寄送
	 */
	public void setBillSend(String billSend) {
		this.billSend = billSend;
	}

	/**
	 * get寄送方式
	 */
	public String getLogisticsType() {
		return this.logisticsType;
	}

	/**
	 * set寄送方式
	 */
	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	/**
	 * get寄送内容
	 */
	public String getSendContent() {
		return this.sendContent;
	}

	/**
	 * set寄送内容
	 */
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	/**
	 * get电信类型
	 */
	public String getTeleType() {
		return this.teleType;
	}

	/**
	 * set电信类型
	 */
	public void setTeleType(String teleType) {
		this.teleType = teleType;
	}

	/**
	 * get设备号码
	 */
	public String getAccNbr() {
		return this.accNbr;
	}

	/**
	 * set设备号码
	 */
	public void setAccNbr(String accNbr) {
		this.accNbr = accNbr;
	}

	/**
	 * get号预存
	 */
	public String getAccNbrFee() {
		return this.accNbrFee;
	}

	/**
	 * set号预存
	 */
	public void setAccNbrFee(String accNbrFee) {
		this.accNbrFee = accNbrFee;
	}

	/**
	 * get套餐ID
	 */
	public String getOfrId() {
		return this.ofrId;
	}

	/**
	 * set套餐ID
	 */
	public void setOfrId(String ofrId) {
		this.ofrId = ofrId;
	}

	/**
	 * get终端机型
	 */
	public String getTerminalType() {
		return this.terminalType;
	}

	/**
	 * set终端机型
	 */
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	/**
	 * get预存话费
	 */
	public String getFee() {
		return this.fee;
	}

	/**
	 * set预存话费
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}

	/**
	 * get活动ID
	 */
	public String getActivityId() {
		return this.activityId;
	}

	/**
	 * set活动ID
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * get付费类型(2G)
	 */
	public String getPaymentType() {
		return this.paymentType;
	}

	/**
	 * set付费类型(2G)
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * get套餐名称
	 */
	public String getOfrInfo() {
		return this.ofrInfo;
	}

	/**
	 * set套餐名称
	 */
	public void setOfrInfo(String ofrInfo) {
		this.ofrInfo = ofrInfo;
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

	/**
	 * get客户ID
	 */
	public String getCustomerId() {
		return this.customerId;
	}

	/**
	 * set客户ID
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
