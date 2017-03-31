package com.tydic.android.usp.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池
 * @function : 
 * @author   :chenyong
 * @company  :vancl
 * @date     :2012-7-11
 */
public class ThreadPool{
	
	
	private static final int COREPOOLSIZE = 5;//核心线程数
	private static final int MAXIMUMPOOLSIZE = 15;//最大线程数
	private static final long KEEPALIVETIME = 1;//可空闲时间
	
	private volatile static ThreadPool threadpool;
	private ThreadPoolExecutor tpe;
	
	
	
	
	private ThreadPool(){
		if(tpe == null){
			tpe = new ThreadPoolExecutor(COREPOOLSIZE,
					MAXIMUMPOOLSIZE, KEEPALIVETIME, 
					TimeUnit.SECONDS,  new ArrayBlockingQueue<Runnable>(5), 
	                new ThreadPoolExecutor.DiscardOldestPolicy());
		}
	}
	
	public static ThreadPool getThreadPool(){
		if(threadpool == null){
			synchronized (ThreadPool.class) {
				if(threadpool == null){
					threadpool = new ThreadPool();
				}
			}
		}
		return threadpool;
	}
	
	public static void excute(Runnable command){
		if(threadpool == null) getThreadPool();
		threadpool.tpe.execute(command);
		Logger.d("excute", "activitythread count = "+threadpool.tpe.getActiveCount());
		Logger.d("excute", "thread count = "+threadpool.tpe.getPoolSize());
	}
	
	public static void shutdown(){
		if(threadpool != null)
		threadpool.tpe.shutdownNow();
	}
	
}
