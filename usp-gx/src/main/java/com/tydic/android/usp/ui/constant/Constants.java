package com.tydic.android.usp.ui.constant;

import java.util.ArrayList;
import java.util.List;


public class Constants {
		
	//soap | http 调用成功
	public static final String OPER_RESULT_SUCCESS = "0";
	
    public static final long FADEIN_DURATION = 500;
    public static final long FADEOUT_DURATION = 500;
    public static final long ROTATELEFT_DURATION = 500;
    public static final long ROTATERIGHT_DURATION = 500;
    
    //图片上传状态
    public static final int UPLOAD_FLAG = 1;
	public static final int UPLOAD_RESPONSE_FLAG = 2;
	//图片基本信息
	public static final String UPLOAD_TIME="uploadTime";
	public static final String UPLOAD_NAME = "uploadName";
	//图片列表key
	public static final String PICTURE_LIST = "picture_list";
	public static final String PICTURE_SELECTED_ID = "selectedId";
	//相册
	public static final String PHOTO_ALBUM_PATH = "photoAlbumPath";//相册路径
	public static final String ACTIVITY_CLASS = "activityClass";//相册调用者类名
	public static final String SELECTED_PHOTO_PATH_LIST = "selectedPhotoPathList";//选中的相片列表
	public static final String UPLOAD_PHOTO_PATH_LIST = "uploadPhotoPathList";//返回参数：上传的相片列表
	public static final String UPLOAD_PHOTO_DESC_LIST = "uploadPhotoDescList";//返回参数：上传的相片描述列表
	//拍照返回图片保存路径key
	public static final String CAMERA_ACTIVITY_PICPATH = "activity_camera_picPath";
	//启动照相机avtivity 请求码
	public static final int CAMERA_REQUEST_CODE = 1;
	//本地图片浏览 请求码
	public static final int LOCAL_REQUEST_CODE = 2;
	//启动本地路径
	public static final int SDCARD_REQUEST_CODE = 2;
	//搖一搖定位百度位置
	public static final String ADDRESS_KEY="address";
	public static final String BAIDU_ADDRESS_KEY="baidu_address";//定位的地址
	public static final String CONFIRM_ADDRESS_KEY="confirm_address";//选择的地址
	public static final String BAIDU_API_ACESS_KEY = "6597DCA75B2ED8D500ABB7D09C2F5397108D52B4";
	//spinner中对应的选项
	public static final int CMCC = 1;
	public static final int TELECOM = 2;
	public static final int OTHER = 3;
	public static final int RAILCOM = 4;
	public static final int MULTI_COOPERATION = 5;
	public static final int NEUTRAL = 6;

	//活动执行状态
	public static final String PLANING = "0";
	public static final String ACTIONING = "1";
	public static final String LACK_OF_COMPLETE_PROOF = "2";
	public static final String COMPLETE = "3";
	
	//viewpager gridview height
	//必须保证内容本身小于该值
	public static final int VIEWPAGER_GRIDITEM_HEIGHT = 80; //选择号码高度
	public static final int VIEWPAGER_GRIDITEM_HEIGHT1 = 110; //选择套餐高度

	public static final int SELECT_NUMBER_SHOW_NUM = 200;//查询号码显示个数
	public static final String SELECT_NUMBER_DEFAULT_TELE_TYPE = "2G";//查询号码默认的电信类型
	
}
