package com.tydic.android.usp.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.R.anim;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.slidingmenu.lib.SlidingMenu;
import com.tydic.android.rest.RestAndroidClient;
import com.tydic.android.usp.R;
import com.tydic.android.usp.Usp;
import com.tydic.android.usp.UspPreferences;
import com.tydic.android.usp.activity.ActivityStateList.ActivityState;
import com.tydic.android.usp.common.GroupAdapter;
import com.tydic.android.usp.common.Logger;
import com.tydic.android.usp.common.UspBroadcastReceiver;
import com.tydic.android.usp.exception.DefaultExceptionHandler;
import com.tydic.android.usp.ui.DrawerView;
import com.tydic.android.usp.ui.cache.AppCache;
import com.tydic.android.usp.ui.constant.Global;
import com.tydic.android.usp.util.NetWorkInfo;
import com.tydic.android.usp.util.TitleBarUtils;

public abstract class BasicActivity extends Activity {

	protected LayoutInflater mLayoutInflater;
	protected WindowManager mWindowManager;

	private ActivityState thisActivityState = new ActivityState(false);
	private Button left;// 左边按钮
	private TextView center;// 头部中间文字
	private Button right;// 右侧按钮
	// search box
	private LinearLayout searchBox;// search box
	private Button button_search;// search Button
	private boolean isSearchBoxOpen = false;
	private View.OnClickListener searchListener;// search listener

	protected Bundle savedInstanceState;
	private DataTask dataTask;// 数据回调接口
	private onFooterClick of;
	private LayoutParams params = new LayoutParams(0, 0);
	private Animation animationTranslate, animationRotate, animationScale;
	private Animation fadeIn;
	private Animation fadeOut;
	private boolean isClick;
	private int guideResourceId = 0;// 引导页图片资源id
	private ProgressDialog progressDialog = null;// 进度等待对话框

	protected RelativeLayout footerView;
	private ProgressBar footerprogressBar;
	private TextView footertextView;
	private Button footerbutton;
	protected int pagecode;
	private updateTask uTask;// 获取数据异步任务
	protected int index_bg;

	private ImageView guideImage;// 引导页图片

	private static final int EXE_SUCCESS = 0;// 获取数据成功
	private static final int EXE_FAIL = 1;// 获取数据失败
	private static final int FOOT_EXE_SUCCESS = 2;// 获取数据成功
	private static final int FOOT_EXE_FAIL = 3;// 获取数据失败

	// navigation bar
	protected RelativeLayout titleBar;
	// protected Button commonBtnSearchUser;
	protected ImageButton commonBtnSlider;
	protected ImageButton commonBtnNotify;
	protected ImageButton commonBtnHome;
	protected ImageButton commonBtnSettings;

	// navigate menu
	protected PopupWindow navMenu;
	public static SlidingMenu mSlidingMenu; // 侧边栏控件
	protected ListView lv_group;
	protected View view;
	protected View top_title;
	protected TextView tvtitle;
	protected List<String> groups;

	// appCache主要是缓存用户会话过程中的一些用户的基本信息
	protected AppCache appCache;

	private static final ExecutorService singleExecutor = Executors.newSingleThreadExecutor();// 单线程执行者，用于执行footer任务

	protected RestAndroidClient client = RestAndroidClient.getAndroidClientByAppKey("20140929");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 异常捕获
		Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
		// 注册退出注销广播
		registerReceiver();

		if (mLayoutInflater == null) {
			mLayoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		if (mWindowManager == null) {
			mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		}

		appCache = AppCache.get(this);
		getWindow().setFormat(PixelFormat.RGBA_8888);

		setTitle();
		setlayout();// 设置布局
		setlayoutBackGround();
		init();// 初始化头部view
		findView();// 查找视图
		setListener();// 添加事件
		prepareData();

		initSlidingMenu();

		ActivityStateList.addState(thisActivityState);
		Logger.d("UpdateReceiver", "add " + this.getClass().getSimpleName());
	}

	@Override
	protected void onStart() {
		super.onStart();
		addGuideImage();
	}

	@Override
	protected void onResume() {
		super.onResume();
		thisActivityState.isPause = false;
	}

	/** 设置窗口title */
	protected void setTitle() {
		// 设置顶部lable title样式
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	/** 载入布局 */
	protected abstract void setlayout();

	/** 载入布局 */
	protected abstract void setlayoutBackGround();

	/** 查找view */
	protected abstract void findView();

	/** 设置事件处理程序 */
	protected abstract void setListener();

	/** 异步获取数据 */
	protected abstract void prepareData();

	/** 退出 注销广播 */
	private BroadcastReceiver mKillReceiver;

	private void registerReceiver() {
		// 注册
		mKillReceiver = new UspBroadcastReceiver(this);
		IntentFilter filter = new IntentFilter();
		filter.addAction(Usp.EXITACTION);
		filter.addAction(Usp.LOGOUTACTION);
		registerReceiver(mKillReceiver, filter);

	}

	private void unregisterReceiver() {
		// 注销
		if (mKillReceiver != null)
			unregisterReceiver(mKillReceiver);
	}

	@Override
	protected void onPause() {
		super.onPause();
		thisActivityState.isPause = true;
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver();// 注销广播
		super.onDestroy();
		// 2012-05-08 添加取消任务代码
		if (uTask != null && uTask.getStatus() == AsyncTask.Status.RUNNING) {
			uTask.cancel(true);
		}
		ActivityStateList.delState(thisActivityState);
		Logger.d("UpdateReceiver", "del " + this.getClass().getSimpleName());
	}

	/**
	 * ListView底部按钮回调接口
	 *
	 */
	public interface onFooterClick {
		public boolean doInThread();

		public void notifyChanged();

		public void showErr();
	}

	protected void initSlidingMenu() {
		int scrWidth = mWindowManager.getDefaultDisplay().getWidth();
		mSlidingMenu = new DrawerView(this, scrWidth).initSlidingMenu();
	}

	// Listview页脚
	protected RelativeLayout getListFooterView(final onFooterClick of) {
		this.of = of;
		LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		footerView = (RelativeLayout) mInflater.inflate(R.layout.listfooterview, null);
		// 进度条
		footerprogressBar = (ProgressBar) footerView.findViewById(R.id.footer_progressbar);
		// 文本内容
		footertextView = (TextView) footerView.findViewById(R.id.footer_textview);

		footerprogressBar.setVisibility(View.INVISIBLE);
		footertextView.setVisibility(View.VISIBLE);

		footerbutton = (Button) footerView.findViewById(R.id.footer_button);
		footerbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				footerprogressBar.setVisibility(View.VISIBLE);
				// footertextView.setVisibility(View.VISIBLE);
				footertextView.setText("正在载入...");
				footerbutton.setVisibility(View.GONE);
				// 开线程去下载网络数据
				singleExecutor.execute(new Runnable() {
					@Override
					public void run() {
						if (of != null && of.doInThread()) {
							Message message = new Message();
							message.what = FOOT_EXE_SUCCESS;
							handler.sendMessage(message);
						} else {
							Message message = new Message();
							message.what = FOOT_EXE_FAIL;
							handler.sendMessage(message);
						}

						handler.post(new Runnable() {
							@Override
							public void run() {
								// 执行完毕，恢复界面
								footerprogressBar.setVisibility(View.INVISIBLE);
								footertextView.setVisibility(View.INVISIBLE);
								footertextView.setText("查看更多...");
								footerbutton.setVisibility(View.VISIBLE);
							}
						});
					}
				});
			}
		});
		return footerView;
	}

	/** 回调接口 */
	public interface DataTask {
		/** 准备工作 UIThread */
		void onPreExecute();

		/** 执行后台任务 BgThread */
		boolean doInBackground();

		/** 后台任务执行失败 UIThread */
		void showErr();

		/** 显示数据 UIThread */
		void showData();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case EXE_SUCCESS:
				dataTask.showData();
				break;
			case EXE_FAIL:
				dataTask.showErr();
				break;
			case FOOT_EXE_SUCCESS:
				of.notifyChanged();
				break;
			case FOOT_EXE_FAIL:
				of.showErr();
				break;

			}
		}
	};

	/**
	 * 获取数据任务
	 *
	 */
	private class updateTask extends AsyncTask<String, String, Integer> {
		@Override
		protected Integer doInBackground(String... params) {
			int flg = EXE_FAIL;
			if (dataTask.doInBackground()) {
				flg = EXE_SUCCESS;// 成功
			}
			return flg;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			Message msg = handler.obtainMessage();
			if (result == EXE_SUCCESS) {
				msg.what = EXE_SUCCESS;
				if (guideImage != null)// 数据加载完后显示引导图片
					guideImage.setVisibility(View.VISIBLE);
			} else {
				msg.what = EXE_FAIL;
			}
			msg.sendToTarget();
			// 执行完毕，隐藏进度
			if (progressDialog != null) {
				hideProgressDialog();
			}
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// 开始执行，显示进度
			dataTask.onPreExecute();
		}

	};

	/**
	 * 执行获取数据任务
	 * 
	 * @param dataTask
	 */
	protected void prepareData(DataTask dataTask) {
		this.dataTask = dataTask;
		if (NetWorkInfo.isNetAvailable(this)) {
			// 取消了上一个任务FIXME
			if (uTask != null && uTask.getStatus() == AsyncTask.Status.RUNNING) {
				uTask.cancel(true);
			}
			uTask = new updateTask();
			// 执行任务
			uTask.execute();
		} else {
			// final DataTask dataTask2 = dataTask;
			final Activity thisActivity = this;
			if (!NetWorkInfo.isNetAvailable(this)) {
				// 无连接
				new AlertDialog.Builder(this).setTitle(R.string.dialog_noNetTitle).setMessage(R.string.dialog_noNetMsg).setPositiveButton("重试", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 重新加载数据
						BasicActivity.this.prepareData(BasicActivity.this.dataTask);
					}
				}).setNegativeButton("返回", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						thisActivity.finish();
					}
				}).create().show();
			}
		}
	}

	/**
	 * 执行获取数据任务
	 * 
	 * @param isCheckNet
	 *            是否处理无网络情况，false 不处理无网络（忽视是否有网络）
	 * @param dataTask
	 */
	protected void prepareData(Boolean isCheckNet, DataTask dataTask) {
		// 若 isCheckNet = false，则
		boolean flag = ((!isCheckNet) ? true : (NetWorkInfo.isNetAvailable(this)));
		if (flag) {
			prepareData(dataTask);
		} else {
			dealNoNet();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// menu.add(Menu.NONE, 6, 1, "切换账户");
		// menu.add(Menu.NONE, 7, 2, "退出");

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int itemId = item.getItemId();
		switch (itemId) {
		case 6:
			// Toast.makeText(this, "切换账户", Toast.LENGTH_SHORT).show();
			this.finish();
			// FIXME 切换账户
			break;
		case 7:
			// FIXME 退出应用
			break;
		}
		return true;
	}

	public void dealNoNet() {
		if (!NetWorkInfo.isNetAvailable(this)) {
			// 无连接
			new AlertDialog.Builder(this).setTitle(R.string.dialog_noNetTitle).setMessage(R.string.dialog_noNetMsg).setNegativeButton(R.string.dialog_exit, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();// 退出
					dialog.cancel();
				}
			}).setPositiveButton(R.string.dialog_setNet, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// 进入设置网络界面
					Intent intentToNetwork = new Intent("/");
					ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
					intentToNetwork.setComponent(componentName);
					intentToNetwork.setAction("android.intent.action.VIEW");
					startActivity(intentToNetwork);
					dialog.cancel();
				}
			}).create().show();
		}

	}

	public Button getRightButton() {
		return right;
	}

	/**
	 * FIXME GridView 设置，暂时清理代码 为了解决 GridView形式 Tab的高度变化问题而统一设置高度的方法 在 find
	 * GridView类型的Tab之后 调用此 方法即可。
	 * 
	 * @param context
	 */
	public void setGridViewTabHeightToDefault(GridView gridViewTabToSet) {
		if (gridViewTabToSet == null) {
			return;
		}
		final ViewGroup.LayoutParams layoutParams = gridViewTabToSet.getLayoutParams();
		layoutParams.height = 96;
		gridViewTabToSet.setLayoutParams(layoutParams);
	}

	/**
	 * 显示进度对话等待 +文字
	 * 
	 * @param text
	 */
	public void showProgressDialog() {
		showProgressDialog("正在向服务器提交请求");
	}

	public void hideProgressDialog() {
		progressDialog.dismiss();
	}

	/**
	 * 显示进度对话等待 +文字
	 * 
	 * @param text
	 */
	public void showProgressDialog(String text) {
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(text);
		progressDialog.setTitle("请稍候");
		progressDialog.show();
	}

	// -------------------公共头部view代码
	private void init() {
		Logger.i("BasicActivity", "init public head view");

	}

	/**
	 * 初始化导航标题和菜单
	 * 
	 * @param
	 */
	protected void initNavigation() {
		Logger.i("BasicActivity", "init navitation bar & navigation menu");

		titleBar = (RelativeLayout) findViewById(R.id.app_head_blank);
		commonBtnSlider = TitleBarUtils.addTitleBarImageButton(this, titleBar, TitleBarUtils.position.left, 0, 0, R.layout.usp_button_bar_slider, R.id.common_btn_bar_slider);
		TitleBarUtils.addTitleBarImageView(this, titleBar, TitleBarUtils.position.left, 1, 0, R.layout.usp_image_bar_cutline);

		commonBtnSettings = TitleBarUtils.addTitleBarImageButton(this, titleBar, TitleBarUtils.position.right, 0, 0, R.layout.button_image_settings, R.id.common_btn_settings);
		TitleBarUtils.addTitleBarImageView(this, titleBar, TitleBarUtils.position.right, 1, 0, R.layout.usp_image_bar_cutline);
		commonBtnNotify = TitleBarUtils.addTitleBarImageButton(this, titleBar, TitleBarUtils.position.right, 2, 1, R.layout.button_image_notify, R.id.common_btn_notify);

		commonBtnSlider.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// commonBtnSearchUser.startAnimation(rotateLeft);
				// showNavigation(v);

				mSlidingMenu.toggle();
			}
		});

		commonBtnSettings.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToastMsg(Global.ON_BUILDING_INFO);
			}
		});
		commonBtnNotify.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToastMsg(Global.ON_BUILDING_INFO);
			}
		});

		commonBtnHome = TitleBarUtils.addTitleBarImageButton(this, titleBar, TitleBarUtils.position.right, 3, 1, R.layout.button_image_home, R.id.common_btn_home);

	}

	// -------------------头部view代码

	public void showToastMsg(String msg) {
		Toast.makeText(this, msg, 0).show();
	}

	protected Bitmap loadImgThumbnail(String imgName, int kind) {
		Bitmap bitmap = null;

		String[] proj = { MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME };

		Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj, MediaStore.Images.Media.DISPLAY_NAME + "='" + imgName + "'", null, null);

		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			ContentResolver crThumb = getContentResolver();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			bitmap = MediaStore.Images.Thumbnails.getThumbnail(crThumb, cursor.getInt(0), kind, options);
		}
		return bitmap;
	}

	protected Animation setAnimScale(float toX, float toY) {
		// TODO Auto-generated method stub
		animationScale = new ScaleAnimation(1f, toX, 1f, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.45f);
		animationScale.setInterpolator(this, anim.accelerate_decelerate_interpolator);
		animationScale.setDuration(500);
		animationScale.setFillAfter(false);
		return animationScale;
	}

	protected Animation animRotate(float toDegrees, float pivotXValue, float pivotYValue) {
		// TODO Auto-generated method stub
		animationRotate = new RotateAnimation(0, toDegrees, Animation.RELATIVE_TO_SELF, pivotXValue, Animation.RELATIVE_TO_SELF, pivotYValue);
		animationRotate.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				animationRotate.setFillAfter(true);
			}
		});
		return animationRotate;
	}

	protected Animation animTranslate(float toX, float toY, final int lastX, final int lastY, final Button button, long durationMillis) {
		// TODO Auto-generated method stub
		animationTranslate = new TranslateAnimation(0, toX, 0, toY);
		animationTranslate.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				if (!isClick) {
					button.setVisibility(View.GONE);
				}

				// params = new LayoutParams(0, 0);
				// params.height = 50;
				// params.width = 50;
				params.setMargins(0, lastX, lastY, 0);
				// button.setLayoutParams(params);
				button.clearAnimation();

			}
		});
		animationTranslate.setDuration(durationMillis);
		return animationTranslate;
	}

	/**
	 * send an email to service
	 */
	private void sendEmail(String emailSubject, String emailBody) {
		// 系统邮件系统的动作为android.content.Intent.ACTION_SEND
		Intent email = new Intent(android.content.Intent.ACTION_SEND);
		email.setType("plain/text");
		// 设置邮件默认地址
		email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {});
		// 设置邮件默认标题
		email.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
		// 设置要默认发送的内容
		email.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);
		// 调用系统的邮件系统
		this.startActivity(Intent.createChooser(email, "请选择邮件发送软件"));
	}

	/**
	 * 设置引导页。在setLayout 或者 findView中调用。
	 * 并在布局xml的根元素上设置android:id="@id/vancl_content_view"
	 * 
	 * @param resId
	 *            引导页图片资源id
	 */
	public void setGuideResId(int resId) {
		this.guideResourceId = resId;
	}

	public void addGuideImage() {
		// View view =
		// getWindow().getDecorView().findViewById(R.id.vancl_content_view);
		View view = null;
		if (view == null)
			return;
		if (UspPreferences.activityIsGuided(this, this.getClass().getName())) {
			// 引导过了
			return;
		}
		ViewParent viewParent = view.getParent();
		if (viewParent instanceof FrameLayout) {
			final FrameLayout frameLayout = (FrameLayout) viewParent;
			if (guideResourceId != 0) {// 设置了引导图片
				guideImage = new ImageView(this);
				guideImage.setVisibility(View.INVISIBLE);
				FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
				guideImage.setLayoutParams(params);
				guideImage.setScaleType(ScaleType.FIT_XY);
				guideImage.setImageResource(guideResourceId);
				guideImage.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						frameLayout.removeView(guideImage);
						guideImage.getDrawable().setCallback(null);
						UspPreferences.setIsGuided(getApplicationContext(), BasicActivity.this.getClass().getName());
					}
				});
				frameLayout.addView(guideImage);// 添加引导图片

			}
		}
	}

	// navigation menu
	private void showNavigation(View parent) {

		int scrWidth = mWindowManager.getDefaultDisplay().getWidth();
		int scrHeight = mWindowManager.getDefaultDisplay().getHeight();
		if (navMenu == null) {

			view = mLayoutInflater.inflate(R.layout.main_navigation_pop, null);

			lv_group = (ListView) view.findViewById(R.id.lvGroup);
			// 加载数据
			groups = new ArrayList<String>();
			groups.add("修改密码");
			groups.add("切换用户");
			groups.add("退出系统");

			GroupAdapter groupAdapter = new GroupAdapter(this, groups);
			// lv_group.setAdapter(groupAdapter);
			// 创建一个PopuWidow对象
			navMenu = new PopupWindow(view, scrWidth / 3, scrHeight);

		}

		// 使其聚集
		navMenu.setFocusable(true);
		// 设置允许在外点击消失
		navMenu.setOutsideTouchable(true);

		navMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				//  TODO Auto-generated method stub
				// button_dropdown.startAnimation(rotateRight);
			}
		});

		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		navMenu.setBackgroundDrawable(new BitmapDrawable());

		navMenu.setAnimationStyle(R.style.PopupAnimation);
		navMenu.showAtLocation(parent, Gravity.LEFT | Gravity.TOP, 0, 0);
		navMenu.update();

		lv_group.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

				Toast.makeText(BasicActivity.this, groups.get(position), 1000).show();

				if (navMenu != null) {
					navMenu.dismiss();
				}
			}
		});
	}

	/*protected void returnHome() {
		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setTitle(R.string.attention_title).setMessage(R.string.exit_prompt).setPositiveButton(R.string.OK_text, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(BasicActivity.this, UspActivity.class);
				startActivity(intent);
				//finish();
			}
		}).setNegativeButton(R.string.cancel_text, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		}).show();
	}*/

	protected void returnHome() {
		Intent intent = new Intent(BasicActivity.this, UspActivity.class);
		startActivity(intent);
	}
	
	public void returnLogin() {
		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setTitle(R.string.attention_title).setMessage(R.string.exit_prompt).setPositiveButton(R.string.OK_text, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				appCache.clear();
				Intent intent = new Intent(BasicActivity.this, UspLoginActivity.class);
				startActivity(intent);
				finish();
				System.exit(0);
			}
		}).setNegativeButton(R.string.cancel_text, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		}).show();
		
	}

}
