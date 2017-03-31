package com.tydic.android.usp.common;

import java.lang.ref.SoftReference;
import java.util.HashMap;


import android.graphics.Bitmap;
/**
 * 软引用的 图片缓存
 * @author zhangchangsheng
 *
 */
public class SoftImageCache {
	private HashMap<String,SoftReference<Bitmap>>  softCache;
	
	public SoftImageCache(){
		softCache = new HashMap<String, SoftReference<Bitmap>>();
	}
	
	
	/**
	 * 取缓存中的图片
	 * @param fileNameInDisk
	 * @return
	 */
	public Bitmap get(String fileNameInDisk){
		if(softCache.containsKey(fileNameInDisk)){
			 SoftReference<Bitmap> softReference = softCache.get(fileNameInDisk);  
			 Logger.d("cache","从cache中读取图片"+":"+fileNameInDisk);
			 Bitmap bitmap = softReference.get();
			 if(bitmap!=null){
				 return bitmap;
			 }
		}
		return null;
	}
	
	/**
	 * 加入到缓存中
	 * @param fileNameInDisk
	 * @param bitmap
	 */
	public void put(String fileNameInDisk,Bitmap bitmap){
		softCache.put(fileNameInDisk, new SoftReference<Bitmap>(bitmap));  
	}
	
	/**
	 * 
	 * @param fileNameInDisk
	 * @return
	 */
	public void remove(String fileNameInDisk){
		softCache.remove(fileNameInDisk);
	}
}
