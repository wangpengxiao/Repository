package com.tydic.android.usp.common;

import java.util.ArrayList;


public class AsyncTaskManager{
	
//	private static final int MAXTHREAD_NUM = 15;
	public static final int INIT = 0;
	public static final int BUSY = 1;
	public static final int COMPLETE = 2;
	public static final int ERR = 3;
	
	public static final int EXIST = 0;
	public static final int SUCCEED = 1;
	
	private volatile static AsyncTaskManager tm;
	private ArrayList<Task> tl = new ArrayList<Task>();
	private AsyncTaskManager(){
		
	}
	
	public static AsyncTaskManager getThreadManager(){
		if(tm == null)
			tm = new AsyncTaskManager();
		return tm;
	}
	
	private static void init(){
		if(tm == null)
			tm = new AsyncTaskManager();
	}
	
	private static boolean isExist(String taskname){
		init();
		for(int i=0;i<tm.tl.size();i++){
			if(taskname.equals(tm.tl.get(i).taskname))
				return true;
		}
		return false;
	}
	
	public static int addTask(String taskname){
		int res;
		init();
		if(isExist(taskname))
			return EXIST;
		tm.tl.add(new Task(taskname));
		return SUCCEED;
	}
	
	public synchronized static int addTask(Task task){
		int res;
		init();
		if(isExist(task.taskname))
			return EXIST;
		tm.tl.add(task);
		return SUCCEED;
	}
	
	public synchronized static void removeTask(String taskname){
		if(taskname == null || tm == null) return;
		for(int i=0;i<tm.tl.size();i++){
			if(taskname.equals(tm.tl.get(i).taskname))
				tm.tl.remove(i);
		}
	}
	
	public static class Task{
		private String taskname;
		private int status;
		public Task(String taskname){
			this.taskname = taskname;
			this.status = INIT;
		}
	}
	
	
//	public static class AsyncResult { 
//		// url 
//		public String _url; 
//		// byte[] 
//		public byte[] _byteBuffer; 
//	} 

	public static interface IResultCallback { 
		public void resultCallback(Task result); 
	} 
	
	public static interface IProgressCallback{ 
		public void progressCallback(Task result) ; 
	} 

}
