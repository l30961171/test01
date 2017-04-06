package com.xp.util.db;

import java.util.List;

public class SqlElement {
	private String customSql = "";
	private String table = "";
	private String fields = "";
	private String join = "";
	private String where = "";
	private String group = "";
	private String order = "";
	private String start = "";
	private String pageNum = "";
	private String pageSize = "";
	private XpMap dataMap;
	private List<XpMap> dataList;
	private Object[] whereMap;
	public enum CURD {SELECT,INSERT,DELETE,UPDATE,FUNCTION};
	private CURD curd;
	
	public String getCustomSql() {
		return customSql;
	}
	public void setCustomSql(String customSql) {
		this.customSql = customSql;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getJoin() {
		return join;
	}
	public void setJoin(String join) {
		this.join = join;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public XpMap getDataMap() {
		return dataMap;
	}
	public void setDataMap(XpMap dataMap) {
		this.dataMap = dataMap;
	}
	public List<XpMap> getDataList() {
		return dataList;
	}
	public void setDataList(List<XpMap> dataList) {
		this.dataList = dataList;
	}
	public Object[] getWhereMap() {
		return whereMap;
	}
	public void setWhereMap(Object[] whereMap) {
		this.whereMap = whereMap;
	}
	public CURD getCurd() {
		return curd;
	}
	public void setCurd(CURD curd) {
		this.curd = curd;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
}
