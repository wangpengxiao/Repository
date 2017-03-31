/**
 * 
 */
package com.tydic.android.usp.common;

/**
 * @author liuyq
 *
 */
public interface DomainHandler {
	
	public void createDataItem();
	
	/**
	 * 解析xml标签 到 javaBean
	 * @param tagName
	 * @param value
	 */
	public void resolver(String tagName,String value);
	
	/**
	 * 返回
	 * @param <T>
	 * @return
	 */
	public <T> T getDataItem();

}
