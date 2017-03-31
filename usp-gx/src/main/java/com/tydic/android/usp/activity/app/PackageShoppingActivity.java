package com.tydic.android.usp.activity.app;

import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.tydic.android.usp.R;
import com.tydic.android.usp.UspPreferences;
import com.tydic.android.usp.activity.BasicActivity;
import com.tydic.android.usp.util.TitleBarUtils;

public class PackageShoppingActivity extends BasicActivity {

	// PackageShopping List
	private LinearLayout packageShoppingGrid;
	private GridView packageShoppingGridView;

	private static String TAG = "PackageShoppingActivity";

	@Override
	protected void setlayout() {
		Log.i(getClass().getName(), "PackageShoppingActivity.onCreate");
		setContentView(R.layout.avtivity_package_shopping); // main page

		initNavigation();
		TitleBarUtils.setTitle(titleBar, "套餐导购");

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
		// 主页按钮的点击事件
		commonBtnHome.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				returnHome();
			}
		});
	}

	@Override
	protected void prepareData() {
		createPackageShoppingList();

	}

	private void createPackageShoppingList() {

		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		int scrWidth = windowManager.getDefaultDisplay().getWidth();

		List<Map<String, Object>> mList = UspPreferences.getPackageShoppingListData();

		packageShoppingGrid = (LinearLayout) this.findViewById(R.id.package_shopping_grid);
		packageShoppingGridView = (GridView) this.findViewById(R.id.package_shopping_gridView);
		packageShoppingGridView.setColumnWidth(scrWidth / 2);

		SimpleAdapter adapterGrid = new SimpleAdapter(this, mList, R.layout.package_shopping_gridview_item, // 自定义布局格式
				new String[] { "PIC", "TITLE" }, new int[] { R.id.package_shopping_gridView_pic, R.id.package_shopping_gridView_title });

		packageShoppingGridView.setAdapter(adapterGrid);

		packageShoppingGridView.setEnabled(true);

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
