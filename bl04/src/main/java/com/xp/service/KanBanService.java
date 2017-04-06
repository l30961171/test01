package com.xp.service;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class KanBanService {
	public static KanBanService service = new KanBanService();

	public List<Record> kb01() {
		String sql = "select * from kb_dianshi_01";
		return Db.find(sql);
	}
	
	public List<Record> kb01Json(){
		String sql = "select *,"
				+ "ifnull(concat(format((zhuangXiangQty/biaoZhunChanNeng),2),'%'),0) daChenglv,"
				+ "ifnull(concat(format(((1-(yaJianQty-buLiangQty)/yaJianQty)*100),2),'%'),0) buLiangLv "
				+ "from kb_dianshi_01";
		return Db.find(sql);
	}
	
	public List<Record> listKb01Rec (String where,String values) {
		Object[] oArr = values.split("~@~");
		String sql = "select * from kb_dianshi_01_rec ";
		if(StrKit.notBlank(where)){
			sql += "where " + where;
			return Db.find(sql,oArr);
		}
		return Db.find(sql);
	}
}
