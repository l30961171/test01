package com.xp.action.center;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xp.interceptor.GlobalExceptionInterceptor;

/**
 * 车间服务器去云中心服务器的数据
 * @author JunBin
 *
 */
@Before({ 
	GlobalExceptionInterceptor.class
})
public class ApiCenter extends Controller {

	//获取工单
	public void listGDan(){
		String sql = "select * from workorderinfo where Enabled=0 order by Id desc limit 100";
		List<Record> rows = Db.find(sql); 
		renderJson(JsonKit.toJson(rows));
	}
	
	//更新工单
	public void altGDan(){
		String sql = "update workorderinfo set Status=?,ModifyUserId=? where Id=?";
		String Status = getPara("Status","");
		String ModifyUserId = getPara("ModifyUserId","0");
		String Id = getPara("Id","0");
		Db.update(sql,Status,ModifyUserId,Id);
		renderText("1");
	}
}
