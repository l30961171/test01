package com.xp.util.db;

import java.util.TreeMap;

import com.xp.util.XPProperties;


public class DBInfo {
	public static enum Dialects {MYSQL,SQLSERVER,ORACLE};
	private String jdbcUrl;
	private String user;
	private String password;
	private Dialects dialect;
	private String isShowSql;
	private static DBInfo instance;
	private static TreeMap<String, DBInfo> mapInstance = new TreeMap<>();
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Dialects getDialect() {
		return dialect;
	}
	public void setDialect(String jdbcUrl) {
		if(jdbcUrl.toUpperCase().indexOf("SQLSERVER") > 0){
			this.dialect = Dialects.SQLSERVER;
		}
		else if(jdbcUrl.toUpperCase().indexOf("ORACLE") > 0){
			this.dialect = Dialects.ORACLE;
		}
		else{
			this.dialect = Dialects.MYSQL;
		}
	}
	
	public String getURL(){
		return jdbcUrl;
	}
	
	public String getDriver(){
		switch(dialect){
		case SQLSERVER:
			return  "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		case ORACLE:
			return  "oracle.jdbc.driver.OracleDriver";
		default:
			return  "com.mysql.jdbc.Driver";
		}
	}
	
	public static DBInfo getInstance(){
		if(instance == null){
			instance = new DBInfo();
		}
		instance.setJdbcUrl(XPProperties.get("jdbc.url"));
		instance.setUser(XPProperties.get("jdbc.username"));
		instance.setPassword(XPProperties.get("jdbc.password"));
		instance.setShowSql(XPProperties.get("show_sql"));
		instance.setDialect(instance.getJdbcUrl());
		return instance;
	}
	
	public static DBInfo getInstance(String configName){
		if(mapInstance.get(configName) == null){
			DBInfo mapDBInfo = new DBInfo();
			mapDBInfo.setJdbcUrl(XPProperties.get("jdbc.url",configName));
			mapDBInfo.setUser(XPProperties.get("jdbc.username",configName));
			mapDBInfo.setPassword(XPProperties.get("jdbc.password",configName));
			mapDBInfo.setShowSql(XPProperties.get("show_sql",configName));
			mapDBInfo.setDialect(mapDBInfo.getJdbcUrl());
			mapInstance.put(configName, mapDBInfo);
		}
		return mapInstance.get(configName);
	}
	public String getShowSql() {
		return isShowSql;
	}
	public void setShowSql(String isShowSql) {
		this.isShowSql = isShowSql;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
}
