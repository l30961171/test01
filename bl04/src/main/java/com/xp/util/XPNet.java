package com.xp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.StrKit;

public class XPNet {
	/***
     * 获取客户端ip地址(可以穿透代理)
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
    	String[] HEADERS_TO_TRY = { 
    	        "X-Forwarded-For",
    	        "Proxy-Client-IP",
    	        "WL-Proxy-Client-IP",
    	        "HTTP_X_FORWARDED_FOR",
    	        "HTTP_X_FORWARDED",
    	        "HTTP_X_CLUSTER_CLIENT_IP",
    	        "HTTP_CLIENT_IP",
    	        "HTTP_FORWARDED_FOR",
    	        "HTTP_FORWARDED",
    	        "HTTP_VIA",
    	        "REMOTE_ADDR",
    	        "X-Real-IP"};
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
    
    /**
     * 获取网页内容
     * @param url 带http://的网站地址
     * @param param post参数id=1&name=admin
     * @param Authorization 账号密码，中间用冒号:隔开。 示例：wmsapi:wms123456
     * @return
     */
    public static String getWebContent(String url, String param, String method,String Authorization) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
            		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "UTF-8");
            if(StrKit.notBlank(Authorization)){
            	conn.setRequestProperty("Authorization", "Basic "
            				+Base64.getEncoder().encodeToString(Authorization.getBytes(StandardCharsets.UTF_8)));
            }
            if("POST".equals(method.toUpperCase())){
            	conn.setDoOutput(true);
            	conn.setDoInput(true);
            	out = new PrintWriter(conn.getOutputStream());
            	out.print(param);
            	out.flush();
            }
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
    
    public static String getWebContent(String url) {
    	return getWebContent(url,"","GET","");
    }
    
}
