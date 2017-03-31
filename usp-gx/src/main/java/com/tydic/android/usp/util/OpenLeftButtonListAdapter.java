/*
 * wcy.tydic
 */
package com.tydic.android.usp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tydic.android.usp.R;
import com.tydic.android.usp.activity.app.OpenActivity;

public class OpenLeftButtonListAdapter extends BaseAdapter {

	private static String TAG = "OpenLeftButtonListAdapter";

	private LayoutInflater inflater = null;
	private ArrayList<Map<String, Object>> items = null;

	private OpenActivity openActivity = null;

	private int selectedPosition = -1;

	public void setSelectedPosition(int position) {
		selectedPosition = position;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public OpenLeftButtonListAdapter(Context context, ArrayList<Map<String, Object>> arraylist) {
		// LayoutInflater用来加载界面
		inflater = LayoutInflater.from(context);
		// 保存适配器中的每项的文字信息
		this.items = arraylist;

		openActivity = (OpenActivity) context;

	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 保存每项中的控件的引用
	class ViewHolder {
		TextView title;
		TextView pic;
		LinearLayout layout;
	}

	@Override
	public View getView(int position, View convert, ViewGroup parent) {
		ViewHolder holder;
		if (convert == null) {
			// 调用LayoutInflater的inflate方法加载layout文件夹中的界面
			convert = inflater.inflate(R.layout.usp_open_left_listview_item, null);
			holder = new ViewHolder();
			holder.pic = (TextView) convert.findViewById(R.id.button_number);
			holder.title = (TextView) convert.findViewById(R.id.button_title);
			holder.layout = (LinearLayout) convert.findViewById(R.id.open_select_left_listView_item);
			// 保存包含当前项控件的对象
			convert.setTag(holder);
		} else {
			// 获取包含当前项控件的对象
			holder = (ViewHolder) convert.getTag();
		}
		// 设置当前项的内容
		Map<String, Object> item = (HashMap<String, Object>) items.get(position);
		// holder.pic.setBackgroundResource((Integer)item.get("PIC"));
		holder.title.setText(String.valueOf(item.get("TITLE")));
		holder.pic.setText(String.valueOf(item.get("INDEX")));

		// 此处每次选中一个都会重新画所有的items，如果是，那就在items里增加一个属性，颜色，从外面传进来，修改指定item的颜色
		// TODO

		Log.i(TAG, "刷新listview 当前位置：" + selectedPosition);

		if (selectedPosition == position) {
			// 选中
			holder.layout.setBackgroundResource(R.drawable.open_left_btn_pressed);
			holder.title.setTextColor(Color.rgb(157, 158, 158));
			holder.pic.setBackgroundResource(R.drawable.usp_left_number_grey_bg);
		} else {
			// 正常
			holder.layout.setBackgroundResource(R.drawable.open_btn_selector);
			holder.title.setTextColor(Color.rgb(72, 72, 72));
			holder.pic.setBackgroundResource(R.drawable.usp_left_number_normal_bg);
		}

		/*
		if (selectedPosition == 1 && position == 0) {// 点击选产品项
			setRed(holder);
			Log.i(TAG, "已经选的号码是：" + this.openActivity.getPage1SelectedPhone());
		}
		if (selectedPosition == 2 && position == 1) {// 点击选活动项
			setProductItem(holder);
		}
		if (selectedPosition == 3 && position == 2) {// 点击客户信息项
			setRed(holder);
		}
		if (selectedPosition == 4 && position == 3) {// 点击其他信息项
			setCustInfoItem(holder);
		}
		*/
		return convert;
	}

	/**
	 * 设置产品项样式
	 * 
	 * @param holder
	 */
	private void setProductItem(ViewHolder holder) {
		if (this.openActivity.verifyIsCompletedProductView()) {
			setRed(holder);
		} else {
			setGrey(holder);
		}
	}

	/**
	 * 设置产品信息样式
	 * 
	 * @param holder
	 */
	private void setCustInfoItem(ViewHolder holder) {
		if (this.openActivity.verifyIsCompletedProductView()) {
			setRed(holder);
		} else {
			setGrey(holder);
		}
	}

	/**
	 * 设置红色
	 * 
	 * @param holder
	 */
	private void setRed(ViewHolder holder) {
		holder.title.setTextColor(Color.rgb(220, 64, 2));
		holder.pic.setBackgroundResource(R.drawable.usp_left_number_red_bg);
		holder.pic.setTextColor(Color.rgb(220, 64, 2));
	}

	/**
	 * 设置灰色
	 * 
	 * @param holder
	 */
	private void setGrey(ViewHolder holder) {
		holder.title.setTextColor(Color.rgb(72, 72, 72));
		holder.pic.setBackgroundResource(R.drawable.usp_left_number_normal_bg);
		holder.pic.setTextColor(Color.rgb(157, 158, 158));
	}
}