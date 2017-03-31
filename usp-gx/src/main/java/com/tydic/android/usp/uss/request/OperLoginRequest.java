package com.tydic.android.usp.uss.request;

import java.io.Serializable;

/**
 * <pre>
 * Description: 登录请求信息login的request bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author lichao
 * @version V1.0
 * @since 2014-10-1
 */
public class OperLoginRequest implements Serializable {

	private static final long serialVersionUID = 5270840052031677447L;

	// Fields

	private String loginCode;// 登录名称

	private String operPwd;// 登录密码

	private String jsessionid;// 当前的sessionId

	private String verifyCode;// 验证码

	// Constructors

	/** default constructor */
	public OperLoginRequest() {
	}

	// Property accessors

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

	/**
	 * get当前的sessionId
	 */
	public String getJsessionid() {
		return this.jsessionid;
	}

	/**
	 * set当前的sessionId
	 */
	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

	/**
	 * get验证码
	 */
	public String getVerifyCode() {
		return this.verifyCode;
	}

	/**
	 * set验证码
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
