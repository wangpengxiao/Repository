package com.tydic.android.usp.ui.constant;

import com.tydic.android.usp.ui.bean.MainItemInfo;
import com.tydic.android.usp.ui.config.ItemConfig;

/**
 * 常量类
 * 
 * @author lichao
 * 
 */
public class Global {

	public static final String ON_BUILDING_INFO = "该功能尚未开通，敬请期待！";
	
	public static final MainItemInfo[] MAIN_ITEM_INFOS;

	static {

		MAIN_ITEM_INFOS = ItemConfig.getMainItems();

	}

}
