package com.tydic.android.usp.ui;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tydic.android.usp.R;

/**
 * 显示进度VIEW
 * @function : 
 */
public class LoadingView{
	
	private Context context;
	public LoadingView(Context context){
		this.context = context;
	}
	
	public LinearLayout addLoadingLinearLayout(){
		LayoutInflater mInflater = ((Activity) context).getLayoutInflater();
		LinearLayout layout = new LinearLayout(context);
		View view = mInflater.inflate(R.layout.public_loading, layout,
				true);
		ImageView iView = (ImageView)view.findViewById(R.id.progressimg);
		TextView msgTextView = (TextView)view.findViewById(R.id.noMsg_textView);
		final AnimationDrawable animDrawable = (AnimationDrawable)context.getResources().getDrawable(R.anim.loading);
		iView.setBackgroundDrawable(animDrawable);
		iView.post(new Runnable(){
	           @Override
	           public void run() {
	               animDrawable.start();
	           }
	        });
		return layout;
	}
	
	
	
}
