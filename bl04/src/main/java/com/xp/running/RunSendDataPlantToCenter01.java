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
车间服务器上传数据到云中心
按车间上传
库存
看板
报表
送检

 * @author Administrator
 *
 */
public class RunSendDataPlantToCenter01 extends TimerTask {
	private M dao = null;
	
	@Override
	public void run() {
		String result = "0";
		dao = new M();
		dao.autoCommit(false);
		List<XpMap> rows = null;
		//库存
		rows = listKuCunInfo();
		result = sendData(JsonKit.toJson(rows),"sync.plant2center.kucun");
		//看板
		rows = listKanBanInfo();
		result = sendData(JsonKit.toJson(rows),"sync.plant2center.kanban");
		
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
	
	private List<XpMap> listKuCunInfo() {
		dao.table("io_origin_rec");
		dao.pageNum(1);
		dao.pageSize(255);
		return dao.select();
	}
	
	private List<XpMap> listKanBanInfo() {
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
