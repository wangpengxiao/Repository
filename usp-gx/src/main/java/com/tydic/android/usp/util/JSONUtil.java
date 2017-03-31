package com.tydic.android.usp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {
	private static GsonBuilder builder = new GsonBuilder();
	private static Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();

    public static Gson getGson(){
    	return gson;
    }
}
