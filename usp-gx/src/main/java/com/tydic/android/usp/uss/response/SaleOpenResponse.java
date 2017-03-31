package com.tydic.android.usp.uss.response;

import java.io.Serializable;

/**
 * <pre>
 * Description: 销售开户订单提交返回信息sale_open_vo的response bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author lichao
 * @version V1.0
 * @since 2014-10-7
 */
public class SaleOpenResponse implements Serializable {

	private static final long serialVersionUID = 8705798496206543070L;

	// Fields

	private String orderId;// 订单ID

	// Constructors

	/** default constructor */
	public SaleOpenResponse() {
	}

	// Property accessors

	/**
	 * get订单ID
	 */
	public String getOrderId() {
		return this.orderId;
	}

	/**
	 * set订单ID
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
