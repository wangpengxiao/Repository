package com.tydic.android.usp.common;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.util.Log;

/**返回头
 * @author chenyong
 *
 */
public class ReturnHead{
	public String cid;
	public String userid;
	public String returncode;
	public String errormsg;
	
	
	/**从xml解析ReturnHead实体
	 * <MobileResponseHead>
 		<ReturnInfo cid="13kcdsdffdsag" userid="12345" code="1" errormsg=""/>
		</MobileResponseHead>
	 * @param headstr
	 * @return
	 */
	public static ReturnHead parserReturnHeadFromStr(String headstr,String err){
		ReturnHead rh = null;
		StringReader read = new StringReader(headstr);
		InputSource source = new InputSource(read);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document dom = builder.parse(source);
			Element root = dom.getDocumentElement();
			NodeList items = root.getElementsByTagName("ReturnInfo");
			Element personNode = (Element) items.item(0);
			if(personNode == null){
				err = "personNode == null";
			}
			rh = new ReturnHead();
			rh.cid = personNode.getAttribute("cid");
			rh.errormsg = personNode.getAttribute("errormsg");
			rh.returncode = personNode.getAttribute("code");
			rh.userid = personNode.getAttribute("userid");
			Log.d("ReturnHead", "cid = "+rh.cid+" errormsg = "+rh.errormsg+" return = "+rh.returncode+" userid = "+rh.userid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			err += headstr +"  err = " +e.toString();
			e.printStackTrace();
		} finally {
		}
		return rh;
	}
}
