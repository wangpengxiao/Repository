package com.tydic.android.usp.uss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.tydic.android.usp.common.Logger;
import com.tydic.android.usp.uss.response.ArrearageMesss;
import com.tydic.android.usp.uss.response.CodeListResponse;
import com.tydic.android.usp.uss.response.CustomerVerifyResponse;
import com.tydic.android.usp.uss.response.OperGetVerifyCodeResponse;
import com.tydic.android.usp.uss.response.OperLoginResponse;
import com.tydic.android.usp.uss.response.SaleOpenResponse;
import com.tydic.android.usp.uss.response.SaleSelectNumberResponse;
import com.tydic.android.usp.uss.response.SaleSelectedCodeResponse;

/**
 * jsonObject转换成bean的工具类
 * 
 * @author lichao
 * 
 */
public class JsonToBeanUtils {

	private static String TAG = "android-client-JsonToBeanUtils";

	/**
	 * 解析获得 验证码信息 的出参
	 * 
	 * @param json
	 * @return
	 */
	public static OperGetVerifyCodeResponse parseToOperGetVerifyCodeResponse(JSONObject json) {
		JSONObject vo = json.optJSONObject("oper_get_verify_code_vo");
		if (vo == null) {
			return null;
		}
		OperGetVerifyCodeResponse operGetVerifyCode = new OperGetVerifyCodeResponse();
		operGetVerifyCode.setJsessionid(vo.optString("jsessionid"));
		operGetVerifyCode.setVerifyCode(vo.optString("verify_code"));

		return operGetVerifyCode;
	}

	/**
	 * 解析获得 操作员登录信息 的出参
	 * 
	 * @param json
	 * @return
	 */
	public static OperLoginResponse parseToOperLoginResponse(JSONObject json) {
		JSONObject vo = json.optJSONObject("oper_login_vo");
		if (vo == null) {
			return null;
		}
		OperLoginResponse operLogin = new OperLoginResponse();
		operLogin.setOperId(vo.optString("oper_id"));
		operLogin.setOperName(vo.optString("oper_name"));
		operLogin.setLoginCode(vo.optString("login_code"));
		operLogin.setOperPwd(vo.optString("oper_pwd"));

		return operLogin;
	}

	/**
	 * 解析获得 选择套餐 的出参
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static SaleSelectedCodeResponse parseToSaleSelectedCodeResponse(JSONObject json) {
		JSONArray jsonArray = json.optJSONArray("sale_selected_code_list");
		if (jsonArray == null) {
			return null;
		}
		SaleSelectedCodeResponse saleSelectedCodeResponse = new SaleSelectedCodeResponse();

		JSONObject jsonObject;
		List<Map<String, Object>> saleList = jsonArrayToList(jsonArray);

		saleSelectedCodeResponse.setSaleList(saleList);

		return saleSelectedCodeResponse;
	}

	/**
	 * 解析获得 选择号码 的出参
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static SaleSelectNumberResponse parseToSaleSelectNumberResponse(JSONObject json) {
		JSONArray jsonArray = json.optJSONArray("number_list");
		if (jsonArray == null) {
			return null;
		}
		SaleSelectNumberResponse saleSelectNumberResponse = new SaleSelectNumberResponse();

		JSONObject jsonObject;
		List<Map<String, Object>> numberList = jsonArrayToList(jsonArray);

		saleSelectNumberResponse.setNumberList(numberList);

		return saleSelectNumberResponse;
	}

	/**
	 * 解析获得 获得codelist 的出参
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static CodeListResponse parseToCodeListResponse(JSONObject json) {
		JSONArray jsonArray = json.optJSONArray("code_list_vos");
		if (jsonArray == null) {
			return null;
		}
		CodeListResponse codeListResponse = new CodeListResponse();

		JSONObject jsonObject;
		List<Map<String, Object>> codeList = jsonArrayToList(jsonArray);

		codeListResponse.setCodeList(codeList);

		return codeListResponse;
	}

	/**
	 * 解析获得 销售开户订单提交返回信息 的出参
	 * 
	 * @param json
	 * @return
	 */
	public static SaleOpenResponse parseToSaleOpenResponse(JSONObject json) {
		JSONObject vo = json.optJSONObject("sale_open_vo");
		if (vo == null) {
			return null;
		}
		SaleOpenResponse saleOpen = new SaleOpenResponse();
		saleOpen.setOrderId(vo.optString("order_id"));

		return saleOpen;
	}

	/**
	 * 解析获得 客户资料校验返回信息 的出参
	 * 
	 * @param json
	 * @return
	 */
	public static CustomerVerifyResponse parseToCustomerVerifyResponse(JSONObject json) {
		JSONObject vo = json.optJSONObject("customer_verify_vo");
		if (vo == null) {
			return null;
		}
		CustomerVerifyResponse customerVerify = new CustomerVerifyResponse();
		customerVerify.setCustInfoCheck(vo.optString("cust_info_check"));
		customerVerify.setCustomerId(vo.optString("customer_id"));
		customerVerify.setBlackListFlag(vo.optString("black_list_flag"));
		customerVerify.setMaxUserFlag(vo.optString("max_user_flag"));
		customerVerify.setArrearageFlag(vo.optString("arrearage_flag"));
		customerVerify.setCustomerType(vo.optString("customer_type"));
		customerVerify.setCustomerName(vo.optString("customer_name"));
		customerVerify.setCustomerAddr(vo.optString("customer_addr"));
		customerVerify.setContactPerson(vo.optString("contact_person"));
		customerVerify.setContactPhone(vo.optString("contact_phone"));
		customerVerify.setContactAddr(vo.optString("contact_addr"));
		customerVerify.setCustStatus(vo.optString("cust_status"));
		customerVerify.setCertType(vo.optString("cert_type"));
		customerVerify.setCertNum(vo.optString("cert_num"));
		customerVerify.setCertAddr(vo.optString("cert_addr"));
		customerVerify.setCertExpireDate(vo.optString("cert_expire_date"));
		customerVerify.setReleOfficeId(vo.optString("rele_office_id"));
		customerVerify.setCustomerZip(vo.optString("customer_zip"));
		customerVerify.setCustomerEmail(vo.optString("customer_email"));
		customerVerify.setCustomerSex(vo.optString("customer_sex"));
		customerVerify.setCustomerBirth(vo.optString("customer_birth"));
		customerVerify.setCustomerOccp(vo.optString("customer_occp"));
		customerVerify.setCustomerOrga(vo.optString("customer_orga"));
		customerVerify.setOrgType(vo.optString("org_type"));
		customerVerify.setContactEmail(vo.optString("contact_email"));
		customerVerify.setContactZip(vo.optString("contact_zip"));
		customerVerify.setOrgLeagalRep(vo.optString("org_leagal_rep"));
		customerVerify.setStatusChgTime(vo.optString("status_chg_time"));
		customerVerify.setDevChnlId(vo.optString("dev_chnl_id"));
		customerVerify.setIndividualBrandId(vo.optString("individual_brand_id"));
		customerVerify.setCorpBrandId(vo.optString("corp_brand_id"));
		customerVerify.setOrgId(vo.optString("org_id"));
		customerVerify.setCustLoc(vo.optString("cust_loc"));
		customerVerify.setDefaultLan(vo.optString("default_lan"));
		customerVerify.setNativePlace(vo.optString("native_place"));
		customerVerify.setMaritalStatus(vo.optString("marital_status"));
		customerVerify.setHobby(vo.optString("hobby"));

		// 解析欠费信息
		JSONArray arrearageMesssArray = vo.optJSONArray("arrearage_messs");
		customerVerify.setArrearageMesss(parseToArrearageMesss(arrearageMesssArray));

		return customerVerify;
	}

	/**
	 * 解析获得 欠费信息 的出参
	 * 
	 * @param json
	 * @return
	 */
	private static List<ArrearageMesss> parseToArrearageMesss(JSONArray jsonArray) {
		if (jsonArray == null) {
			return null;
		}
		List<ArrearageMesss> list = new ArrayList<ArrearageMesss>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject vo = jsonArray.getJSONObject(i);
				if (vo != null) {
					ArrearageMesss arrearageMesss = new ArrearageMesss();
					arrearageMesss.setArrearageNum(vo.optString("arrearage_num"));
					arrearageMesss.setArrearageFee(vo.optString("arrearage_fee"));
					list.add(arrearageMesss);
				}
			}
		} catch (JSONException e) {
			Logger.e(TAG, e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 解析获得 json数组对应的list
	 * 
	 * @param jsonArray
	 * @return
	 * @throws JSONException
	 */
	public static List<Map<String, Object>> jsonArrayToList(JSONArray jsonArray) {
		JSONObject jsonObject;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);

				Iterator<String> keyIter = jsonObject.keys();
				String key;
				String value;
				Map<String, Object> valueMap = new HashMap<String, Object>();
				while (keyIter.hasNext()) {
					key = (String) keyIter.next();
					if(jsonObject.get(key) == JSONObject.NULL) {
						value = "";
					} else {
						value = (String)jsonObject.get(key);
					}
					valueMap.put(key, value);
				}
				list.add(valueMap);
			}
		} catch (JSONException e) {
			Logger.e(TAG, e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
}
