package com.tydic.android.usp.uss.response;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Description: 选择套餐的response bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author wangxiao
 * @version V1.0
 * @since 2014-10-3
 */
public class SaleSelectedCodeResponse {

	// Fields 
	
	private List<Map<String, Object>> saleList;

	// Constructors 

	/** default constructor */
	public SaleSelectedCodeResponse() {
	}

	public List<Map<String, Object>> getSaleList() {
		return saleList;
	}

	public void setSaleList(List<Map<String, Object>> saleList) {
		this.saleList = saleList;
	}

	

}
