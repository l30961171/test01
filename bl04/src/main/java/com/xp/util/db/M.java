package com.xp.util.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.xp.util.db.SqlElement.CURD;

public class M {
	private SqlElement sqlElement;
	private DBDriver driver;
	
	public M(){
		sqlElement = new SqlElement();
		driver = DBDriver.getInstance();
	}
	
	public M(String configName){
		sqlElement = new SqlElement();
		driver = DBDriver.getInstance(configName);
	}

	public void table(String table){
		sqlElement.setTable(table);
	}
	public void fields(String fields){
		sqlElement.setFields(fields);
	}
	public void where(String where,Object... whereMap){
		sqlElement.setWhere(where);
		sqlElement.setWhereMap(whereMap);
	}
	public String getWhereStr(){
		return sqlElement.getWhere();
	}
	public Object[] getWhereMap(){
		return sqlElement.getWhereMap();
	}
	public void concatWhereByAnd(XpMap whereMap){
		if (whereMap == null){
			return ;
		}
		Set<String> keySet = whereMap.keySet();
		if(keySet.size() == 0)
			return ;
		String whereStr = "";
		List<Object> values = new ArrayList<>();
		for(String key : keySet){
			whereStr += key + "=? AND ";
			values.add(whereMap.get(key));
		}
		whereStr = whereStr.substring(0, whereStr.length()-5); //去掉最后一个 AND
		where(whereStr, values.toArray());
	}
	public void join(String join){
		join(join,"");
	}
	public void leftJoin(String join){
		join(join,"LEFT");
	}
	public void rightJoin(String join){
		join(join,"RIGHT");
	}
	private void join(String join ,String lr){
		join = lr + " JOIN " + join;
		if(!"".equals(sqlElement.getJoin())){
			join = sqlElement.getJoin()+" "+join;
		}
		sqlElement.setJoin(join);
	}
	public void group(String group){
		sqlElement.setGroup(group);
	}
	public void order(String order){
		sqlElement.setOrder(order);
	}
	public void start(Object start){
		sqlElement.setStart(String.valueOf(start));
	}
	public void pageNum(Object pageNum){
		sqlElement.setPageNum(String.valueOf(pageNum));
	}
	public void pageSize(Object pageSize){
		sqlElement.setPageSize(String.valueOf(pageSize));
	}
	public void autoCommit(boolean isAutoCommit){
		driver.setAutoCommit(isAutoCommit);
	}
	public void commit(){
		driver.commit();
	}
	public void rollback(){
		driver.rollback();
	}
	public void closeDB(){
		driver.closeDB();
		driver = null;
		sqlElement = null;
	}
	
	public int insert(XpMap data){
		List<XpMap> dataList = new ArrayList<>();
		dataList.add(data);
		return insert(dataList);
	}
	public int insert(List<XpMap> dataList){
		sqlElement.setCurd(CURD.INSERT);
		sqlElement.setDataList(dataList);
		return (int)doSql();
	}
	public int update(XpMap data){
		sqlElement.setCurd(CURD.UPDATE);
		sqlElement.setDataMap(data);
		return (int)doSql();
	}
	public int delete(){
		sqlElement.setCurd(CURD.DELETE);
		return (int)doSql();
	}
	@SuppressWarnings("unchecked")
	public List<XpMap> select(){
		sqlElement.setCurd(CURD.SELECT);
		return (List<XpMap>) doSql();
	}
	public XpMap find(){
		start(0);
		pageSize(1);
		List<XpMap> datas = select();
		if(datas.size() == 0){
			return null;
		}
		return datas.get(0);
	}
	@SuppressWarnings("unchecked")
	public List<XpMap> doSqlSelect(String customSql,Object... whereMap){
		sqlElement.setCurd(CURD.SELECT);
		sqlElement.setCustomSql(customSql);
		sqlElement.setWhereMap(whereMap);
		return (List<XpMap>) doSql();
	}
	public XpMap doSqlFind(String customSql,Object... whereMap){
		return doSqlSelect(customSql,whereMap).get(0);
	}
	public int doSqlExecute(String customSql,Object... whereMap){
		sqlElement.setCurd(CURD.UPDATE);
		sqlElement.setCustomSql(customSql);
		sqlElement.setWhereMap(whereMap);
		return (int)doSql();
	}
	public int doSqlInsert(String customSql,Object... whereMap){
		sqlElement.setCurd(CURD.INSERT);
		sqlElement.setCustomSql(customSql);
		sqlElement.setWhereMap(whereMap);
		return (int)doSql();
	}
	
	/**
	 * 例如callFunction("call proc_001")
	 * @param customSql
	 * @param whereMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<List<XpMap>> callFunction(String customSql,Object... whereMap){
		sqlElement.setCurd(CURD.FUNCTION);
		sqlElement.setCustomSql(customSql);
		sqlElement.setWhereMap(whereMap);
		return (List<List<XpMap>>)doSql();
	}
	
	public String getLastSql(){
		return driver.getLastSql();
	}
	
	public SqlElement getSqlElement(){
		return sqlElement;
	}
	
	public void setSqlElement(SqlElement sqlElement){
		this.sqlElement = sqlElement;
	}
	
	/**
	 * 返回最先插入的主键值
	 * 如果插入多条数据，则返回的是最先插入的主键值
	 * 例：插入两条数据，产生主键21、22，则返回21
	 * @return
	 */
	public String getLastInsertID(){
		return driver.getLastInsertId();
	}
	
	private Object doSql(){
		Object result = driver.doSql(sqlElement);
		sqlElement = new SqlElement();
		return result;
	}
}
