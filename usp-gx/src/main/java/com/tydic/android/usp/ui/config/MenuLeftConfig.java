package com.tydic.android.usp.ui.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tydic.android.usp.R;

/**
 * 左侧菜单配置项
 * 
 * @author lichao
 *
 */
public class MenuLeftConfig {

	/**
	 * 获取左侧开户菜单的配置
	 */
	public static ArrayList<Map<String, Object>> getOpenLeftMenuMap() {

		ArrayList<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_activity);
		map.put("TITLE", "选号码");
		map.put("INDEX", "1");
		mList.add(map);

		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_activity);
		map.put("TITLE", "选产品");
		map.put("INDEX", "2");
		mList.add(map);

		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_activity);
		map.put("TITLE", "选活动");
		map.put("INDEX", "3");
		mList.add(map);

		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_activity);
		map.put("TITLE", "客户信息");
		map.put("INDEX", "4");
		mList.add(map);

		map = new HashMap<String, Object>();
		map.put("PIC", R.drawable.icon_activity);
		map.put("TITLE", "其他信息");
		map.put("INDEX", "5");
		mList.add(map);

		return mList;
	}
}
