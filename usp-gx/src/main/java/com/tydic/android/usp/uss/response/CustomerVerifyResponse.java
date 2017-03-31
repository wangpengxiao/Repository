package com.tydic.android.usp.uss.response;

import java.io.Serializable;
import java.util.List;

import android.util.Log;

/**
 * <pre>
 * Description: 客户资料校验返回信息customer_verify_vo的response bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author lichao
 * @version V1.0
 * @since 2014-10-7
 */
public class CustomerVerifyResponse implements Serializable {

	private static final long serialVersionUID = 1271302019166023414L;

	// Fields

	private String custInfoCheck;// 客户校验

	private String customerId;// 客户ID

	private String blackListFlag;// 是否黑名单标识

	private String maxUserFlag;// 是否达到最大用户数标识

	private String arrearageFlag;// 是否欠费标识

	private List<ArrearageMesss> arrearageMesss;// 欠费信息

	private String customerType;// 客户类型

	private String customerName;// 客户名称

	private String customerAddr;// 客户地址

	private String contactPerson;// 联系人

	private String contactPhone;// 联系人电话

	private String contactAddr;// 联系人地址

	private String custStatus;// 客户状态

	private String certType;// 证件类型

	private String certNum;// 证件号码

	private String certAddr;// 证件地址

	private String certExpireDate;// 证件失效日期

	private String releOfficeId;// 客户归属渠道

	private String customerZip;// 客户邮政编码

	private String customerEmail;// 客户email

	private String customerSex;// 客户性别

	private String customerBirth;// 客户生日

	private String customerOccp;// 客户职业

	private String customerOrga;// 客户单位

	private String orgType;// 客户性质

	private String contactEmail;// 客户email

	private String contactZip;// 联系人邮编

	private String orgLeagalRep;// 法人代表

	private String statusChgTime;// 状态变更时间

	private String devChnlId;// 发展渠道标识

	private String individualBrandId;// 个人客户品牌标识

	private String corpBrandId;// 集团客户品牌标识

	private String orgId;// 集团标识

	private String custLoc;// 本地标识

	private String defaultLan;// 默认语种

	private String nativePlace;// 籍贯

	private String maritalStatus;// 婚否

	private String hobby;// 爱好特长

	// Constructors

	/** default constructor */
	public CustomerVerifyResponse() {
	}

	// Property accessors

	/**
	 * get客户校验
	 */
	public String getCustInfoCheck() {
		return this.custInfoCheck;
	}

	/**
	 * set客户校验
	 */
	public void setCustInfoCheck(String custInfoCheck) {
		this.custInfoCheck = custInfoCheck;
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

	/**
	 * get是否黑名单标识
	 */
	public String getBlackListFlag() {
		return this.blackListFlag;
	}

	/**
	 * set是否黑名单标识
	 */
	public void setBlackListFlag(String blackListFlag) {
		this.blackListFlag = blackListFlag;
	}

	/**
	 * get是否达到最大用户数标识
	 */
	public String getMaxUserFlag() {
		return this.maxUserFlag;
	}

	/**
	 * set是否达到最大用户数标识
	 */
	public void setMaxUserFlag(String maxUserFlag) {
		this.maxUserFlag = maxUserFlag;
	}

	/**
	 * get是否欠费标识
	 */
	public String getArrearageFlag() {
		return this.arrearageFlag;
	}

	/**
	 * set是否欠费标识
	 */
	public void setArrearageFlag(String arrearageFlag) {
		this.arrearageFlag = arrearageFlag;
	}

	/**
	 * get欠费信息
	 */
	public List<ArrearageMesss> getArrearageMesss() {
		return this.arrearageMesss;
	}

	/**
	 * set欠费信息
	 */
	public void setArrearageMesss(List<ArrearageMesss> arrearageMesss) {
		this.arrearageMesss = arrearageMesss;
	}

	/**
	 * get客户类型
	 */
	public String getCustomerType() {
		return this.customerType;
	}

	/**
	 * set客户类型
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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
	 * get客户地址
	 */
	public String getCustomerAddr() {
		return this.customerAddr;
	}

	/**
	 * set客户地址
	 */
	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	/**
	 * get联系人
	 */
	public String getContactPerson() {
		return this.contactPerson;
	}

	/**
	 * set联系人
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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
	public String getContactAddr() {
		return this.contactAddr;
	}

	/**
	 * set联系人地址
	 */
	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	/**
	 * get客户状态
	 */
	public String getCustStatus() {
		return this.custStatus;
	}

	/**
	 * set客户状态
	 */
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}

	/**
	 * get证件类型
	 */
	public String getCertType() {
		return this.certType;
	}

	/**
	 * set证件类型
	 */
	public void setCertType(String certType) {
		this.certType = certType;
	}

	/**
	 * get证件号码
	 */
	public String getCertNum() {
		return this.certNum;
	}

	/**
	 * set证件号码
	 */
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	/**
	 * get证件地址
	 */
	public String getCertAddr() {
		return this.certAddr;
	}

	/**
	 * set证件地址
	 */
	public void setCertAddr(String certAddr) {
		this.certAddr = certAddr;
	}

	/**
	 * get证件失效日期
	 */
	public String getCertExpireDate() {
		return this.certExpireDate;
	}

	/**
	 * set证件失效日期
	 */
	public void setCertExpireDate(String certExpireDate) {
		this.certExpireDate = certExpireDate;
	}

	/**
	 * get客户归属渠道
	 */
	public String getReleOfficeId() {
		return this.releOfficeId;
	}

	/**
	 * set客户归属渠道
	 */
	public void setReleOfficeId(String releOfficeId) {
		this.releOfficeId = releOfficeId;
	}

	/**
	 * get客户邮政编码
	 */
	public String getCustomerZip() {
		return this.customerZip;
	}

	/**
	 * set客户邮政编码
	 */
	public void setCustomerZip(String customerZip) {
		this.customerZip = customerZip;
	}

	/**
	 * get客户email
	 */
	public String getCustomerEmail() {
		return this.customerEmail;
	}

	/**
	 * set客户email
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * get客户性别
	 */
	public String getCustomerSex() {
		return this.customerSex;
	}

	/**
	 * set客户性别
	 */
	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	/**
	 * get客户生日
	 */
	public String getCustomerBirth() {
		return this.customerBirth;
	}

	/**
	 * set客户生日
	 */
	public void setCustomerBirth(String customerBirth) {
		this.customerBirth = customerBirth;
	}

	/**
	 * get客户职业
	 */
	public String getCustomerOccp() {
		return this.customerOccp;
	}

	/**
	 * set客户职业
	 */
	public void setCustomerOccp(String customerOccp) {
		this.customerOccp = customerOccp;
	}

	/**
	 * get客户单位
	 */
	public String getCustomerOrga() {
		return this.customerOrga;
	}

	/**
	 * set客户单位
	 */
	public void setCustomerOrga(String customerOrga) {
		this.customerOrga = customerOrga;
	}

	/**
	 * get客户性质
	 */
	public String getOrgType() {
		return this.orgType;
	}

	/**
	 * set客户性质
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * get客户email
	 */
	public String getContactEmail() {
		return this.contactEmail;
	}

	/**
	 * set客户email
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * get联系人邮编
	 */
	public String getContactZip() {
		return this.contactZip;
	}

	/**
	 * set联系人邮编
	 */
	public void setContactZip(String contactZip) {
		this.contactZip = contactZip;
	}

	/**
	 * get法人代表
	 */
	public String getOrgLeagalRep() {
		return this.orgLeagalRep;
	}

	/**
	 * set法人代表
	 */
	public void setOrgLeagalRep(String orgLeagalRep) {
		this.orgLeagalRep = orgLeagalRep;
	}

	/**
	 * get状态变更时间
	 */
	public String getStatusChgTime() {
		return this.statusChgTime;
	}

	/**
	 * set状态变更时间
	 */
	public void setStatusChgTime(String statusChgTime) {
		this.statusChgTime = statusChgTime;
	}

	/**
	 * get发展渠道标识
	 */
	public String getDevChnlId() {
		return this.devChnlId;
	}

	/**
	 * set发展渠道标识
	 */
	public void setDevChnlId(String devChnlId) {
		this.devChnlId = devChnlId;
	}

	/**
	 * get个人客户品牌标识
	 */
	public String getIndividualBrandId() {
		return this.individualBrandId;
	}

	/**
	 * set个人客户品牌标识
	 */
	public void setIndividualBrandId(String individualBrandId) {
		this.individualBrandId = individualBrandId;
	}

	/**
	 * get集团客户品牌标识
	 */
	public String getCorpBrandId() {
		return this.corpBrandId;
	}

	/**
	 * set集团客户品牌标识
	 */
	public void setCorpBrandId(String corpBrandId) {
		this.corpBrandId = corpBrandId;
	}

	/**
	 * get集团标识
	 */
	public String getOrgId() {
		return this.orgId;
	}

	/**
	 * set集团标识
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * get本地标识
	 */
	public String getCustLoc() {
		return this.custLoc;
	}

	/**
	 * set本地标识
	 */
	public void setCustLoc(String custLoc) {
		this.custLoc = custLoc;
	}

	/**
	 * get默认语种
	 */
	public String getDefaultLan() {
		return this.defaultLan;
	}

	/**
	 * set默认语种
	 */
	public void setDefaultLan(String defaultLan) {
		this.defaultLan = defaultLan;
	}

	/**
	 * get籍贯
	 */
	public String getNativePlace() {
		return this.nativePlace;
	}

	/**
	 * set籍贯
	 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	/**
	 * get婚否
	 */
	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	/**
	 * set婚否
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * get爱好特长
	 */
	public String getHobby() {
		return this.hobby;
	}

	/**
	 * set爱好特长
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public void print_cust_info(){
		Log.i("CUSTINFO"," 客户校验              ="+ custInfoCheck);      
		Log.i("CUSTINFO"," 客户ID                ="+ customerId);         
		Log.i("CUSTINFO"," 是否黑名单标识        ="+ blackListFlag);      
		Log.i("CUSTINFO"," 是否达到最大用户数标识="+ maxUserFlag);        
		Log.i("CUSTINFO"," 是否欠费标识          ="+ arrearageFlag);      
		Log.i("CUSTINFO"," 客户类型              ="+ customerType);       
		Log.i("CUSTINFO"," 客户名称              ="+ customerName);       
		Log.i("CUSTINFO"," 客户地址              ="+ customerAddr);       
		Log.i("CUSTINFO"," 联系人                ="+ contactPerson);      
		Log.i("CUSTINFO"," 联系人电话            ="+ contactPhone);       
		Log.i("CUSTINFO"," 联系人地址            ="+ contactAddr);        
		Log.i("CUSTINFO"," 客户状态              ="+ custStatus);         
		Log.i("CUSTINFO"," 证件类型              ="+ certType);           
		Log.i("CUSTINFO"," 证件号码              ="+ certNum);            
		Log.i("CUSTINFO"," 证件地址              ="+ certAddr);           
		Log.i("CUSTINFO"," 证件失效日期          ="+ certExpireDate);     
		Log.i("CUSTINFO"," 客户归属渠道          ="+ releOfficeId);       
		Log.i("CUSTINFO"," 客户邮政编码          ="+ customerZip);        
		Log.i("CUSTINFO"," 客户email             ="+ customerEmail);      
		Log.i("CUSTINFO"," 客户性别              ="+ customerSex);        
		Log.i("CUSTINFO"," 客户生日              ="+ customerBirth);      
		Log.i("CUSTINFO"," 客户职业              ="+ customerOccp);       
		Log.i("CUSTINFO"," 客户单位              ="+ customerOrga);       
		Log.i("CUSTINFO"," 客户性质              ="+ orgType);            
		Log.i("CUSTINFO"," 客户email             ="+ contactEmail);       
		Log.i("CUSTINFO"," 联系人邮编            ="+ contactZip);         
		Log.i("CUSTINFO"," 法人代表              ="+ orgLeagalRep);       
		Log.i("CUSTINFO"," 状态变更时间          ="+ statusChgTime);      
		Log.i("CUSTINFO"," 发展渠道标识          ="+ devChnlId);          
		Log.i("CUSTINFO"," 个人客户品牌标识      ="+ individualBrandId);  
		Log.i("CUSTINFO"," 集团客户品牌标识      ="+ corpBrandId);        
		Log.i("CUSTINFO"," 集团标识              ="+ orgId);              
		Log.i("CUSTINFO"," 本地标识              ="+ custLoc);            
		Log.i("CUSTINFO"," 默认语种              ="+ defaultLan);         
		Log.i("CUSTINFO"," 籍贯                  ="+ nativePlace);        
		Log.i("CUSTINFO"," 婚否                  ="+ maritalStatus);      
		Log.i("CUSTINFO"," 爱好特长              ="+ hobby);         
	} 

}
