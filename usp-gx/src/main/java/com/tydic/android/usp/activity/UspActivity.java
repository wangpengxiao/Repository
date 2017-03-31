package com.tydic.android.usp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tydic.android.usp.R;
import com.tydic.android.usp.activity.app.OpenActivity;
import com.tydic.android.usp.activity.app.PackageShoppingActivity;
import com.tydic.android.usp.ui.HorizontalListView;
import com.tydic.android.usp.ui.bean.MainItemInfo;
import com.tydic.android.usp.ui.constant.Global;
import com.tydic.android.usp.uss.response.OperLoginResponse;
import com.tydic.android.usp.util.TitleBarUtils;

public class UspActivity extends BasicActivity {

	private static String TAG = "UspActivity";

	// image button
	ImageButton button_switch_content;

	Button button_dropdown;
	Button button_exit;
	// 个人信息
	private TextView userName;

	@Override
	protected void setlayout() {
		Log.i(getClass().getName(), "UspActivity.onCreate");
		setContentView(R.layout.usp_main); // main page

		initNavigation();
		TitleBarUtils.setTitle(titleBar, "主页");

		HorizontalListView listview = (HorizontalListView) findViewById(R.id.main_item_list);
		listview.setAdapter(mAdapter);

		commonBtnHome.setBackgroundResource(R.drawable.usp_topbar_btn_pressed);
	}

	private static MainItemInfo[] dataItems = Global.MAIN_ITEM_INFOS;

	private BaseAdapter mAdapter = new BaseAdapter() {

		private OnClickListener onItemClicked = new OnClickListener() {

			@Override
			public void onClick(View v) {
				int position = (Integer) v.getTag();
				Intent intent = null;
				if (position == 0) {
					intent = new Intent(UspActivity.this, OpenActivity.class);

				} else if (position == 2) {
					intent = new Intent(UspActivity.this, PackageShoppingActivity.class);
				}
				itemStartActivity(intent);

			}
		};

		@Override
		public int getCount() {
			return dataItems.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.usp_main_item, null);
			TextView itemTitle = (TextView) retval.findViewById(R.id.main_item_title);
			ImageView iconImage = (ImageView) retval.findViewById(R.id.main_item_icon);

			itemTitle.setText(dataItems[position].getItemTitle());
			iconImage.setImageResource(dataItems[position].getItemIcon());
			retval.setBackgroundResource(dataItems[position].getItemContentBackground());
			retval.setTag(position);
			retval.setOnClickListener(onItemClicked);

			return retval;
		}

	};

	private void itemStartActivity(Intent intent) {
		if (intent != null) {
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		} else {
			showToastMsg(Global.ON_BUILDING_INFO);
		}

	}

	@Override
	protected void setlayoutBackGround() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void findView() {

	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void prepareData() {

		// 获取从登录成功后界面的缓存的基本信息
		String jsessionid = appCache.getAsString("jsessionid");
		OperLoginResponse operLoginInfo = (OperLoginResponse) appCache.getAsObject("operLoginResponse");

		Log.i(TAG, "jsessionid : " + jsessionid);
		Log.i(TAG, "operName : " + operLoginInfo.getOperName());

	}

	// 添加菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	// 菜单点击事件处理
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.help_menu:
			Toast.makeText(this, "你选中的是 ‘帮助’ 菜单！", Toast.LENGTH_SHORT).show();
			break;
		case R.id.open_menu:
			Toast.makeText(this, "你选中的是 ‘反馈’ 菜单！", Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
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
