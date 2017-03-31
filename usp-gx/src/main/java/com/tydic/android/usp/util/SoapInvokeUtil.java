/**
 * 
 */
package com.tydic.android.usp.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.tydic.android.usp.common.Logger;
import com.tydic.android.usp.exception.SoapInvokeException;

/**
 * @author liuyq
 * 
 */
public class SoapInvokeUtil {

	//TODO
	//private static final String endPoint = "http://130.76.54.166:8080/mss_ws/services/ZteService?wsdl";
	private static final String endPoint = "http://172.168.1.240:18081/mss_ws/services/ZteService?wsdl";
	//private static final String endPoint = "http://localhost:8080/axis2/services/ZteService?wsdl";
	private static String NAMESPACE = "http://service.wsApp.ws.mss.com";

	/**
	 * 
	 * @return
	 * @throws SoapInvokeException
	 */
	public static String getRequestData(String methodName,String postData) throws SoapInvokeException{
		Logger.d("soap request:",postData);
		// 实例化SoapObject对象,指定webService的命名空间以及调用方法的名称
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		// example方法中有一个String的参数，这里将“android client”传递到example中
		request.addProperty("message", postData);
		// 获得序列化的Envelope
		SoapSerializationEnvelope envelope;
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = request;
        envelope.dotNet = true;  
        envelope.setOutputSoapObject(request);
		// Android传输对象
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		
		//transport.debug = true;
		//调用WebService
		
		try {
			transport.call(null, envelope);
			if (envelope.getResponse() != null) {
				Logger.d("soap response",envelope.getResponse().toString());
				return envelope.getResponse().toString();
			}
		} catch (Exception e) {
			throw new SoapInvokeException("soap invoke error",e);
		}
		return null;
	}

	// public static Object remoteCall(String methodName, String postData)
	// throws AxisFault{
	// RPCServiceClient serviceClient = new RPCServiceClient();
	// Options options = serviceClient.getOptions();
	// EndpointReference targetEPR = new EndpointReference(endPoint);
	// options.setTo(targetEPR);
	// Object[] opAddEntryArgs = new Object[]{postData};
	// Class[] classes = new Class[]{String.class};
	// QName opAddEntry = new QName("http://service.wsApp.ws.mss.com",
	// methodName);
	// return serviceClient.invokeBlocking(opAddEntry,
	// opAddEntryArgs,classes)[0];
	// }
}
