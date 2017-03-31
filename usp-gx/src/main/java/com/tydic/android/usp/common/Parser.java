package com.tydic.android.usp.common;

import java.io.InputStream;

/**
 * 解析数据类
 * @function : 
 * @author   :chenyong
 * @company  :vancl
 * @date     :2011-11-2
 */
public interface Parser{
	public boolean parser(String string);
	public boolean parser(InputStream is);
}
