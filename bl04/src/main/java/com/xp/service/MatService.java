package com.xp.service;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.xp.util.db.M;
import com.xp.util.db.XpMap;

public class MatService {
	public static MatService service = new MatService();

	public List<XpMap> listKuCun (String where,String values) {
		M dao = new M();
		//字段
		dao.fields("*");
		
		//表
		dao.table("a_mat_kucun");
		Object[] oArr = values.split("~@~");
		if(StrKit.notBlank(where)){
			dao.where(where, oArr);
		}
		return dao.select();
	}
}
