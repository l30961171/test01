package com.xp.util;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import com.xp.util.db.XpMap;


public class XPFunctions {
	// ////////////join()方法///////////////////
	public static String join(String separator, Set<String> array) {
		if (separator == null)
			separator = "";
		if (array == null || array.size() == 0)
			return "";
		String newStr = "";
		for (String s : array) {
			newStr += separator + s;
		}
		newStr = newStr.substring(separator.length(), newStr.length());
		return newStr;
	}
	
	public static String join(String separator, List<String> array) {
		if (separator == null)
			separator = "";
		if (array == null || array.size() == 0)
			return "";
		String newStr = "";
		for (String s : array) {
			newStr += separator + s;
		}
		newStr = newStr.substring(separator.length(), newStr.length());
		return newStr;
	}

	public static String join(String separator, String[] keys,
			Map<String, Object> map) {
		if (separator == null)
			separator = "";
		if (map == null || map.size() == 0)
			return "";
		String newStr = "";
		for (String key : keys) {
			newStr += separator + map.get(key);
		}
		newStr = newStr.substring(separator.length(), newStr.length());
		return newStr;
	}

	public static String join(String separator, String str, int account) {
		if (separator == null)
			separator = "";
		if (account == 0)
			return "";
		String newStr = "";
		for (int i = 0; i < account; i++) {
			newStr += separator + str;
		}
		newStr = newStr.substring(separator.length(), newStr.length());
		return newStr;
	}

	public static String join(String separator, TreeMap<String, Object> map) {
		if (separator == null)
			separator = "";
		if (map == null || map.size() == 0)
			return "";
		String newStr = "";
		Set<String> keys = map.keySet();
		for (String key : keys) {
			newStr += separator + map.get(key);
		}
		newStr = newStr.substring(separator.length(), newStr.length());
		return newStr;
	}

	public static String join(String separator, Object... array) {
		if (separator == null)
			separator = "";
		if (array == null || array.length == 0)
			return "";
		String newStr = "";
		for (Object s : array) {
			newStr += separator + s;
		}
		newStr = newStr.substring(separator.length(), newStr.length());
		return newStr;
	}
	
	public static String join(String separator, String... array) {
		if (separator == null)
			separator = "";
		if (array == null || array.length == 0)
			return "";
		String newStr = "";
		for (Object s : array) {
			newStr += separator + s;
		}
		newStr = newStr.substring(separator.length(), newStr.length());
		return newStr;
	}

	// ////////////以上是join()方法///////////////////

	// 用来遍历对象属性和属性值
	@SuppressWarnings("rawtypes")
	public static TreeMap<String, Object> object2Map(Class className, Object tb) {
		TreeMap<String, Object> map = null;
		try {
			map = new TreeMap<String, Object>();
			Field[] fields = className.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.get(tb) != null
						&& !"".equals(field.get(tb).toString())) {
					map.put(field.getName(), field.get(tb));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 密码加密
	 */
	public final static String saltMD5(String s) {
		s = "_" + s + "."; //加密盐
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * 获取当前时间<br>
	 * 常用格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurTime(String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * 生成随机数
	 */
	public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");   
        StringBuffer sb = new StringBuffer();   
        Random random = new Random();   
        int range = buffer.length();   
        for (int i = 0; i < length; i ++) {   
            sb.append(buffer.charAt(random.nextInt(range)));   
        }   
        return sb.toString();   
    }
	
	/**
	 * 将List<XpMap> 转成TreeMap<String,Object>
	 */
	public static XpMap convertListToMap(List<XpMap> list,
			String key_,String value_){
		XpMap map = new XpMap();
		for(XpMap row : list){
			map.put(row.getStr(key_), row.get(value_));
		}
		return map;
	}
	
	/**
	 * 补零
	 */
	public static String getZeros(String curStr,int targetLength ){
		int diff = targetLength - curStr.length();
		String zero = "";
		for(int i = 0; i < diff; i++){
			zero += "0";
		}
		return zero;
	}
	public static String getZeros(int curInt,int targetLength){
		String curStr = String.valueOf(curInt);
		return getZeros(curStr,targetLength);
	}
	
}
