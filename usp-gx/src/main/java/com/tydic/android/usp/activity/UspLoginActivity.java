package com.tydic.android.usp.activity;

import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tydic.android.rest.RestParameters;
import com.tydic.android.rest.api.ApiError;
import com.tydic.android.rest.api.RestApiListener;
import com.tydic.android.usp.R;
import com.tydic.android.usp.UspPreferences;
import com.tydic.android.usp.uss.ApiUrls;
import com.tydic.android.usp.uss.BitmapUtil;
import com.tydic.android.usp.uss.JsonToBeanUtils;
import com.tydic.android.usp.uss.ObjectToRestParamUtils;
import com.tydic.android.usp.uss.request.OperLoginRequest;
import com.tydic.android.usp.uss.response.OperGetVerifyCodeResponse;
import com.tydic.android.usp.uss.response.OperLoginResponse;
import com.tydic.android.usp.util.StringUtils;

public class UspLoginActivity extends BasicActivity {

	private static String TAG = "android-client-LoginActivity";

	private static final int LOGIN_ERROR = 0;// 登录失败
	
	private Button loginBtn = null;
	private TextView anotherCode = null;
	private EditText userCode = null;
	private EditText password = null;
	private EditText identifyingCode = null;
	private CheckBox rememberpwd = null;
	private Map<String, Object> spInfo = null;
	private ImageView codeImage = null;
	private int codeImageWidth = 0;
	private int codeImageHeight = 0;

	protected String jsessionid = null;
	protected String loginErrorInfo = "登录失败.请检查用户名密码";
	protected Boolean loginResult = false;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOGIN_ERROR:
				// 登录返回错误后要切换验证码
				getVerifyCode();
				break;
			}
		}
	};
	
	@Override
	protected void setlayout() {
		Log.i(TAG, "setLayout login");
		setContentView(R.layout.usp_login);

		codeImage = (ImageView) findViewById(R.id.code_img);
		LayoutParams para = codeImage.getLayoutParams();
		codeImageWidth = para.width;
		codeImageHeight = para.height;
		// 获取
		// Log.d(TAG, "codeImage layout width: " + codeImageWidth);
		// Log.d(TAG, "codeImage layout height: " + codeImageHeight);

		// 获取验证码
		getVerifyCode();

	}

	@Override
	protected void setlayoutBackGround() {

	}

	@Override
	protected void findView() {
		spInfo = UspPreferences.getState(this);
		userCode = (EditText) this.findViewById(R.id.user_code);
		password = (EditText) this.findViewById(R.id.user_pwd);
		identifyingCode = (EditText) this.findViewById(R.id.identifying_code);
		rememberpwd = (CheckBox) this.findViewById(R.id.remember_pwd);
		loginBtn = (Button) this.findViewById(R.id.login_btn);
		anotherCode = (TextView) this.findViewById(R.id.another_code);

		setSpValues();

		// 设置光标的位置
		CharSequence userCodeText = userCode.getText();
		if (userCodeText instanceof Spannable) {
			Spannable spanText = (Spannable) userCodeText;
			Selection.setSelection(spanText, userCodeText.length());
		}
	}

	private void setSpValues() {
		String userName = (String) spInfo.get(UspPreferences.USERNAME);
		userCode.setText(userName);
		boolean rememberPwd = (Boolean) spInfo.get(UspPreferences.REMEMBER_PASSWORD);
		if (!rememberPwd) {
			rememberpwd.setChecked(false);
		} else {
			String passwordSp = (String) spInfo.get(UspPreferences.PASSWORD);
			password.setText(passwordSp);
		}
	}

	@Override
	protected void setListener() {
		/* 为 Button 注册点击事件监听对象，采用了匿名内部类的方式。 */
		loginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String userName = userCode.getText().toString();
				String passwordStr = password.getText().toString();
				String identifyingCodeStr = identifyingCode.getText().toString();

				if (StringUtils.isBlank(userName)) {
					showToastMsg("请输入工号！");
				} else if (StringUtils.isBlank(passwordStr)) {
					showToastMsg("请输入密码！");
				} else if (StringUtils.isBlank(identifyingCodeStr)) {
					showToastMsg("请输入验证码！");
				} else {
					login();
				}

			}

		});

		/* 为 换一张 注册点击事件监听对象 */
		anotherCode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 切换验证码
				getVerifyCode();
			}
		});
	}

	@Override
	protected void prepareData() {

	}

	// 获取验证码
	private void getVerifyCode() {

		RestParameters params = new RestParameters();

		client.apiGet(ApiUrls.GET_VERIFY_CODE, params, new RestApiListener() {

			@Override
			public void onComplete(JSONObject json) {
				// Log.e(TAG, json.toString());
				OperGetVerifyCodeResponse operGetVerifyCode = JsonToBeanUtils.parseToOperGetVerifyCodeResponse(json);
				jsessionid = operGetVerifyCode.getJsessionid();
				String verifyCode = operGetVerifyCode.getVerifyCode();
				if (verifyCode != null) {
					byte[] bitmapArray = BitmapUtil.decoderStringToByte(verifyCode);
					Bitmap bm = BitmapUtil.convertByteToBitmap(bitmapArray, null);
					codeImage.setImageBitmap(BitmapUtil.zoomBitmap(bm, codeImageWidth, codeImageHeight));
				} else {
					showToastMsg("服务无验证码返回！");
				}

				// 为appCache传递相应的值,用于在session过程中记录相关用户信息
				appCache.put("jsessionid", jsessionid);

			}

			@Override
			public void onError(ApiError error) {
				Log.i(TAG, error.toString());

			}

			@Override
			public void onException(Exception e) {
				Log.e(TAG, e.getMessage());
			}
		}, false);
	}

	private void login() {
		DataTask dataTask = new DataTask() {

			@Override
			public void onPreExecute() {
				showProgressDialog();
			}

			@Override
			public boolean doInBackground() {
				String userName = userCode.getText().toString();
				String passwordStr = password.getText().toString();
				boolean rememberPwd = rememberpwd.isChecked();
				String identifyingCodeStr = identifyingCode.getText().toString();
				// if (passwordStr.length() != 24) {
				// passwordStr = EncoderUtil.getMd5Encoder(passwordStr);
				// }
				UspPreferences.savedState(UspLoginActivity.this, userName, passwordStr, rememberPwd, false);

				OperLoginRequest login = new OperLoginRequest();

				login.setLoginCode(userName);// 登录名称
				login.setOperPwd(passwordStr);// 登录密码
				login.setJsessionid(jsessionid);// 当前的sessionId
				login.setVerifyCode(identifyingCodeStr);// 验证码

				boolean flag = getLoginInfo(login);
				if (flag) {
					return true;
				}else{
					//登录错误后重新获取验证码
					Message msg = handler.obtainMessage();
					msg.what = LOGIN_ERROR;
					msg.sendToTarget();
				}
				return false;
			}

			@Override
			public void showErr() {
				showToastMsg(loginErrorInfo);
			}

			@Override
			public void showData() {
				enter();
			}

		};

		prepareData(true, dataTask);

	}

	private void enter() {
		// 进入主页
		// 初始化当前位置
		Intent intent = new Intent();
		intent.setClass(UspLoginActivity.this, UspActivity.class);

		startActivity(intent);
		hideProgressDialog();
		UspLoginActivity.this.finish();
	};

	private boolean getLoginInfo(OperLoginRequest operLoginRequest) {

		loginResult = false;

		// 业务参数
		RestParameters params = ObjectToRestParamUtils.transOperLoginRequestToParam(operLoginRequest);

		client.apiPost(ApiUrls.OPER_LOGIN, params, new RestApiListener() {
			@Override
			public void onComplete(JSONObject json) {
				// Log.e(TAG, json.toString());
				OperLoginResponse operLoginResponse = JsonToBeanUtils.parseToOperLoginResponse(json);
				// 缓存操作员信息
				appCache.put("operLoginResponse", operLoginResponse);

				loginResult = true;
			}

			@Override
			public void onError(ApiError error) {
				Log.i(TAG, error.toString());
				if (!StringUtils.isBlank(error.getContent())) {
					loginErrorInfo = error.getContent();
				}
			}

			@Override
			public void onException(Exception e) {
				Log.e(TAG, e.getMessage());
			}
		}, false);

		return loginResult;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder build = new AlertDialog.Builder(this);
			build.setTitle(R.string.attention_title).setMessage(R.string.exit_prompt).setPositiveButton(R.string.OK_text, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			}).setNegativeButton(R.string.cancel_text, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			}).show();
			break;

		default:
			break;
		}
		return false;
	}

}
