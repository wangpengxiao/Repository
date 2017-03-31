package com.tydic.android.usp.common;

import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class Xmlparser {

	public static boolean parser(String string, DefaultHandler handler) {
		StringReader read = new StringReader(string);
		SAXParserFactory spfactory = SAXParserFactory.newInstance();
		try {
			SAXParser sparser = spfactory.newSAXParser();
			XMLReader xreader = sparser.getXMLReader();
			xreader.setContentHandler(handler);
			InputSource source = new InputSource(read);
			source.setEncoding("utf-8");
			xreader.parse(source);
			return true;
		} catch (Exception e) {
			Log.d("parser", e.toString());
			e.printStackTrace();
			return false;
		}
	}


	public static boolean parser(InputStream is, DefaultHandler handler) {
		// TODO Auto-generated method stub
		if (is != null) {
			SAXParserFactory spfactory = SAXParserFactory.newInstance();
			try {
				SAXParser sparser = spfactory.newSAXParser();
				XMLReader xreader = sparser.getXMLReader();
				xreader.setContentHandler(handler);
				InputSource isSource = new InputSource(is);
				isSource.setEncoding("utf-8");
				xreader.parse(isSource);
				return true;
			} catch (Exception e) {
				Log.d("parser", e.toString());
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static boolean parser(DefaultHandler dh, String xmlfile,
			String fileOkTag) {
		if (xmlfile.contains(fileOkTag)) {
			StringReader read = new StringReader(xmlfile);
			SAXParserFactory spfactory = SAXParserFactory.newInstance();
			try {
				SAXParser sparser = spfactory.newSAXParser();
				XMLReader xreader = sparser.getXMLReader();
				xreader.setContentHandler(dh);
				InputSource source = new InputSource(read);
				source.setEncoding("utf-8");
				xreader.parse(source);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean parser(DefaultHandler dh, InputStream is) {
		if (is != null) {
			SAXParserFactory spfactory = SAXParserFactory.newInstance();
			try {
				SAXParser sparser = spfactory.newSAXParser();
				XMLReader xreader = sparser.getXMLReader();
				xreader.setContentHandler(dh);
				InputSource isSource = new InputSource(is);
				isSource.setEncoding("utf-8");
				xreader.parse(isSource);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
