package com.tydic.android.usp.uss.response;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Description: 选择号码的response bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author wangxiao
 * @version V1.0
 * @since 2014-10-3
 */
public class SaleSelectNumberResponse {

	// Fields 
	
	private List<Map<String, Object>> numberList;

	// Constructors 

	/** default constructor */
	public SaleSelectNumberResponse() {
	}

	public List<Map<String, Object>> getNumberList() {
		return numberList;
	}

	public void setNumberList(List<Map<String, Object>> numberList) {
		this.numberList = numberList;
	}

	
	

}
