package com.tydic.android.usp.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.slidingmenu.lib.SlidingMenu;
import com.tydic.android.usp.R;
import com.tydic.android.usp.activity.BasicActivity;
import com.tydic.android.usp.ui.cache.AppCache;
import com.tydic.android.usp.uss.response.OperLoginResponse;

/**
 * 自定义SlidingMenu 侧拉菜单类
 */
public class DrawerView implements OnClickListener {
	private final Activity activity;
	SlidingMenu localSlidingMenu;
	private TextView logout;
	private TextView loginCode;
	private TextView operName;
	private TextView operDept;
	private int scrWidth;

	public DrawerView(Activity activity, int scrWidth) {
		this.activity = activity;
		this.scrWidth = scrWidth;
	}

	public SlidingMenu initSlidingMenu() {
		Context context = activity.getApplicationContext();
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		// Logger.i("packageName", "pkg:" + cn.getPackageName());
		// Logger.i("className", "cls:" + cn.getClassName());

		localSlidingMenu = new SlidingMenu(activity);
		if (!"com.tydic.android.usp.activity.UspLoginActivity".equals(cn.getClassName())) {
			localSlidingMenu.setMode(SlidingMenu.LEFT);// 设置向右滑菜单
			localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);// 设置要使菜单滑动，触碰屏幕的范围
			// localSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			// localSlidingMenu.setTouchModeBehind(SlidingMenu.SLIDING_CONTENT);//设置了这个会获取不到菜单里面的焦点，所以先注释掉
			localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);// 设置阴影图片的宽度
			localSlidingMenu.setShadowDrawable(R.drawable.usp_shadow);// 设置阴影图片
			localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// SlidingMenu划出时主页面显示的剩余宽度
			localSlidingMenu.setBehindWidth(scrWidth / 5);
			localSlidingMenu.setFadeDegree(0.35f);// SlidingMenu滑动时的渐变程度
			localSlidingMenu.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);// 使SlidingMenu附加在Activity右边
			// localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//设置SlidingMenu菜单的宽度
			localSlidingMenu.setMenu(R.layout.usp_navigation_pop);
			// localSlidingMenu.toggle();//动态判断自动关闭或开启SlidingMenu

			AppCache appCache = AppCache.get(context);

			OperLoginResponse operLoginInfo = (OperLoginResponse) appCache.getAsObject("operLoginResponse");

			initView(operLoginInfo);
		}

		return localSlidingMenu;
	}

	private void initView(OperLoginResponse operLoginInfo) {

		logout = (TextView) localSlidingMenu.findViewById(R.id.usp_navigation_logout);
		logout.setOnClickListener(this);

		loginCode = (TextView) localSlidingMenu.findViewById(R.id.usp_navigation_login_code);
		operName = (TextView) localSlidingMenu.findViewById(R.id.usp_navigation_oper_name);
		operDept = (TextView) localSlidingMenu.findViewById(R.id.usp_navigation_oper_dept);

		if (operLoginInfo != null) {
			loginCode.setText(operLoginInfo.getLoginCode());
			operName.setText(operLoginInfo.getOperName());
			operDept.setText(operLoginInfo.getOperId());
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.usp_navigation_logout:
			((BasicActivity) activity).returnLogin();
			break;

		default:
			break;
		}
	}
}
