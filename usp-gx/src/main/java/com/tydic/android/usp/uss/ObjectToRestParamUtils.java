package com.tydic.android.usp.uss;

import com.tydic.android.rest.RestParameters;
import com.tydic.android.usp.uss.request.CheckCustInfoRequest;
import com.tydic.android.usp.uss.request.OperLoginRequest;
import com.tydic.android.usp.uss.request.SaleOpenOrderSubmitRequest;
import com.tydic.android.usp.uss.request.SaleSelectNumberRequest;
import com.tydic.android.usp.uss.request.SaleSelectedCodeRequest;
import com.tydic.android.usp.util.StringUtils;

/**
 * Object转换成请求业务参数的工具类
 * 
 * @author lichao
 *
 */
public class ObjectToRestParamUtils {

	/**
	 * 转换 登录请求信息 的入参
	 * 
	 * @param operLoginRequest
	 * @return
	 */
	public static RestParameters transOperLoginRequestToParam(OperLoginRequest operLoginRequest) {

		RestParameters params = new RestParameters();
		if (operLoginRequest != null) {
			// 添加业务请求参数
			params.addParam("login_code", operLoginRequest.getLoginCode());
			params.addParam("oper_pwd", operLoginRequest.getOperPwd());
			params.addParam("jsessionid", operLoginRequest.getJsessionid());
			params.addParam("verify_code", operLoginRequest.getVerifyCode());
		}

		return params;
	}

	/**
	 * 转换 选套餐请求信息 的入参
	 * 
	 * @param saleSelectedCodeRequest
	 * @return
	 */
	public static RestParameters transSaleSelectedCodeRequestToParam(SaleSelectedCodeRequest saleSelectedCodeRequest) {

		RestParameters params = new RestParameters();
		if (saleSelectedCodeRequest != null) {
			// 添加业务请求参数
			params.addParam("ofr_sub_type", saleSelectedCodeRequest.getOfrSubType());
			params.addParam("month_fee", saleSelectedCodeRequest.getMonthFee());
			params.addParam("month_call_duration", saleSelectedCodeRequest.getMonthCallDuration());
			params.addParam("month_net_duration", saleSelectedCodeRequest.getMonthNetDuration());
			params.addParam("ofr_name", saleSelectedCodeRequest.getOfrName());
			params.addParam("tele_type", saleSelectedCodeRequest.getTeleType());
			params.addParam("device_number", saleSelectedCodeRequest.getDeviceNumber());
			params.addParam("jsessionid", saleSelectedCodeRequest.getJsessionid());
		}

		return params;
	}

	/**
	 * 转换 选号码请求信息 的入参
	 * 
	 * @param saleSelectNumberRequest
	 * @return
	 */
	public static RestParameters transSaleSelectNumberRequestToParam(SaleSelectNumberRequest saleSelectNumberRequest) {

		RestParameters params = new RestParameters();
		if (saleSelectNumberRequest != null) {
			// 添加业务请求参数
			params.addParam("tele_type", saleSelectNumberRequest.getTeleType());
			params.addParam("mob_section", saleSelectNumberRequest.getMobSection());
			params.addParam("price_range", saleSelectNumberRequest.getPriceRange());
			params.addParam("perrty_type", saleSelectNumberRequest.getPerrtyType());
			params.addParam("select_count", saleSelectNumberRequest.getSelectCount());
			params.addParam("jsessionid", saleSelectNumberRequest.getJsessionid());
		}

		return params;
	}

	/**
	 * 转换 订单提交信息 的请求入参
	 * 
	 * @param saleOpenOrderSubmitRequest
	 * @return
	 */
	public static RestParameters transSaleOpenOrderSubmitRequestToParam(SaleOpenOrderSubmitRequest saleOpenOrderSubmitRequest) {

		RestParameters params = new RestParameters();
		if (saleOpenOrderSubmitRequest != null) {
			// 添加业务请求参数
			params.addParam("order_num", saleOpenOrderSubmitRequest.getOrderNum());
			params.addParam("id_type", saleOpenOrderSubmitRequest.getIdType());
			params.addParam("id_number", saleOpenOrderSubmitRequest.getIdNumber());
			params.addParam("auth_end_date", saleOpenOrderSubmitRequest.getAuthEndDate());
			params.addParam("auth_adress", saleOpenOrderSubmitRequest.getAuthAdress());
			params.addParam("customer_name", saleOpenOrderSubmitRequest.getCustomerName());
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getCustSex())) {
				params.addParam("cust_sex", saleOpenOrderSubmitRequest.getCustSex());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getBornDate())) {
				params.addParam("born_date", saleOpenOrderSubmitRequest.getBornDate());
			}
			params.addParam("cust_post", saleOpenOrderSubmitRequest.getCustPost());
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getCustEmail())) {
				params.addParam("cust_email", saleOpenOrderSubmitRequest.getCustEmail());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getRemarkDesc())) {
				params.addParam("remark_desc", saleOpenOrderSubmitRequest.getRemarkDesc());
			}
			params.addParam("contact_name", saleOpenOrderSubmitRequest.getContactName());
			params.addParam("contact_phone", saleOpenOrderSubmitRequest.getContactPhone());
			params.addParam("contact_adress", saleOpenOrderSubmitRequest.getContactAdress());
			params.addParam("devolop_name", saleOpenOrderSubmitRequest.getDevolopName());
			params.addParam("devolop_post", saleOpenOrderSubmitRequest.getDevolopPost());
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getDevolopPhone())) {
				params.addParam("devolop_phone", saleOpenOrderSubmitRequest.getDevolopPhone());
			}
			params.addParam("devolop_channel_id", saleOpenOrderSubmitRequest.getDevolopChannelId());
			params.addParam("devolop_channel_name", saleOpenOrderSubmitRequest.getDevolopChannelName());
			params.addParam("credit_first", saleOpenOrderSubmitRequest.getCreditFirst());
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getHandlerName())) {
				params.addParam("handler_name", saleOpenOrderSubmitRequest.getHandlerName());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getHandlerIdType())) {
				params.addParam("handler_id_type", saleOpenOrderSubmitRequest.getHandlerIdType());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getHandlerIdNumber())) {
				params.addParam("handler_id_number", saleOpenOrderSubmitRequest.getHandlerIdNumber());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getHandlerRemarkDesc())) {
				params.addParam("handler_remark_desc", saleOpenOrderSubmitRequest.getHandlerRemarkDesc());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getBillSend())) {
				params.addParam("bill_send", saleOpenOrderSubmitRequest.getBillSend());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getLogisticsType())) {
				params.addParam("logistics_type", saleOpenOrderSubmitRequest.getLogisticsType());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getSendContent())) {
				params.addParam("send_content", saleOpenOrderSubmitRequest.getSendContent());
			}
			params.addParam("tele_type", saleOpenOrderSubmitRequest.getTeleType());
			params.addParam("acc_nbr", saleOpenOrderSubmitRequest.getAccNbr());
			params.addParam("acc_nbr_fee", saleOpenOrderSubmitRequest.getAccNbrFee());
			params.addParam("product_id", saleOpenOrderSubmitRequest.getOfrId());
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getTerminalType())) {
				params.addParam("terminal_type", saleOpenOrderSubmitRequest.getTerminalType());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getFee())) {
				params.addParam("fee", saleOpenOrderSubmitRequest.getFee());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getActivityId())) {
				params.addParam("activity_id", saleOpenOrderSubmitRequest.getActivityId());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getPaymentType())) {
				params.addParam("payment_type", saleOpenOrderSubmitRequest.getPaymentType());
			}
			if (!StringUtils.isBlank(saleOpenOrderSubmitRequest.getCustomerId())) {
				params.addParam("customer_id", saleOpenOrderSubmitRequest.getCustomerId());
			}
			params.addParam("jsessionid", saleOpenOrderSubmitRequest.getJsessionid());
		}

		return params;
	}

	/**
	 * 转换 客户资料校验请求信息 的请求入参
	 * 
	 * @param checkCustInfoRequest
	 * @return
	 */
	public static RestParameters transCheckCustInfoRequestToParam(CheckCustInfoRequest checkCustInfoRequest) {

		RestParameters params = new RestParameters();
		if (checkCustInfoRequest != null) {
			// 添加业务请求参数
			params.addParam("oper_id", checkCustInfoRequest.getOperId());
			params.addParam("province", checkCustInfoRequest.getProvince());
			params.addParam("city", checkCustInfoRequest.getCity());
			params.addParam("district", checkCustInfoRequest.getDistrict());
			params.addParam("oper_dept", checkCustInfoRequest.getOperDept());
			params.addParam("channel_type", checkCustInfoRequest.getChannelType());
			params.addParam("access_type", checkCustInfoRequest.getAccessType());
			params.addParam("id_type", checkCustInfoRequest.getIdType());
			params.addParam("id_number", checkCustInfoRequest.getIdNumber());
			params.addParam("jsessionid", checkCustInfoRequest.getJsessionid());
		}

		return params;
	}
}
