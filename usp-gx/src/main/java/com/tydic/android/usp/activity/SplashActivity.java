package com.tydic.android.usp.activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.tydic.android.usp.R;
import com.tydic.android.usp.Usp;
import com.tydic.android.usp.UpdateService;
import com.tydic.android.usp.common.Logger;
import com.tydic.android.usp.common.RequestHead;
import com.tydic.android.usp.util.AppUtil;
import com.tydic.android.usp.util.NetWorkInfo;
/**
 * 启动页
 * 
 * 流程：先判断 是否 有网络连接，
 * 有网络连接再判断是否第一次启动
 */
public class SplashActivity extends BasicActivity {
	
	private static String TAG = "android-client-SplashActivity";
	
	private static final int DIALOG_OPEN_NETSETTINGS = 1;

	private static final int LOADING_INIT_DATABASE=1;//初始化数据库
	private static final int LOADING_CHECK_NET=2;//检查网络
	private static final int LOADING_INIT_MACHINE=3;//初始化设备信息
	private static final int DIALOG_OPEN_UPDATE = 4;
	private static final int LOADING_ENTER=200;//进入应用
	
	private TextView loadingTv;//载入进度文字
	private InitAppTask initAppTask;
	private Intent updateIntent = null;
	
	

	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOADING_INIT_DATABASE:
				loadingTv.setText("初始化数据库中...");
				break;
			case LOADING_CHECK_NET:
				loadingTv.setText("检查网络中...");
				break;
			case LOADING_INIT_MACHINE:
				loadingTv.setText("初始化设备信息...");
				break;
			case DIALOG_OPEN_UPDATE:
				loadingTv.setText("初始更新应用...");
				break;
			case LOADING_ENTER:
				enter();//进入应用
				break;
			default:
				break;
			}
		};
	};

	@Override	
	protected void setlayout() {
		//全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
	}
		@Override	
	protected void findView() {
		loadingTv=(TextView)findViewById(R.id.loading_tv);
		loadingTv.setVisibility(View.VISIBLE);
	}
	@Override
	protected void setlayoutBackGround() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog dialog = null;
		switch(id){
			case DIALOG_OPEN_NETSETTINGS:
				dialog = new AlertDialog.Builder(this)
						.setTitle(R.string.dialog_noNetTitle)
						.setCancelable(false)
						.setMessage(R.string.dialog_noNetMsg)
						.setPositiveButton(R.string.dialog_setNet, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
								startActivity(intent);
							}
						}).setNegativeButton(R.string.dialog_exit, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//退出！
								finish();
							}
						}).create();
				break;
			case DIALOG_OPEN_UPDATE:
				dialog = new AlertDialog.Builder(this)
						.setTitle(R.string.update_title)
						.setMessage(R.string.update_message)
						.setPositiveButton(R.string.update_positive_button, new DialogInterface.OnClickListener() {
			                 public void onClick(DialogInterface dialog, int which) {
			                     //开启更新服务UpdateService
			                     updateIntent = new Intent(SplashActivity.this, UpdateService.class);
			                     handler.sendEmptyMessage(DIALOG_OPEN_UPDATE);
			                     startService(updateIntent);
			                     
			                     enter();
			                 }
						})
			             .setNegativeButton(R.string.update_negative_button,new DialogInterface.OnClickListener(){
			                 public void onClick(DialogInterface dialog, int which) {
			                     dialog.dismiss();
			                     enter();
			                 }
			             }).create();
		}
		
		return dialog;
	}

	//进入应用
	private void enter() {
		startAppHomeActivity();
	}
	
	/**
	 * 启动引导页
	 */
//	private void startGuideActivity(){
//		Intent intent = new Intent(this,GuideActivity.class);
//		startActivity(intent);
//		finish();
//	}
	/**
	 * 启动 首页
	 */
	private void startAppHomeActivity(){
		Intent intent  = new Intent(this,UspLoginActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); 
		Logger.d("guideTime",System.currentTimeMillis()+"; SplashActivity  finish()之后");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(initAppTask!=null&&initAppTask.getStatus()==AsyncTask.Status.RUNNING)
			initAppTask.cancel(true);
		initAppTask = new InitAppTask();//运行初始化程序任务
		initAppTask.execute(new Object[]{});
	}
	
	
	
	@Override
	protected void onDestroy() {
//		if(updateIntent != null){
//			stopService(updateIntent);
//		}
		super.onDestroy();
	}



	public class InitAppTask extends AsyncTask<Object, Object, Object>{
		private boolean netOk=false;//网络状态
		private boolean newVersion = false;//新版本
		@Override
		protected Object doInBackground(Object... params) {
			//2.检查网络
			netOk = checkNect();
			Logger.i(TAG, "netOk=" + netOk);
			if(!netOk){
				publishProgress(LOADING_CHECK_NET);//检查网络需要弹Dialog,在UI进程中弹出
				return null;
			}
			//3.初始化设备信息
			initMachineData();
			//4检查版本更新
			if(checkVersion()){
				newVersion = true;
				publishProgress(DIALOG_OPEN_UPDATE);//检查网络需要弹Dialog,在UI进程中弹出
			}
			return null;
		}
		

		@Override
		protected void onPostExecute(Object result) {
			if(newVersion){
				return;
			}
			//最后发送 进入应用
			if(netOk){
				handler.sendEmptyMessageDelayed(LOADING_ENTER, 500);
			}
		}
		
		@Override
		protected void onProgressUpdate(Object... values) {//可以执行一些UI进程操作
			Integer loadingPhase = (Integer)values[0];
			switch(loadingPhase){
				case LOADING_CHECK_NET://网络检查
					showDialog(DIALOG_OPEN_NETSETTINGS);
					break;
				case DIALOG_OPEN_UPDATE://版本更新
					showDialog(DIALOG_OPEN_UPDATE);
					break;
			}
		}

	}
	

	/**
	 * 初始化设备信息
	 */
	private void initMachineData() {
		//初始化请求头信息
		handler.sendEmptyMessageDelayed(LOADING_INIT_MACHINE,500);
		AppUtil.initMachineInfo(this);
		initUsp();
		RequestHead.init(getApplicationContext());
	}

	private void initUsp() {
		Usp.localVersion = AppUtil.getVersionCode(this); //设置本地版本号
		Usp.serverVersion = 1;//假定服务器版本为2，本地版本默认是1
		
	}
	/**检查网络
	 * @return
	 */
	private boolean checkNect() {
		handler.sendEmptyMessage(LOADING_CHECK_NET);
		return NetWorkInfo.isNetAvailable(this);
	}
	/**
	 * 检查更新版本
	 */
	public boolean checkVersion(){
		if(Usp.localVersion < Usp.serverVersion){
	        return true;
	    }else{
	    	return false;
	    }
	}
	
	
		
}
