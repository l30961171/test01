package com.xp.running;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.TimerTask;

import com.jfinal.kit.JsonKit;
import com.xp.util.XPNet;
import com.xp.util.XPProperties;
import com.xp.util.db.M;
import com.xp.util.db.XpMap;

/**
 一体机采集部分

 * @author Administrator
 *
 */
public class RunSendDataPCToPLANT01 extends TimerTask {
	private M dao = null;
	
	@Override
	public void run() {
		String result = "0";
		dao = new M();
		dao.autoCommit(false);
		//一体机信号采集数据同步到车间服务器
		List<XpMap> rows = listSignalInfo();
		result = sendData(JsonKit.toJson(rows),"sync.pc2plant.signalinfo");
		//同步后就删掉这些数据
		if("1".equals(result)){
			deleteData(rows);
		}
		dao.commit();
	}
	
	private String sendData(String postStr,String urlKey) {
		String url = XPProperties.get(urlKey);
		String param = "";
		try {
			param = "collectorCode="+XPProperties.get("iocard.cardNumber")
					+ "&plantId="+XPProperties.get("iocard.plantId")
					+ "&JsonString="+URLEncoder.encode(postStr,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String content = XPNet.getWebContent(url, param, "POST", null).trim();
		return content;
	}
	
	private void deleteData(List<XpMap> rows){
		for(XpMap row : rows){
			dao.doSqlExecute("delete from io_origin_rec where recId=?", row.get("recId"));
		}
	}
	
	private List<XpMap> listSignalInfo() {
		dao.table("io_origin_rec");
		dao.pageNum(1);
		dao.pageSize(255);
		return dao.select();
	}

//	public static void main(String[] s) {
//		RunSendDataToCenter01 inst = new RunSendDataToCenter01();
//		inst.run();
//	}
}
