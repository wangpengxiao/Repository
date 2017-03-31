package com.tydic.android.usp.uss.response;

import java.io.Serializable;

/**
 * <pre>
 * Description: 操作员登录信息oper_login_vo的response bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author lichao
 * @version V1.0
 * @since 2014-10-1
 */
public class OperLoginResponse implements Serializable {

	private static final long serialVersionUID = -8957257150993372230L;

	// Fields

	private String operId;// 当前的操作员ID

	private String operName;// 当前的操作员名称

	private String loginCode;// 登录名称

	private String operPwd;// 登录密码

	// Constructors

	/** default constructor */
	public OperLoginResponse() {
	}

	// Property accessors

	/**
	 * get当前的操作员ID
	 */
	public String getOperId() {
		return this.operId;
	}

	/**
	 * set当前的操作员ID
	 */
	public void setOperId(String operId) {
		this.operId = operId;
	}

	/**
	 * get当前的操作员名称
	 */
	public String getOperName() {
		return this.operName;
	}

	/**
	 * set当前的操作员名称
	 */
	public void setOperName(String operName) {
		this.operName = operName;
	}

	/**
	 * get登录名称
	 */
	public String getLoginCode() {
		return this.loginCode;
	}

	/**
	 * set登录名称
	 */
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	/**
	 * get登录密码
	 */
	public String getOperPwd() {
		return this.operPwd;
	}

	/**
	 * set登录密码
	 */
	public void setOperPwd(String operPwd) {
		this.operPwd = operPwd;
	}

}
