package com.tydic.android.usp.uss;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.rt.BASE64Decoder;

public class BitmapUtil {

	/**
	 * @param 将图片内容解析成字节数组
	 * @param inStream
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;

	}

	/**
	 * 用BASE64Decoder对接收到的字符串解码成二进制数据
	 * 
	 * @param st
	 * @return
	 */
	public static byte[] decoderStringToByte(String st) {
		byte[] bt_array = null;
		try {
			bt_array = new BASE64Decoder().decodeBuffer(st);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bt_array;
	}

	/**
	 * 将字节数组转换为ImageView可调用的Bitmap对象
	 * 
	 * @param bytes
	 * @param opts
	 * @return
	 */
	public static Bitmap convertByteToBitmap(byte[] bytes, BitmapFactory.Options opts) {
		Bitmap bitmap = null;
		try {
			if (bytes != null) {
				if (opts != null) {
					bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
				} else {
					bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
				}
			}
			return bitmap;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * string转成bitmap
	 * 
	 * @param st
	 */
	public static Bitmap convertStringToBitmap(String st) {
		// OutputStream out;
		Bitmap bitmap = null;
		try {
			// out = new FileOutputStream("/sdcard/aa.jpg");
			// byte[] bitmapArray= Base64.decode(st, Base64.DEFAULT);
			// 在接收端用BASE64Decoder对接收到的字符串解码成二进制数据;再输出生成图片;
			// byte[] bitmapArray = new
			// sun.misc.BASE64Decoder().decodeBuffer(st);

			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bitmapArray = decoder.decodeBuffer(st);
			// byte[] bitmapArray= Base64.decode(st);

			bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
			// bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			return bitmap;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param 图片缩放
	 * @param bitmap
	 *            对象
	 * @param w
	 *            要缩放的宽度
	 * @param h
	 *            要缩放的高度
	 * @return newBmp 新 Bitmap对象
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		return newBmp;
	}

	/**
	 * 图片按固定宽度等比例缩放
	 * 
	 * @param bitmap
	 * @param w
	 * @return
	 */
	public static Bitmap zoomBitmapScaleWidth(Bitmap bitmap, int w) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) w / width);
		matrix.postScale(scaleWidth, scaleWidth);
		Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		return newBmp;
	}

	/**
	 * 把Bitmap转Byte
	 * 
	 * @Author HEH
	 * @EditTime 2010-07-19 上午11:45:56
	 */
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * 把字节数组保存为一个文件
	 * 
	 * @Author HEH
	 * @EditTime 2010-07-19 上午11:45:56
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

}
