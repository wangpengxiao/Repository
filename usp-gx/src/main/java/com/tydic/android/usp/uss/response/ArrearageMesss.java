package com.tydic.android.usp.uss.response;

import java.io.Serializable;

/**
 * <pre>
 * Description: 欠费信息arrearage_messs的response bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author lichao
 * @version V1.0
 * @since 2014-10-7
 */
public class ArrearageMesss implements Serializable {

	private static final long serialVersionUID = -8578211638043683797L;

	// Fields

	private String arrearageNum;// 欠费号码

	private String arrearageFee;// 欠费金额

	// Constructors

	/** default constructor */
	public ArrearageMesss() {
	}

	// Property accessors

	/**
	 * get欠费号码
	 */
	public String getArrearageNum() {
		return this.arrearageNum;
	}

	/**
	 * set欠费号码
	 */
	public void setArrearageNum(String arrearageNum) {
		this.arrearageNum = arrearageNum;
	}

	/**
	 * get欠费金额
	 */
	public String getArrearageFee() {
		return this.arrearageFee;
	}

	/**
	 * set欠费金额
	 */
	public void setArrearageFee(String arrearageFee) {
		this.arrearageFee = arrearageFee;
	}

}
