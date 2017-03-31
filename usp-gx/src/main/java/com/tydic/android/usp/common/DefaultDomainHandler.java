/**
 * 
 */
package com.tydic.android.usp.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tydic.android.usp.util.ReflectUtil;

/**
 * @author liuyq
 *
 */
public class DefaultDomainHandler implements DomainHandler {
	
	private Object object;
	private Class clazz;

	
	
	public DefaultDomainHandler(Class<?> clazz) {
		this.clazz = clazz;
	}

	/* (non-Javadoc)
	 * @see com.tydic.android.usp.common.DomainHandler#createDataItem()
	 */
	@Override
	public void createDataItem() {
		try {
			this.object = clazz.newInstance();
		} catch (IllegalAccessException e) {
			Logger.e("xml parse", "创建业务对象失败");
		} catch (InstantiationException e) {
			Logger.e("xml parse", "创建业务对象失败");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tydic.android.usp.common.DomainHandler#resolver(java.lang.String, java.lang.String)
	 */
	@Override
	public void resolver(String tagName, String value) {
		try {
			Field field = clazz.getDeclaredField(tagName);
			Class<?> fieldType = field.getType();
			String fieldName = field.getName();
			String newfieldName= ReflectUtil.getMethodSetStr(fieldName);
			field.setAccessible(true);
			Method method = clazz.getDeclaredMethod(newfieldName,fieldType);
			if(fieldType.getSimpleName().equalsIgnoreCase(Long.class.getSimpleName())){
				method.invoke(object,Long.valueOf(value));
			}else if(fieldType.getSimpleName().equalsIgnoreCase(Integer.class.getSimpleName())){
				method.invoke(object,Integer.valueOf(value));
			}else{
				method.invoke(object,value);
			}
			
		} catch (SecurityException e) {
			Logger.e("xml parse error", e.getMessage());
		} catch (NoSuchFieldException e) {
			Logger.e("xml parse error", e.getMessage());
		} catch (IllegalArgumentException e) {
			Logger.e("xml parse error", e.getMessage());
		} catch (IllegalAccessException e) {
			Logger.e("xml parse error", e.getMessage());
		} catch (InvocationTargetException e) {
			Logger.e("xml parse error", e.getMessage());
		} catch (NoSuchMethodException e) {
			Logger.e("xml parse error", e.getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see com.tydic.android.usp.common.DomainHandler#getDataItem()
	 */
	@Override
	public <T> T getDataItem() {
		return (T)object;
	}

}
