package com.tydic.android.usp.common;


public class Flow{
	private static long num;
	private volatile static Flow flow;
	private Flow(){
		
	}
	
	
	public static Flow getFlow(){
		if(flow == null)
			flow = new Flow();
		return flow;
	}
	
	
	public static void addNewFlow(long newsize){
		if(newsize > 0)
			num += newsize;
		else {
			Logger.e("Flow C", "new size is wrong...");
		}
	}
	
	public static long getFlowSize(){
		return num;
	}
}
