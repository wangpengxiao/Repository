package com.tydic.android.usp.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tydic.android.usp.R;

/**
 * 按钮工具类
 * 
 */
public class TitleBarUtils {
	/**
	 * 
	 * @param dateString
	 * @return
	 */
	private static float buttonWidth = 48.0f;
	private static float lineWidth = 2.0f;

	public static enum position {
		left, right;
	}

	// 获取排列位置 left right
	private static int getPosition(position a) {

		int p = RelativeLayout.ALIGN_PARENT_LEFT;
		switch (a) {
		case left:
			p = RelativeLayout.ALIGN_PARENT_LEFT;
			break;
		case right:
			p = RelativeLayout.ALIGN_PARENT_RIGHT;
			break;

		}
		return p;

	}

	// 增加文本按钮
	public static Button addTitleBarTextButton(Activity context, ViewGroup titleBar, position p, int posNumber, String text) {

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout view = (RelativeLayout) layoutInflater.inflate(R.layout.button_text, null);
		Button commonBtnText = (Button) view.findViewById(R.id.button_text);
		commonBtnText.setText(text);

		int marginWidth = AppUtil.dp2Px(context, buttonWidth) * posNumber;

		titleBar.addView(view);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
		params.addRule(getPosition(p));
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		switch (p) {
		case left:
			params.leftMargin = marginWidth;
			break;
		case right:
			params.rightMargin = marginWidth;
			break;

		}

		view.setLayoutParams(params);

		return commonBtnText;
	}

	// 增加图形按钮 edit
	public static ImageButton addTitleBarImageButton(Activity context, ViewGroup titleBar, position p, int posNumber, int lineNumber, int layout, int id) {

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout view = (RelativeLayout) layoutInflater.inflate(layout, null);
		ImageButton commonBtnImage = (ImageButton) view.findViewById(id);
		int marginWidth = AppUtil.dp2Px(context, buttonWidth) * (posNumber - lineNumber) + AppUtil.dp2Px(context, lineWidth) * lineNumber;

		titleBar.addView(view);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
		params.addRule(getPosition(p));
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		switch (p) {
		case left:
			params.leftMargin = marginWidth;
			break;
		case right:
			params.rightMargin = marginWidth;
			break;

		}

		view.setLayoutParams(params);

		return commonBtnImage;

	}

	// 增加图片
	public static void addTitleBarImageView(Activity context, ViewGroup titleBar, position p, int posNumber, int lineNumber, int layout) {

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout view = (RelativeLayout) layoutInflater.inflate(layout, null);

		int marginWidth = AppUtil.dp2Px(context, buttonWidth) * (posNumber - lineNumber) + AppUtil.dp2Px(context, lineWidth) * lineNumber;

		titleBar.addView(view);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
		params.addRule(getPosition(p));
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		switch (p) {
		case left:
			params.leftMargin = marginWidth;
			break;
		case right:
			params.rightMargin = marginWidth;
			break;

		}

		view.setLayoutParams(params);

	}

	// 设置标题
	public static void setTitle(ViewGroup titleBar, String text) {

		TextView title = (TextView) titleBar.findViewById(R.id.head_title);
		title.setText(text);

	}

	// 设置关闭事件
	public static void setReturnEvent(final Activity content, View commBtn) {

		if (commBtn != null) {
			commBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					content.finish();// 返回
				}
			});
		}

	}

}
