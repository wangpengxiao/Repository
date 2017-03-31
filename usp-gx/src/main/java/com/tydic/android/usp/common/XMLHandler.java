package com.tydic.android.usp.common;

import org.xml.sax.helpers.DefaultHandler;



/**
 * 设置对应XML结束标记
 * @function : 
 * @author   :chenyong
 * @company  :vancl
 * @date     :2011-11-3
 */
public abstract class XMLHandler extends DefaultHandler{
	protected String flagWord = "";
	public String getFlagWord(){
		setFlagWord();
		return flagWord;
	}
	
	
	public abstract void setFlagWord();
	
	
	public interface XMLTag<T extends Message>{
		//单独一条消息的 tag
		public String getItemTag();
		//文档元素标签
		public String getDocTag();
		public T newInstance();
	}
	
}
