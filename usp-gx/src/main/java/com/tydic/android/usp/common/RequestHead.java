package com.tydic.android.usp.common;

import java.io.StringWriter;
import java.util.Locale;
import java.util.TimeZone;
import org.xmlpull.v1.XmlSerializer;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.tydic.android.usp.UspPreferences;
import com.tydic.android.usp.util.AppUtil;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

/**
 * 公共信息头。软件启动后进行初始化。调用toString(Type)来输出为JSON或者xml
 */
public class RequestHead{
	private volatile static RequestHead requestHead;
	
	public Context context;
	
	//---------序列化类型
	public static final String SERIALIZER_TYPE_XML="XML"; //序列化格式 xml
	public static final String SERIALIZER_TYPE_JSON="JSON";//序列化 json
	
	//--------@SerializedName注解为生成json串的键名,@Expose指暴露该属性用于序列化和反序列化
	@SerializedName("equipment")@Expose
	public Equipment equipment;
	@SerializedName("client")@Expose
	public Client client;
	@SerializedName("operation")@Expose
	public Operation operation;
	@SerializedName("other")@Expose
	public Other other;
	
	public class Equipment{
		@SerializedName("uid")@Expose
		public String uid;
		@SerializedName("muid")@Expose
		public String muid;
		@SerializedName("name")@Expose
		public String name;//设备名
		@SerializedName("version")@Expose
		public String versionEquipment;//设备版本
		@SerializedName("osName")@Expose
		public String osName;//系统名称  Android
		@SerializedName("osVersion")@Expose
		public String osVersion;//系统版本号
	}
	
	public class Client{
		@SerializedName("netType")@Expose
		public String netType;//网络类型
		@SerializedName("versionName")@Expose
		public String versionName;//应用版本名称    version
		@SerializedName("versionCode")@Expose
		public String versionCode;//版本号 versionId
		@SerializedName("channelId")@Expose
		public String channelId;//渠道号
		@SerializedName("cid")@Expose
		public String cid;//服务器返回的唯一标识
	}
	
	public class Operation{
		@SerializedName("userid")@Expose
		public String userId;//用户 id
		@SerializedName("pagecode")@Expose
		volatile public String pageCode;
		@SerializedName("dataType")@Expose
		public String dataType;//JSON
		@SerializedName("action")@Expose
		volatile public String actionCode;
	}
	
	public class Other{
		@SerializedName("timeZone")@Expose
		public String timezone;
		@SerializedName("country")@Expose
		public String country;
		@SerializedName("lan")@Expose
		public String lan;
	}
	
	private RequestHead(){
	}
    

    
	/**初始化数据
	 * @param context
	 */
	public static synchronized void init(Context context){
		Logger.d("RequestHead","init begin...");
		
		if(requestHead == null){
				requestHead = new RequestHead();
				requestHead.context = context;
				requestHead.equipment=requestHead.new Equipment();
				requestHead.client=requestHead.new Client();
				requestHead.operation=requestHead.new Operation();
				requestHead.other = requestHead.new Other();
		}else{
			return;
		}
    	
    	requestHead.equipment.uid = "Android";
    	requestHead.equipment.muid = AppUtil.getTelephonyManager(context).getSubscriberId();
    	if(requestHead.equipment.muid==null||requestHead.equipment.muid.equals("")){
    		requestHead.equipment.muid = UspPreferences.getUserUUID(context);
    	}
    	requestHead.equipment.name = android.os.Build.MODEL;
    	requestHead.equipment.versionEquipment = ""+AppUtil.getTelephonyManager(context).getDeviceSoftwareVersion();
    	requestHead.equipment.osName = android.os.Build.MODEL;
    	requestHead.equipment.osVersion=android.os.Build.VERSION.SDK;
    	
    	requestHead.client.netType=AppUtil.getNetWorkType(context);
    	requestHead.client.versionName=AppUtil.getVersionName(context);
    	requestHead.client.versionCode=AppUtil.getVersionCode(context)+"";
    	requestHead.client.channelId=AppUtil.getChanel(context); //设置渠道号暂时为空
    	requestHead.client.cid=AppUtil.getSpCid(context);
    	
    	requestHead.operation.dataType="JSON";//数据类型
    	//pageCode actionCode 在具体使用时赋值
    	
    	requestHead.other.timezone=TimeZone.getDefault().getDisplayName(); //取得时区信息
    	requestHead.other.country=AppUtil.getTelephonyManager(context).getNetworkCountryIso(); //取得运营商所在国家
    	requestHead.other.lan=Locale.getDefault().getLanguage(); //所使用语言
	}
	
	/**BaseActivity中进行init，保证得到的不为null
	 * @return
	 */
	public static RequestHead getRequestHead(){
		return requestHead;
	}
	
	/**
	 * 
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *	<MobileRequestHead>
	 *	 <Equipment uid="xxxxxx" muid="xxxxx"  name="Phone/iPad" version="2" osname="IOS/Android/Windows" osversion="5.30" />
	 *	 <Client nettype="3G" version="1.01" channelid="001" cid="13242343321"/>
	 *	 <Operation userid="12345" pagecode="1" datatype="POST/XML/JSON" action="1" /><!-- 退出程序action=0 -->
	 *	 <Other timezone="Beijing" country="China" lan="SimplifiedChineses"/>
	 *	</MobileRequestHead>
	 */
	/**生成xml
	 * @param rh
	 * @return
	 * @throws Exception
	 */
	private String createNetHeadXml(){
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try{
			serializer.setOutput(writer);
	
			// <?xml version=”1.0″ encoding=”UTF-8″ standalone=”yes”?>
			serializer.startDocument("UTF-8",true);
	
			// <MobileRequestHead>
			serializer.startTag("","MobileRequestHead");
			
			// <Equipment uid="xxxxxx" muid="xxxxx"  name="Phone/iPad" version="2" osname="IOS/Android/Windows" osversion="5.30">
			serializer.startTag("","Equipment");
			serializer.attribute("","uid",this.equipment.uid);
			serializer.attribute("","muid",this.equipment.muid);
			serializer.attribute("","name",this.equipment.name);
			serializer.attribute("","version",this.equipment.versionEquipment);
			serializer.attribute("","osname",this.equipment.osName);
			serializer.attribute("","osversion",this.equipment.osVersion);
			serializer.endTag("","Equipment");
			
			// <Client nettype="3G" version="1.01" channelid="001" cid="13242343321"/>
			serializer.startTag("","Client");
			serializer.attribute("","nettype",this.client.netType);
			serializer.attribute("","versionName",this.client.versionName);
			serializer.attribute("","versionCode",this.client.versionCode);
			serializer.attribute("","channelid",this.client.channelId);
			serializer.attribute("","cid",this.client.cid);
			serializer.endTag("","Client");
			
			// <Operation userid="12345" pagecode="1" datatype="POST/XML/JSON" action="1" />
			serializer.startTag("","Operation");
			serializer.attribute("","userid",this.operation.userId);
			serializer.attribute("","datatype",SERIALIZER_TYPE_XML);
			serializer.attribute("","pagecode",""+this.operation.pageCode);
			serializer.attribute("","action",""+this.operation.actionCode);
			
			serializer.endTag("","Operation");
			
			// <Other timezone="Beijing" country="China" lan="SimplifiedChineses"/>
			serializer.startTag("","Other");
			serializer.attribute("","timezone",this.other.timezone);
//			serializer.attribute("","timezone",new String("汉字测试".getBytes(),"utf-8"));
			serializer.attribute("","country",this.other.country);
			serializer.attribute("","lan",this.other.lan);
			serializer.endTag("","Other");
			
			// </MobileRequestHead>
			serializer.endTag("","MobileRequestHead");
			serializer.endDocument();
			Log.d("nethead", writer.toString());
			return writer.toString();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}	
	
	private String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
	public String toString(String TYPE) {
		if(SERIALIZER_TYPE_XML.equalsIgnoreCase(TYPE))
			return createNetHeadXml();
		if(SERIALIZER_TYPE_JSON.equalsIgnoreCase(TYPE))
			return toJson();
		return null;
	}



	@Override
	public String toString() {
		return "RequestHead [client=" + client + ", context=" + context + ", equipment=" + equipment + ", operation="
				+ operation + ", other=" + other + "]";
	}
	
}
