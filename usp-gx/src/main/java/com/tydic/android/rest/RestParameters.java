package com.tydic.android.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tydic.android.rest.api.FileItem;

/**
 * Rest API请求业务参数
 * 
 * @author lichao
 *
 */
public class RestParameters {

	private List<String> fields = new ArrayList<String>();// 返回数据字段

	private Map<String, String> params = new HashMap<String, String>();// 业务参数 

	private Map<String, FileItem> attachments = new HashMap<String, FileItem>();//附件

	/**
	 * 添加返回数据字段
	 * 
	 * @param value
	 */
	public void addFields(String... value) {
		if (value != null) {
			for (String v : value) {
				fields.add(v);
			}
		}
	}

	/**
	 * 添加业务参数
	 * 
	 * @param key
	 * @param value
	 */
	public void addParam(String key, String value) {
		params.put(key, value);
	}

	/**
	 * 获取已添加的业务参数
	 * 
	 * @param key
	 * @return
	 */
	public String getParam(String key) {
		return params.get(key);
	}

	/**
	 * 删除已添加的业务参数
	 * 
	 * @param key
	 */
	public void removeParam(String key) {
		params.remove(key);
	}

	/**
	 * 添加附件
	 * 
	 * @param key
	 * @param file
	 */
	public void addAttachment(String key, FileItem file) {
		if (file == null) {
			return;
		}
		attachments.put(key, file);
	}

	/**
	 * 获取已添加的附件
	 * 
	 * @param key
	 * @return
	 */
	public FileItem getAttachment(String key) {
		return attachments.get(key);
	}

	/**
	 * 删除已添加的附件
	 * 
	 * @param key
	 */
	public void removeAttachment(String key) {
		attachments.remove(key);
	}
	
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public Map<String, FileItem> getAttachments() {
		return attachments;
	}
	public void setAttachments(Map<String, FileItem> attachment) {
		this.attachments = attachment;
	}
}
