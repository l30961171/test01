package com.xp.util.db;

import java.util.ArrayList;
import java.util.List;

import com.xp.util.XPFunctions;
import com.xp.util.db.SqlElement.CURD;

public class BuildMysql {
	public static String buildSql(SqlElement sqlElement) throws Exception{
		String table = " "+sqlElement.getTable()+" ";
        String where = "";
        if(!"".equals(sqlElement.getWhere())){
        	where = " WHERE "+ sqlElement.getWhere()+" ";
    	}
        CURD curd = sqlElement.getCurd();
        String sql = "";
        switch(curd){
        case SELECT:{
        	String join = sqlElement.getJoin();
            String fields = "*";
            if(!"".equals(sqlElement.getFields())){
            	fields = sqlElement.getFields();
            }
            String group = "";
            if(!"".equals(sqlElement.getGroup())){
            	group = " GROUP BY "+sqlElement.getGroup();
            }
            String order = "";
            if(!"".equals(sqlElement.getOrder())){
            	order = " ORDER BY "+sqlElement.getOrder();
            }
            String limit = "";
            if("all".equals(sqlElement.getPageSize())){
            	limit = "";
            }
            else if(!"".equals(sqlElement.getPageSize()) && !"all".equals(sqlElement.getPageSize())){
            	if(!"".equals(sqlElement.getStart())){
            		limit = " LIMIT "+sqlElement.getStart()+","+sqlElement.getPageSize();
            	}
            	else if(!"".equals(sqlElement.getPageNum())){
            		int pageNum = Integer.parseInt(sqlElement.getPageNum());
            		int pageSize = Integer.parseInt(sqlElement.getPageSize());
            		int start = (pageNum - 1) * pageSize;
            		limit = " LIMIT " + start +","+pageSize;
            	} else {
            		limit = " LIMIT "+sqlElement.getPageSize();
            	}
            }
            else {
            	limit = " LIMIT 1000";
            }
            sql = "SELECT "+fields+" FROM "+table+join+where+group+order+limit;
            break;
        }
        case INSERT:{
        	List<XpMap> dataList = sqlElement.getDataList();
        	String colStr = "("+XPFunctions.join(",",dataList.get(0).keySet())+")";
        	
        	int dataListSize = dataList.size();
        	List<String> valueList = new ArrayList<>();
        	String valueStr = "";
        	for(int index = 0; index < dataListSize; index++){
        		XpMap data = dataList.get(index);
        		valueStr = "("+XPFunctions.join(",", "?",data.keySet().size())+")";
            	valueList.add(valueStr);
        	}
        	valueStr = XPFunctions.join(",",valueList);
        	
        	sql = "INSERT INTO "+table+" "+colStr+" VALUES "+valueStr;
        	
        	break;
        }
        case UPDATE:{
        	if(where == null || "".equals(where))
        		throw new Exception("DBG规定 UPDATE 操作where要有条件，要选中所有可以where 1");
        	XpMap data = sqlElement.getDataMap();
        	String setStr = XPFunctions.join("=?,",data.keySet())+"=? ";
        	String order = "";
            if(sqlElement.getOrder() != null){
            	order = sqlElement.getOrder();
            }
        	sql = "UPDATE "+table+" SET "+setStr+where+order;
        	break;
        }
        case DELETE:
        	if(where == null || "".equals(where))
        		throw new Exception("DBG规定 DELETE 操作where要有条件，要选中所有可以where 1");
        	sql = "DELETE FROM "+table+where;
        	break;
        default:
        	break;
        }
		return sql;
	}
}
