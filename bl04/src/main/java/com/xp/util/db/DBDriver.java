package com.xp.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xp.util.XPFunctions;

public class DBDriver {
	
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet rs;
	private boolean isAutoCommit = true;
	
	private DBInfo info;
	private String lastSql;
	private String lastInsertId;
	private String errInfo;
	private boolean isShowSql = false;
	
	
	public DBDriver(){
		info = DBInfo.getInstance();
		connect();
	}
	
	public static DBDriver getInstance(){
		return new DBDriver();
	}
	
	public DBDriver(String configName){
		info = DBInfo.getInstance(configName);
		connect();
	}
	
	public static DBDriver getInstance(String configName){
		return new DBDriver(configName);
	}
	
	private void connect(){
		try {
			// 加载驱动程序
			Class.forName(info.getDriver());
			conn = DriverManager.getConnection(info.getURL(), info.getUser(),
					info.getPassword());
			if("true".equals(info.getShowSql()) || "1".equals(info.getShowSql())){
				isShowSql = true;
			}
		} catch (Exception e) {
			System.out.println("链接数据库失败：");
			e.printStackTrace();
		} finally{
			
		}
	}
	
	public void setAutoCommit(boolean isAutoCommit){
		this.isAutoCommit = isAutoCommit;
	}
	
	public Object doSql(SqlElement sqlElement){
		Object result = null;
		String sql = "";
		List<XpMap> dataList = new ArrayList<XpMap>();
		List<String> metaDataMap = null;
		
		sql = getSql(sqlElement);
		setLastSql(sql,sqlElement);
		try {
			conn.setAutoCommit(isAutoCommit());
			
			switch (sqlElement.getCurd()) {
			case INSERT:{
				statement = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				break;
			}
			case FUNCTION:{
				statement = conn.prepareCall(sql);
				break;
			}
			default :{
				statement = conn.prepareStatement(sql);
				break;
			}
			}
			
			setParameters(sqlElement);
			
			switch (sqlElement.getCurd()) {
			case SELECT:{
				rs = statement.executeQuery();
				
		        while(rs.next()){
		            if(metaDataMap == null){
		                metaDataMap = getColNameList(rs);
		            }
		            dataList.add(setData(rs,metaDataMap));
		        }
		        result = dataList;
				break;
			}
			case UPDATE:{
				result = statement.executeUpdate();
				break;
			}
			case DELETE:{
				result = statement.executeUpdate();
				break;
			}
			case INSERT:{
				result = statement.executeUpdate();
				rs = statement.getGeneratedKeys();
				if ( rs.next() ) {
	               setLastInsertID(rs.getString(1)); //得到刚插入的主键值（单主键自增）
	            }
				break;
			}
			case FUNCTION:{
				statement.execute();
				rs = statement.getResultSet();
				if(rs == null){
					break;
				}
				List<List<XpMap>> results = new ArrayList<>();
				while(rs.next()){
		            if(metaDataMap == null){
		                metaDataMap = getColNameList(rs);
		            }
		            dataList.add(setData(rs,metaDataMap));
		        }
		        results.add(dataList);
				while (statement.getMoreResults()) {
					rs = statement.getResultSet();
					metaDataMap = null;
					dataList = new ArrayList<XpMap>();
					while(rs.next()){
			            if(metaDataMap == null){
			                metaDataMap = getColNameList(rs);
			            }
			            dataList.add(setData(rs,metaDataMap));
			        }
			        results.add(dataList);
				}
				result = results;
				break;
			}
			default :{
				break;
			}
			}
		} catch (Exception e) {
			System.out.println("执行sql出错："+getLastSql());
			if(isAutoCommit() == false){
				rollback();
			}
			e.printStackTrace();
		} finally{
			if(isAutoCommit() == true){
				closeDB();
			}
		}
		
		return result;
	}

	public String getLastSql(){
		return lastSql;
	}
	
	public String getLastInsertId(){
		return lastInsertId;
	}
	public String getErrorInfo(){
		return errInfo;
	}
	
	public void commit(){
		try {
			conn.commit();
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(){
		try {
			conn.rollback();
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDB(){
    	try {
			if(rs != null){
				rs.close();
			}
			if(statement != null){
				statement.close();
			}
			if(conn != null){
				conn.close();
			}
			info = null;
			rs = null;
			statement = null;
			conn = null;
    	} catch (SQLException e) {
    		System.out.println("关闭数据库连接失败");
			e.printStackTrace();
		}
    }
	
	private String getSql(SqlElement sqlElement){
		String sql = "";
		if(!sqlElement.getCustomSql().equals("")){
			return sqlElement.getCustomSql();
		}
		try {
			switch (info.getDialect()) {
			case MYSQL: {
				sql = BuildMysql.buildSql(sqlElement);
				break;
			}
			case SQLSERVER: {
				sql = BuildSQLServer.buildSql(sqlElement);
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql = sql.replace("__", ".");
		return sql;
	}
	
	private List<String> getColNameList(ResultSet rs){  
		List<String> colNameList = new ArrayList<>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			for (int column = 0; column < numberOfColumns; column++) {
				String columnName = metaData.getColumnLabel(column + 1);
				colNameList.add(columnName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colNameList;  
	}
	
	private XpMap setData(ResultSet rs,List<String> colNameList) throws Exception {  
		XpMap map = new XpMap();  
		int countColName = colNameList.size();
        for (int index = 0; index < countColName ; index++) {  
        	String colName = colNameList.get(index);
            Object object = rs.getObject(colName);  
            map.put(colName, object);
        }  
	    return map;  
	}
	
	private void setParameters(SqlElement sqlElement){
		try {
			int i = 1;
			
			List<XpMap> dataList = sqlElement.getDataList();
			if(dataList != null){
				int dataListSize = dataList.size();
				for(int j = 0; j < dataListSize; j++){
					XpMap data = dataList.get(j);
					if(data != null && data.size() > 0){
						for(String key : data.keySet()){
							statement.setObject(i++, data.get(key));
						}
					}
				}
			} else {
				XpMap data = sqlElement.getDataMap();
				if(data != null && data.size() > 0){
					for(String key : data.keySet()){
						statement.setObject(i++, data.get(key));
					}
				}
			}
			
			Object[] whereMap = sqlElement.getWhereMap();
			if(whereMap != null && whereMap.length > 0){
				for(Object key : whereMap){
					statement.setObject(i++, key);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setLastSql(String sql,SqlElement sqlElement){
		lastSql = "{sql:" + sql +"}";
		List<XpMap> dataList = sqlElement.getDataList();
		String dataStr = "";
		if(dataList != null){
			List<String> dataArr = new ArrayList<>();
			int dataListSize = dataList.size();
			for(int index = 0; index < dataListSize; index++){
				dataStr = "(" + XPFunctions.join(",", dataList.get(index)) + ")";
				dataArr.add(dataStr);
			}
			dataStr = XPFunctions.join(",", dataArr);
		} else {
			dataStr = XPFunctions.join(",", sqlElement.getDataMap());
		}
		
		
		String whereMapStr = XPFunctions.join(",",sqlElement.getWhereMap());
		if(!"".equals(dataStr)){
			lastSql += ",{dataMap:"+dataStr+"}";
		}
		lastSql += ",{whereMap:" + whereMapStr + "}";
		
		if(isShowSql){
			System.out.println(lastSql);
		}
	}
	
	private void setLastInsertID(String lastInsertId){
		this.lastInsertId = lastInsertId;
	}
	
	private boolean isAutoCommit(){
		return isAutoCommit;
	}
}
