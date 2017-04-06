package com.xp.running;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TimerTask;

import com.xp.util.XPNet;
import com.xp.util.XPProperties;

/**
车间服务器上传数据到云中心
按车间上传
工单

 * @author Administrator
 *
 */
public class RunGetDataPlantFromCenter01 extends TimerTask {
	
	@Override
	public void run() {
		String result = "0";
		//工单
		result = sendData("","sync.center2plant.gdan");
		
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
	

//	public static void main(String[] s) {
//		RunSendDataToCenter01 inst = new RunSendDataToCenter01();
//		inst.run();
//	}
}
