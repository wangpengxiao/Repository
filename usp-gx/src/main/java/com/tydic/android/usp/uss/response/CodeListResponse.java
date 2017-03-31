package com.tydic.android.usp.uss.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Description: 获得codelist的response bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author wangxiao
 * @version V1.0
 * @since 2014-10-5
 */
public class CodeListResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	
	private	List<Map<String, Object>> codeList;

	// Constructors

	/** default constructor */
	public CodeListResponse() {
	}

	public List<Map<String, Object>> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<Map<String, Object>> codeList) {
		this.codeList = codeList;
	}

}
