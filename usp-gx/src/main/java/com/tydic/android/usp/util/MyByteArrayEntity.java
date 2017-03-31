package com.tydic.android.usp.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;

import org.apache.http.entity.ByteArrayEntity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.tydic.android.usp.ui.constant.Constants;

public class MyByteArrayEntity extends ByteArrayEntity{
	private Handler handler;
	private ExecutorService service;
	
	public MyByteArrayEntity(byte[] b) {
		super(b);
	}

	/**
	 * @param b 数据
	 * @param handler 发送进度
	 * @param service 判断是否中止了任务
	 */
	public MyByteArrayEntity(byte[] b,Handler handler,ExecutorService service) {
		super(b);
		this.service=service;
		this.handler=handler;
	}

	@Override
	public void writeTo(OutputStream outstream) throws IOException {
		ByteArrayInputStream ips=null;
		long dataSize=getContentLength();
		try {
//			Log.e("food", "writeTo");
			//super.writeTo(outstream);
			byte[] buffer = new byte[10*1024];
			ips = new ByteArrayInputStream(content);
			int len=0;
			int total=0;
			while((len=ips.read(buffer))!=-1 && (service == null || !service.isShutdown())){
				outstream.write(buffer, 0, len);
				total+=len;
				//发送进度
				if(handler != null){
					Message msg = handler.obtainMessage();
					msg.what=Constants.UPLOAD_FLAG;
					msg.arg1=(int)dataSize;//总数据大小 long转为了int，即不能超过2147483647，2g大小
					msg.arg2=total;
					handler.sendMessage(msg);
					Log.e("pic", ""+total);
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ips!=null)ips.close();
			if(outstream!=null)outstream.close();
		}
		
	}
	
	@Override
	public InputStream getContent() {
//		Log.e("food", "getContent");
		return super.getContent();
	}
	
	@Override
	public long getContentLength() {
		long len = super.getContentLength();
//		Log.e("food", "getContentLength="+len);
		return len;
	}
}
