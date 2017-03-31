package com.tydic.android.usp.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;

import com.tydic.android.usp.Usp;
import com.tydic.android.usp.ui.constant.Constants;

/**图片上传
 * @author ibm
 *
 */
public class PictureUpload {
	//private static final String URL_UPLOAD="http://192.168.0.111:8080/life-foodsafety-web/fileupload/fileupload";
	//private static final String URL_UPLOAD="http://130.76.1.128:8080/mss_ws/upload";
	private static final String URL_UPLOAD="http://130.76.54.166:8080/mss_ws/upload";
	
	private static final String BOUNDARY = "7cd4a6d158c";// 分隔符
	private static final String MP_BOUNDARY = "--" + BOUNDARY;
	private static final String END_MP_BOUNDARY = "--" + BOUNDARY + "--";
	private static final String FILE_PARAMETERNAME = "parameterName";// 请求参数名
	private static final String FILE_NAME = "filename";// 文件名
	private static final String FILE_TYPE="image/png";//文件类型

	/**图片上传
	 * @param file 图片文件
	 * @param handler 发送进度，以及响应的数据
	 * @throws Exception
	 */
	public static void upload(File file,Handler handler,ExecutorService service) throws Exception {
		//上传的文件地址
		Log.i("pic", "photo path=" + file.getAbsolutePath());
		ByteArrayOutputStream bos = null;
		byte[] data = null;
		bos = new ByteArrayOutputStream();
		// 实例化httpclient
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		// 创建post请求
		HttpPost httppost = new HttpPost(URL_UPLOAD);
		httppost.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		// 构造请求参数
		HashMap<String, String> paramMaps = new HashMap<String, String>();
		paramMaps.put(FILE_PARAMETERNAME, "file");//
		paramMaps.put(FILE_NAME, file.getName());
		paramToUpload(bos, paramMaps, file);
		data = bos.toByteArray();

		// 请求实体
		ByteArrayEntity byteArrayEntity = new MyByteArrayEntity(data,handler,service);
		httppost.setEntity(byteArrayEntity);

		HttpResponse response = httpclient.execute(httppost);
		Log.i("pic","executing request " + httppost.getRequestLine());
		HttpEntity resEntity = response.getEntity();
		Log.i("pic", "http status = " + response.getStatusLine());
		
		Message msg = handler.obtainMessage();
		msg.what=Constants.UPLOAD_RESPONSE_FLAG;
		msg.arg1=response.getStatusLine().getStatusCode();//StatusCode
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){//响应成功
			
		}
		
		if (resEntity != null) {
			String responseStr = EntityUtils.toString(resEntity);
			Log.i("pic", responseStr);
			msg.obj=responseStr;
		}
		handler.sendMessage(msg);//发送结果
		
		if (resEntity != null) {
			resEntity.consumeContent();
		}
		
		httpclient.getConnectionManager().shutdown();
		
		
	}

	/**将发送的数据封读入字节流
	 * @param baos
	 * @param maps
	 * @param file
	 * @throws Exception
	 */
	private static void paramToUpload(OutputStream baos, Map maps, File file)
			throws Exception {
		StringBuilder temp = new StringBuilder(10);
		temp.setLength(0);
		temp.append(MP_BOUNDARY).append("\r\n");
		temp.append("content-disposition: form-data; name=\"")
				.append(maps.get(FILE_PARAMETERNAME)).append("\";filename=\"")
				.append(maps.get(FILE_NAME)).append("\"\r\n");
		temp.append("Content-Type: ").append(FILE_TYPE).append("\r\n\r\n");
		byte[] res = temp.toString().getBytes();
		FileInputStream input = null;
		try {
			baos.write(res);
			res=null;
			// -----------头信息写入完毕
			
			//-----文件拷贝
			input = new FileInputStream(file);
			byte[] buffer = new byte[1024 * 50];
			while (true) {
				int count = input.read(buffer);
				if (count == -1) {
					break;
				}
				baos.write(buffer, 0, count);
			}
			
			// -----------结尾 信息
			baos.write("\r\n".getBytes());
			baos.write(("\r\n" + END_MP_BOUNDARY).getBytes());
		} catch (IOException e) {
			throw new Exception(e);
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					throw new Exception(e);
				}
			}
			if (null != baos) {
				try {
					baos.close();
				} catch (IOException e) {
					throw new Exception(e);
				}
			}
		}
	}

	/**保存图片到sd卡的insight文件夹
	 * @param data
	 * @return 保存成功返回图片路径  失败返回""
	 */
	public static String savePic(byte[] data) {
		String photoPath="";
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			return photoPath;
		String fname = DateFormat.format("yyyyMMddhhmmss", new Date()).toString()+".jpg";  
        File picFolder = new File(Environment.getExternalStorageDirectory()+Usp.IMGFILE_DIR);
        if(!picFolder.exists())
        	picFolder.mkdirs();
        File picture = new File(picFolder.getAbsolutePath(),"/"+fname); 
        Log.i("pic", "fname="+fname+";photoPath="+photoPath);  
        FileOutputStream fos =null;
        try {  
            fos = new FileOutputStream(picture); // Get file output stream  
            fos.write(data); // Written to the file  
            photoPath=picture.getAbsolutePath();
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
        return photoPath;
	}
	
	/**保存图片到sd卡的insight文件夹
	 * @param bitmap
	 * @return 保存成功返回图片路径  失败返回""
	 */
	public static String savePic(Bitmap bmp) {
		String photoPath="";
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			return photoPath;
		String fname = DateFormat.format("yyyyMMddhhmmss", new Date()).toString()+".jpg";  
        File picFolder = new File(Environment.getExternalStorageDirectory()+Usp.IMGFILE_DIR);
        if(!picFolder.exists())
        	picFolder.mkdirs();
        File picture = new File(picFolder.getAbsolutePath(),"/"+fname); 
        Log.i("pic", "fname="+fname+";photoPath="+photoPath);  
        FileOutputStream fos =null;
        try {  
            fos = new FileOutputStream(picture); // Get file output stream  
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);// 把数据写入文件
            photoPath=picture.getAbsolutePath();
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	if(fos!=null)
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
        return photoPath;
	}
	
}
