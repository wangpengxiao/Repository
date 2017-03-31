package com.tydic.android.usp.common;

import java.util.WeakHashMap;

import android.graphics.Bitmap;

/**
 * Caches downloaded images, saves bandwidth and user's
 * packets
 * 
 * @author Lukasz Wisniewski
 */
public class ImageCache extends WeakHashMap<String, Bitmap> {

	private static final long serialVersionUID = 1L;
	
	public boolean isCached(String url){
		return containsKey(url) && get(url) != null;
	}

}
