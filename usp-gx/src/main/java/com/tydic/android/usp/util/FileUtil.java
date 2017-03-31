package com.tydic.android.usp.util;

import java.io.File;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

import com.tydic.android.usp.Usp;

/**
 * 文件相关的 工具方法
 *
 */
public class FileUtil {
	
	private static final String TAG = "FileUtil";  
	
	
	/**
	 * 达到1MB的，将文件大小转换为以MB为单位的，小数点后四舍五入 保留两位。
	 * 否则，以KB为单位，小数点后四舍五入 保留两位。
	 * 
	 * 即使小数点后两位都是0，也保留两位。
	 * @param fileSize int 文件大小,单位是byte
	 * @return 形如　2.34MB 或者 234.21KB 的字符串,根据文件大小 转换单位 得到的。
	 */
	public static String sizeToKorM(int fileSize){
		float size_float = fileSize;
		float oneMB = 1024 * 1024.0F;
		String resultSize = null;
		//如果 小于等于1MB,则换算成KB
		if(size_float <= oneMB){
			Float sizeOfK = size_float/1024;
			float roundSizeOfK = (Math.round(sizeOfK * 100))/100.0F;
			resultSize = roundSizeOfK + "KB";
			
		}else{//如果大于1MB，则换算成单位是MB
			Float sizeOfM = size_float/oneMB;
			float roundSizeOfM = (Math.round(sizeOfM*100))/100.0F;
			resultSize = roundSizeOfM + "MB";
			
		}
		return resultSize;
	}
	
	  
    public static File getCacheFile(String imageUri){  
        File cacheFile = null;  
        try {  
            if (Environment.getExternalStorageState().equals(  
                    Environment.MEDIA_MOUNTED)) {  
                File sdCardDir = Environment.getExternalStorageDirectory();  
                String fileName = getFileName(imageUri);  
                File dir = new File(sdCardDir.getCanonicalPath()  
                        + Usp.IMGFILE_DIR);  
                if (!dir.exists()) {  
                    dir.mkdirs();  
                }  
                cacheFile = new File(dir, fileName);  
                Log.i(TAG, "exists:" + cacheFile.exists() + ",dir:" + dir + ",file:" + fileName);  
            }    
        } catch (IOException e) {  
            e.printStackTrace();  
            Log.e(TAG, "getCacheFileError:" + e.getMessage());  
        }  
          
        return cacheFile;  
    }  
      
    public static String getFileName(String path) {  
        int index = path.lastIndexOf("/");  
        return path.substring(index + 1);  
    }  
    
}
