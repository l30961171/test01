<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.jfinal.kit.PropKit"%>

<%
String DEV_OR_PRD = "";
if(!"PRD".equals(PropKit.get("DEV_OR_PRD"))){
	DEV_OR_PRD = "(测试环境)";
}

//站点名
String webTitle = "泰瑞MES" + DEV_OR_PRD;
request.setAttribute("webTitle", webTitle);

//公司名
String companyName = "泰瑞MES";
request.setAttribute("companyName", companyName);

//页脚
String login_footer = "&copy; 2016 <a href='http://www.blmii.com/' target='_blank'>柏麟</a> POWER BY XP 2016" + DEV_OR_PRD;

request.setAttribute("login_footer", login_footer);
%>