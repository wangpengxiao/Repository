package com.tydic.android.usp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

import com.tydic.android.usp.Usp;

public class FileDownloader {
	public  int fileSize;
	public  File saveFile;
	public  int downLength;
	public  boolean isCanceled = false;
	public  boolean isFinished = false;
	
	public FileDownloader(){
		
	}
	/**
	 * 开始下载文件
	 * 
	 * @param listener
	 *            监听下载数量的变化,如果不需要了解实时下载的数量,可以设置为null
	 * @return 已下载文件大小
	 * @throws Exception
	 */
	public  void directDownload(String downloadUrl,DownloadProgressListener listener,File fileSaveDir,String defaultFileSuffix) throws Exception{
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
				URL url = new URL(downloadUrl);
			
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				http.setConnectTimeout(Usp.DOWNLOAD_APP_TIMEOUT_LENGTH * 1000);
				http.setRequestMethod("GET");
				http.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
				http.setRequestProperty("Accept-Language", "zh-CN");
				http.setRequestProperty("Referer", url.toString()); 
				http.setRequestProperty("Charset", "UTF-8");
				http.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
				http.setRequestProperty("Connection", "Keep-Alive");
				if(http.getResponseCode()==200) {
					fileSize = http.getContentLength();//根据响应获取文件大小

					if (fileSize <= 0) throw new RuntimeException("Unkown file size ");
							
					String filename = getFileName(downloadUrl,http,defaultFileSuffix);//获取文件名称
					saveFile = getUniqueFile(fileSaveDir, filename);//构建保存文件
					if(!saveFile.exists()){
						saveFile.createNewFile();
	                }
					inStream = http.getInputStream();
					byte[] buffer = new byte[1024];
					int len = -1;
					outStream = new FileOutputStream(saveFile);
					int lastDownloadSize = downLength;
					
					while ((len = inStream.read(buffer, 0, 1024)) != -1	&& !isFinished &&!isCanceled) {
						outStream.write(buffer, 0, len);
						downLength += len;
						/**
						 * 如果有新的下载进度才再次通知！！！
						 */
						if(listener!=null && downLength > lastDownloadSize) {
							listener.onDownloadSize(downLength,fileSize);//通知目前已经下载完成的数据长度
						}
						lastDownloadSize = downLength;
						if(isCanceled){
							Log.e("DownLoad Thread ","downloadThread is canceled");
							return;
						}
					}
					
					if(!isCanceled)isFinished = true;
				}else{
					throw new RuntimeException("server no response ");
				}
			} catch (Exception e) {
				e.printStackTrace();
				downLength = -1;
			}finally{
				if(inStream!=null){
					try{inStream.close();
						inStream  = null;
					}catch(Exception e){}
				}
				if(outStream!=null){
					try{outStream.close();
						outStream = null;
					}catch(Exception e){}
				}
			}
	}

	/**
	 * 获取文件名 如果无法获取文件名，则随机文件名的 后缀默认是.apk
	 */
	private static String getFileName(String downloadUrl,
			HttpURLConnection conn, String defaultFileSuffix) {
		String filename = downloadUrl
				.substring(downloadUrl.lastIndexOf('/') + 1);
		if (filename == null || "".equals(filename.trim())) {// 如果获取不到文件名称
			for (int i = 0;; i++) {
				String mine = conn.getHeaderField(i);
				if (mine == null)
					break;
				if ("content-disposition".equals(conn.getHeaderFieldKey(i)
						.toLowerCase())) {
					Matcher m = Pattern.compile(".*filename=(.*)").matcher(
							mine.toLowerCase());
					if (m.find()) {
						filename = m.group(1);
						return filename;
					}
				}
			}
			filename = UUID.randomUUID() + defaultFileSuffix;// 默认取一个文件名,
																// 后缀默认是apk
		}
		return filename;
	}

	/**
	 * 处理重名文件存在的问题
	 * 
	 * @param file
	 * @return
	 */
	public static File getUniqueFile(File saveDir, String fileName) {
		File file = new File(saveDir, fileName);
		File testFile = file;
		int num = 1;
		while (testFile.exists()) {
			testFile = getUniqueFile(file, num);
			num++;
		}
		file = testFile;
		return file;
	}

	private static File getUniqueFile(File file, int num) {

		String filePath = file.getAbsolutePath();
		String fileName = filePath.substring(0, filePath.lastIndexOf("."))
				+ "_" + num + filePath.substring(filePath.lastIndexOf("."));
		file = new File(fileName);
		return file;
	}
	
	public interface DownloadProgressListener {
		public void onDownloadSize(int size,int total);
	}
}
