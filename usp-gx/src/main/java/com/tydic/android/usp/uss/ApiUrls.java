package com.tydic.android.usp.uss;

/**
 * 接口URL配置
 * 
 * @author lichao
 * @version 1.0
 * @created 2014-9-29
 */
public class ApiUrls {

	//public final static String HOST = "172.168.1.224:8283";
	public final static String HOST = "133.0.191.249:8080";
	//public final static String HOST = "130.59.252.73:8080";

	public final static String HTTP = "http://";
	public final static String HTTPS = "https://";
	private final static String URL_SPLITTER = "/";
	private final static String URL_API_HOST = HTTP + HOST + URL_SPLITTER;

	/**
	 * 获得验证码
	 */
	public final static String GET_VERIFY_CODE = URL_API_HOST + "uss_web/rest/oper/getVerifyCode";

	/**
	 * 登录
	 */
	public final static String OPER_LOGIN = URL_API_HOST + "uss_web/rest/oper/login";

	/**
	 * 选号
	 */
	public final static String SALE_SELECT_NUMBER = URL_API_HOST + "uss_web/rest/selectNumber/selectNumberData";

	/**
	 * 选套餐
	 */
	public final static String SALE_SELECT_CODE = URL_API_HOST + "uss_web/rest/sale/saleSelectedCode";

	/**
	 * 获得codelist
	 */
	public final static String GET_CODE_LIST = URL_API_HOST + "uss_web/rest/codeType/codeList";

	/**
	 * 订单提交
	 */
	public final static String SALE_OPEN_ORDER_SUBMIT = URL_API_HOST + "uss_web/rest/saleOpen/orderSubmit";

	/**
	 * 客户资料校验
	 */
	public final static String CHECK_CUST_INFO = URL_API_HOST + "uss_web/rest/customerVerify/checkCustInfo";

}
