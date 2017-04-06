package com.xp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class XPProperties {
	private static String ljb_properties = "/xp.config";
	private static Map<String,Properties> mapP = new HashMap<String, Properties>();
	
	private Properties p(String filename){
		try {
			InputStream in = this.getClass().getResourceAsStream(filename);
			Properties filenameP = new Properties();
			filenameP.load(in);
			mapP.put(filename, filenameP);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapP.get(filename);
	}
	
	public static String get(String property){
		return get(property,ljb_properties);
	}
	
	public static String get(String property,String filename){
		if(mapP.get(filename) != null){
			return mapP.get(filename).getProperty(property);
		}
		XPProperties ljbP = new XPProperties();
		return ljbP.p(filename).getProperty(property);
	}
	
	public static void setLoadFile(String filename){
		ljb_properties = filename;
	}
	
//	public static void main(String[] s){
//		System.out.println(LjbProperties.get("user"));
//		System.out.println(LjbProperties.get("port","/xljb.properties"));
//		LjbProperties.setLoadFile("/xljb.properties");
//		System.out.println(LjbProperties.get("password"));
//		System.out.println(LjbProperties.get("dialect"));
//		System.out.println(LjbProperties.get("ip"));
//		System.out.println(LjbProperties.get("database","/xljb.properties"));
//	}
}
