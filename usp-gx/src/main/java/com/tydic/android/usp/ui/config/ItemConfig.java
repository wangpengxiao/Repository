package com.tydic.android.usp.ui.config;

import com.tydic.android.usp.R;
import com.tydic.android.usp.ui.bean.MainItemInfo;

/**
 * 配置项
 * 
 * @author lichao
 *
 */
public class ItemConfig {

	/**
	 * 获取主界面的配置信息
	 * 
	 * @return
	 */
	public static MainItemInfo[] getMainItems() {
		String[] itemTitles = new String[] { "统一开户", "客户查询", "终端导购", "常用工具 " };
		int[] icons = new int[] { R.drawable.usp_main_item_number_icon, R.drawable.usp_main_item_query_icon, R.drawable.usp_main_item_phone_icon, R.drawable.usp_main_item_tools_icon };
		int[] itemBackgrounds = new int[] { R.drawable.usp_main_item_number_selector, R.drawable.usp_main_item_query_selector, R.drawable.usp_main_item_phone_selector, R.drawable.usp_main_item_tools_selector };

		MainItemInfo[] itemInfo = new MainItemInfo[4];
		for (int i = 0; i < itemInfo.length; i++) {
			// New
			MainItemInfo mainItemInfo = new MainItemInfo();

			mainItemInfo.setItemId(i + 1);// 编号
			mainItemInfo.setItemContentBackground(itemBackgrounds[i]);// 背景图片

			mainItemInfo.setItemTitle(itemTitles[i]);// 标题名称
			mainItemInfo.setItemIcon(icons[i]);// 图标

			itemInfo[i] = mainItemInfo;
		}
		return itemInfo;
	}
}
