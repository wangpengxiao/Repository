package com.tydic.android.usp.activity.app.domain;

import java.io.Serializable;

/**
 * 渠道基本信息
 * 
 * @author yangqiong
 * 
 */
public class StaffChannelInfo implements Serializable {
	private Long staff_id;
	private String channel_id = "";// 渠道编码
	private String channel_name = "";// 渠道名称
	private String channel_kind_name = "";// 渠道类型
	private String mart_type = "";// 市场类型
	private String area_type = "";// 地域类型
	private String channel_boss_name;// 渠道总监
	private String picNum = "0";// 图片数量

	public StaffChannelInfo() {

	}

	public String getPicNum() {
		return picNum;
	}

	public void setPicNum(String picNum) {
		this.picNum = picNum;
	}

	public Long getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Long staff_id) {
		this.staff_id = staff_id;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getChannel_kind_name() {
		return channel_kind_name;
	}

	public void setChannel_kind_name(String channel_kind_name) {
		this.channel_kind_name = channel_kind_name;
	}

	public String getMart_type() {
		return mart_type;
	}

	public void setMart_type(String mart_type) {
		this.mart_type = mart_type;
	}

	public String getArea_type() {
		return area_type;
	}

	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}

	public String getChannel_boss_name() {
		return channel_boss_name;
	}

	public void setChannel_boss_name(String channel_boss_name) {
		this.channel_boss_name = channel_boss_name;
	}

}
