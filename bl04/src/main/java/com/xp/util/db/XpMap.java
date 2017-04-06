package com.xp.util.db;

import java.util.TreeMap;

public class XpMap extends TreeMap<String, Object> {
	private static final long serialVersionUID = -1897478674688568853L;

	public String getStr(String key){
		return get(key).toString();
	}
	
	public String getStr(String key,String defaultValue){
		if(get(key) == null){
			return defaultValue;
		}
		return get(key).toString();
	}
	
	public int getInt(String key){
		return Integer.parseInt(getStr(key));
	}

	public int getInt(String key,int defaultValue){
		if(get(key) == null){
			return defaultValue;
		}
		return Integer.parseInt(getStr(key));
	}
	
	public long getLong(String key){
		return Long.parseLong(getStr(key));
	}
	
	public long getLong(String key,long defaultValue){
		if(get(key) == null){
			return defaultValue;
		}
		return Long.parseLong(getStr(key));
	}
	
	public double getDouble(String key){
		return Double.parseDouble(getStr(key));
	}
	
	public double getDouble(String key,double defaultValue){
		if(get(key) == null){
			return defaultValue;
		}
		return Double.parseDouble(getStr(key));
	}
	
	public float getFloat(String key){
		return Float.parseFloat(getStr(key));
	}
	
	public float getFloat(String key, float defaultValue){
		if(get(key) == null){
			return defaultValue;
		}
		return Float.parseFloat(getStr(key));
	}
	
	public java.sql.Date getDate(String key){
		return (java.sql.Date)get(key);
	}
	
	public java.sql.Timestamp getTimestamp(String key){
		return (java.sql.Timestamp)get(key);
	}
}
