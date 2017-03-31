/**
 * 
 */
package com.tydic.android.usp;

import java.io.File;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.tydic.android.usp.activity.UspLoginActivity;
import com.tydic.android.usp.common.Logger;
import com.tydic.android.usp.util.FileDownloader;
import com.tydic.android.usp.util.FileDownloader.DownloadProgressListener;

/**
 * @author liuyq
 *
 */
public class UpdateService extends Service {

	//文件存储
	private File updateDir = null;
	private File updateFile = null;
	private String downloadUrl = "http://172.168.3.43:8080/examples/android-client.apk";
	//下载状态
	private final static int DOWNLOAD_COMPLETE = 0;
	private final static int DOWNLOAD_FAIL = 1;
	//通知栏
	private NotificationManager updateNotificationManager = null;
	private Notification updateNotification = null;
	private final int UPDATE_NOTIFIY = R.string.app_name;
	//通知栏跳转Intent
	private Intent updateIntent = null;
	private PendingIntent updatePendingIntent = null;
	private Thread updateAppTask = null;

	private Handler updateHandler = new  Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	    	switch(msg.what){
            case DOWNLOAD_COMPLETE:
                //点击安装PendingIntent
                Uri uri = Uri.fromFile(updateFile);
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                updatePendingIntent = PendingIntent.getActivity(UpdateService.this, UPDATE_NOTIFIY, installIntent, 0);
                
                //updateNotification.defaults = Notification.DEFAULT_SOUND;//铃声提醒 
                updateNotification.setLatestEventInfo(UpdateService.this, Usp.APP_NAME, "下载完成,点击安装。", updatePendingIntent);
                updateNotificationManager.notify(UPDATE_NOTIFIY, updateNotification);
                //停止服务
                stopService(updateIntent);
            case DOWNLOAD_FAIL:
                //下载失败
                updateNotification.setLatestEventInfo(UpdateService.this, Usp.APP_NAME, "下载失败", updatePendingIntent);
                updateNotificationManager.notify(UPDATE_NOTIFIY, updateNotification);
                //停止服务
                stopService(updateIntent);
            default:
                stopService(updateIntent);
                updateNotificationManager.cancel(UPDATE_NOTIFIY);
        }
	    }
	};
	
	
	@Override
	public void onStart(Intent intent, int startId) {
	    //创建文件
	    if(android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState())){
	        updateDir = new File(Environment.getExternalStorageDirectory(),Usp.DOWNLOAD_DIR);
	    }else{
	    	updateDir = getDir("apk", Context.MODE_PRIVATE | Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
	    }
	    this.updateNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	    this.updateNotification = new Notification();
	    updateIntent = new Intent(this, UpdateService.class);

	    //设置下载过程中，点击通知栏，回到主界面
	    updatePendingIntent = PendingIntent.getActivity(this,0,new Intent(this, UspLoginActivity.class),0);
	    //设置通知栏显示内容
	    updateNotification.icon = R.drawable.icon;
	    updateNotification.tickerText = "开始下载";
	    updateNotification.setLatestEventInfo(this,Usp.APP_NAME,"0%",updatePendingIntent);
	    //发出通知
	    updateNotificationManager.notify(UPDATE_NOTIFIY,updateNotification);

	    //开启一个新的线程下载，如果使用Service同步下载，会导致ANR问题，Service本身也会阻塞
	    if(updateAppTask == null){
	    	updateAppTask = new Thread(new UpdateAppTask());//运行初始化程序任务
	    	updateAppTask.start();//这个是下载的重点，是下载的过程
	    }
	}
	
	
	public class UpdateAppTask implements Runnable {
		@Override
		public void run() {
	        try{
	            //增加权限<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE">;
	            if(!updateDir.exists()){
	                updateDir.mkdirs();
	            }
	            
	            //增加权限<uses-permission android:name="android.permission.INTERNET">;
	            FileDownloader fileDownloader = new FileDownloader();
	            DownloadProgressListener listener =  new FileDownloader.DownloadProgressListener() {
					@Override
					public void onDownloadSize(int size,int total) {
						updateNotification.setLatestEventInfo(UpdateService.this, "正在下载", (int)size*100/total+"%", updatePendingIntent);
                        updateNotificationManager.notify(UPDATE_NOTIFIY, updateNotification);
						Logger.i("download",""+size);
					}
					
				}; 
	            fileDownloader.directDownload(downloadUrl, listener, updateDir, ".apk");
	            updateFile = fileDownloader.saveFile;
                //下载成功
                updateHandler.sendEmptyMessage(DOWNLOAD_COMPLETE);
	        }catch(Exception ex){
	            ex.printStackTrace();
	            //下载失败
	            updateHandler.sendEmptyMessage(DOWNLOAD_FAIL);
	        }
		}
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
