package com.xp.util.db;

import java.util.ArrayList;
import java.util.List;

public class Demo {
	
	public static void main(String[] s){
		Demo.test2();
//		Demo.test3();
//		Demo.test5();
	}
	
	//不加条件 + 条件查询 + 字段 + 排序
	public static void test2(){
		M dao = new M();
		dao.table("web_user a");
		dao.leftJoin("web_user_role b ON b.userId = a.userId");
		dao.fields("b.*");
		dao.where("a.userId < ? OR userNum like ?",4,"%1%");
		dao.order("a.userId=12 desc,a.userId desc");
		dao.group("userNum");
		List<XpMap> rows = dao.select();
		System.out.println(dao.getLastSql());
		System.out.println(rows);
	}
	
	//自定义SQL语句 + 事务
	public void test3(){
		M dao = new M();
		List<XpMap> rows = dao.doSqlSelect("select * from xp_user where user_id < ?",3);
		System.out.println(dao.getLastSql());
		System.out.println(rows);
		
		dao.doSqlExecute("delete from xp_user where user_id>?",15);
		System.out.println(dao.getLastSql());
	}
	
	
	//Insert + Update 
	public void test4(){
		M dao = new M();
		List<XpMap> dataList = new ArrayList<>();
		XpMap data = new XpMap();
		data.put("user_num", "xxx1");
		data.put("user_name", "xxxname1");
		data.put("user_psw", "xxxpsw1");
		dataList.add(data);
		data = new XpMap();
		data.put("user_num", "xxx2");
		data.put("user_name", "xxxname2");
		data.put("user_psw", "xxxpsw2");
		dataList.add(data);
//		data.put("user_id", 3);
		dao.table("xp_user");
		dao.insert(dataList);
		System.out.println(dao.getLastSql());
		System.out.println(dao.getLastInsertID());
		
		data = new XpMap();
		dao.table("xp_user");
		data.put("user_psw", "kkk");
		dao.where("user_id > ?", 20);
		dao.delete();
		System.out.println(dao.getLastSql());
	}
	
	public void test5(){
		M dao = new M();
		dao.table("xp_node a");
		dao.leftJoin("xp_access b ON a.node_id=b.node_id AND b.role_id=?");
		dao.where("1", 2);
		List<XpMap> dataList = dao.select();
		for(XpMap data : dataList){
			System.out.println(data);
		}
		System.out.println(dao.getLastSql());
	}
}
