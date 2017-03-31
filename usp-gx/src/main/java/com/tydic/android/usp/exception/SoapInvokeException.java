/**
 * 
 */
package com.tydic.android.usp.exception;


/**
 * @author <a href="mailto:yongqiang.liu@qq.com">liuyq</a>
 *
 */
public class SoapInvokeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6800049798233623030L;

	/** */
	private static final String DEF_MSG = "unknow error";
	
	public SoapInvokeException() {
		super(DEF_MSG);
	}

	public SoapInvokeException(String message) {
		super(message);
	}
	

	public SoapInvokeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
}
