package com.tydic.android.usp.ui.bean;

import java.io.Serializable;

/**
 * <pre>
 * Description: 主界面选择项main_item_info的bean
 * Copyright: Copyright(C) 2014</p>
 * Company: Tydic
 * </pre>
 *
 * @author lichao
 * @version V1.0
 * @since 2014-10-3
 */
public class MainItemInfo implements Serializable {

	private static final long serialVersionUID = -527693517590407225L;

	// Fields

	private Integer itemId;// 编号

	private Integer itemContentBackground;// 背景图片

	private Integer itemIcon;// 图标

	private String itemTitle;// 标题名称

	// Constructors

	/** default constructor */
	public MainItemInfo() {
	}

	// Property accessors

	/**
	 * get编号
	 */
	public Integer getItemId() {
		return this.itemId;
	}

	/**
	 * set编号
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * get背景图片
	 */
	public Integer getItemContentBackground() {
		return this.itemContentBackground;
	}

	/**
	 * set背景图片
	 */
	public void setItemContentBackground(Integer itemContentBackground) {
		this.itemContentBackground = itemContentBackground;
	}

	/**
	 * get图标
	 */
	public Integer getItemIcon() {
		return this.itemIcon;
	}

	/**
	 * set图标
	 */
	public void setItemIcon(Integer itemIcon) {
		this.itemIcon = itemIcon;
	}

	/**
	 * get标题名称
	 */
	public String getItemTitle() {
		return this.itemTitle;
	}

	/**
	 * set标题名称
	 */
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

}
