package com.tydic.android.usp.uss.response;

import java.io.Serializable;

/**
 * <pre>
 * Description: 验证码信息oper_get_verify_code_vo的response bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author lichao
 * @version V1.0
 * @since 2014-9-30
 */
public class OperGetVerifyCodeResponse implements Serializable {

	private static final long serialVersionUID = -1231603201610721449L;

	// Fields

	private String jsessionid;// 当前的sesionId

	private String verifyCode;// 当前的验证码

	// Constructors

	/** default constructor */
	public OperGetVerifyCodeResponse() {
	}

	// Property accessors

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
	 * get当前的验证码
	 */
	public String getVerifyCode() {
		return this.verifyCode;
	}

	/**
	 * set当前的验证码
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
