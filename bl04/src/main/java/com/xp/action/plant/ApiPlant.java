package com.xp.action.plant;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.xp.config.ResConfig;
import com.xp.interceptor.GlobalExceptionInterceptor;
import com.xp.util.XPFunctions;
import com.xp.util.db.M;
import com.xp.util.db.XpMap;

@Before({ 
	GlobalExceptionInterceptor.class
})
public class ApiPlant extends Controller {

	public void reciveSignalInfo(){
		String postStr = getPara("JsonString","[]");
		String collectorCode = getPara("collectorCode","");
		JSONArray dataArr = new JSONArray(postStr);
		int dataLength = dataArr.length();
		if(dataLength == 0){
			renderText("1");
			return ;
		}
		
		M dao = new M(ResConfig.CONF_MES);
		dao.autoCommit(false);
		for(int i = 0; i < dataLength; i++){
			JSONObject data = (JSONObject) dataArr.get(i);
			String sign = data.getString("sign");
			XpMap row = new XpMap();
			row.put("CollectorCode", collectorCode);
			for(int portN = 1; portN <= 8; portN++){
				row.put("Port"+(9-portN), sign.substring(portN-1,portN));
			}
			row.put("CreateTime", XPFunctions.getCurTime("yyyy-MM-dd HH:mm:ss"));
			
			dao.table("signalinfo");
			dao.insert(row);
		}
		dao.commit();
		
		renderText("1");
	}
}
