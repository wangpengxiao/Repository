package com.tydic.android.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.tydic.android.rest.api.ApiError;
import com.tydic.android.rest.api.RestApiListener;
import com.tydic.android.rest.api.WebUtils;

/**
 * uss rest服务的客户端实现
 * 
 * @author lichao
 *
 */
public class RestAndroidClient {

	private static final ConcurrentHashMap<String, RestAndroidClient> CLIENT_STORE = new ConcurrentHashMap<String, RestAndroidClient>();

	private static final String SDK_TRACK_ID = "track-id";
	private static final String SDK_DEVICE_UUID = "device-uuid";
	private static final String SDK_TIMESTAMP = "timestamp";
	private static final String SDK_CLIENT_SYSVERSION = "client-sysVersion";
	private static final String SDK_CLIENT_SYSNAME = "client-sysName";
	private static final String SDK_VERSION = "sdk-version";

	private static final String VERSION = "1.0";

	private static final String LOG_TAG = "UssAndroidClient";
	private static final String SYS_NAME = "Android";

	private Context context;
	private int connectTimeout = 10000;// 10秒
	private int readTimeout = 30000;// 30秒

	/**
	 * 注册client所需信息
	 * 
	 * @param context
	 */
	public static void registerAndroidClient(Context context, String appKey) {
		if (context == null) {
			throw new IllegalArgumentException("context must not null.");
		}
		RestAndroidClient client = new RestAndroidClient();
		client.setContext(context);
		CLIENT_STORE.put(appKey, client);
	}

	/**
	 * 获得事先已经注册的client对象。如果事先没有注册的话返回null
	 * 
	 * @see #registerAndroidClient(Context, String, String, String)
	 * @see #registerAndroidClient(Context, String, String, String, Env)
	 * @param appKey
	 * @return
	 */
	public static RestAndroidClient getAndroidClientByAppKey(String appKey) {
		return CLIENT_STORE.get(appKey);
	}

	private RestAndroidClient() {
	}

	/**
	 * 调用Rest服务的 API（POST方式）
	 * 
	 * @see RestApiListener
	 * @param apiUrl
	 *            API请求地址
	 * @param params
	 *            系统及业务参数
	 * @param listener
	 *            api调用回调处理监听器，不能为null
	 * @param async
	 *            true:异步调用；false:同步调用。Android 3.0以后会限制在UI主线程中同步访问网络，使用同步方式需谨慎
	 */
	public void apiPost(final String apiUrl, final RestParameters params, final RestApiListener listener, final boolean async) {
		api("POST", apiUrl, params, listener, async);
	}

	/**
	 * 调用Rest服务的 API（GET方式）
	 * 
	 * @param apiUrl
	 *            API请求地址
	 * @param params
	 *            系统及业务参数
	 * @param listener
	 *            api调用回调处理监听器，不能为null
	 * @param async
	 *            true:异步调用；false:同步调用。Android 3.0以后会限制在UI主线程中同步访问网络，使用同步方式需谨慎
	 */
	public void apiGet(final String apiUrl, final RestParameters params, final RestApiListener listener, final boolean async) {
		api("GET", apiUrl, params, listener, async);
	}

	/**
	 * 调用Rest服务的 API
	 * 
	 * @see RestApiListener
	 * @param type
	 *            请求方式 ("POST" 或 "GET")， 默认为 "POST"
	 * @param apiUrl
	 *            API请求地址
	 * @param params
	 *            系统及业务参数
	 * @param listener
	 *            api调用回调处理监听器，不能为null
	 * @param async
	 *            true:异步调用；false:同步调用。Android 3.0以后会限制在UI主线程中同步访问网络，使用同步方式需谨慎
	 * @throws IllegalArgumentException
	 *             当参数<code>listener</code>为null时
	 */
	private void api(final String type, final String apiUrl, final RestParameters params, final RestApiListener listener, final boolean async) {

		if (apiUrl == null) {
			throw new IllegalArgumentException("apiUrl must not null.");
		}
		if (params == null) {
			throw new IllegalArgumentException("params must not null.");
		}
		if (listener == null) {
			throw new IllegalArgumentException("listener must not null.");
		}
		if (async) {// 异步调用
			new Thread() {
				@Override
				public void run() {
					invokeApi(type, apiUrl, params, listener);
				}
			}.start();
		} else {// 同步
			invokeApi(type, apiUrl, params, listener);
		}
	}

	private void invokeApi(String type, String apiUrl, RestParameters params, RestApiListener listener) {
		try {
			String jsonStr = null;
			if ("POST".equals(type)) {
				jsonStr = WebUtils.doPost(context, apiUrl, this.generateApiParams(params), this.getProtocolParams(), params.getAttachments(), connectTimeout, readTimeout);
			} else {
				// 默认为GET的方式
				jsonStr = WebUtils.doGet(context, apiUrl, this.generateApiParams(params));
			}

			Log.d(LOG_TAG, jsonStr);
			handleApiResponse(listener, jsonStr);
		} catch (Exception e) {
			Log.e(LOG_TAG, e.getMessage(), e);
			listener.onException(e);
		}
	}

	private void handleApiResponse(RestApiListener listener, String jsonStr) throws JSONException {
		JSONObject json = new JSONObject(jsonStr);
		ApiError error = this.parseError(json);
		if (error != null) {// failed
			Log.w(LOG_TAG, jsonStr);
			listener.onError(error);
		} else {

			listener.onComplete(getArgs(json));
		}
	}

	private JSONObject getArgs(JSONObject json) {
		if (json == null) {
			return null;
		}
		JSONObject args = json.optJSONObject("args");
		if (args == null) {
			return json;
		}
		return args;
	}

	private ApiError parseError(JSONObject json) throws JSONException {
		// JSONObject resp = json.optJSONObject("error_response");
		if (json == null) {
			return null;
		}
		String content = json.optString("content");
		String type = json.optString("type");
		if ("success".equals(type)) {
			return null;
		}
		ApiError error = null;
		if (!TextUtils.isEmpty(type)) {
			error = new ApiError();
			error.setErrorCode("11111");
			error.setContent(content);
			error.setType(type);
		}
		return error;
	}

	private Map<String, String> generateApiParams(RestParameters restParameters) throws IOException {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("v", VERSION);
		params.put("partner_id", "uss-android-sdk");
		params.put("format", "json");

		Map<String, String> map = restParameters.getParams();
		if (map != null) {
			Set<Entry<String, String>> set = map.entrySet();
			for (Entry<String, String> entry : set) {
				params.put(entry.getKey(), entry.getValue());
			}
		}
		List<String> list = restParameters.getFields();
		if (list != null) {
			String fileds = TextUtils.join(",", list);
			if (!TextUtils.isEmpty(fileds)) {
				params.put("fields", fileds);
			}
		}
		return params;
	}

	private Map<String, String> getProtocolParams() {
		String trackId = "1000001";

		Map<String, String> params = new HashMap<String, String>();
		params.put(SDK_CLIENT_SYSNAME, SYS_NAME);
		params.put(SDK_CLIENT_SYSVERSION, android.os.Build.VERSION.RELEASE);
		params.put(SDK_DEVICE_UUID, UssUtils.getDeviceId(context));
		params.put(SDK_TRACK_ID, trackId);
		params.put(SDK_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
		params.put(SDK_VERSION, VERSION);

		return params;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

}
