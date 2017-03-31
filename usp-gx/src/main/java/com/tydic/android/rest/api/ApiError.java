package com.tydic.android.rest.api;

/**
 * API调用的业务错误信息
 * 
 * @author lichao
 *
 */
public class ApiError {

	private String errorCode;

	private String type;// 成功失败标志(success/error)

	private String content;// 成功失败信息

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * get成功失败标志(success/error)
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * set成功失败标志(success/error)
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * get成功失败信息
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * set成功失败信息
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("errorCode:").append(this.errorCode).append(" type:")
				.append(this.type).append(" content:").append(content);
		return builder.toString();
	}
}
