package com.xp.service;

import com.xp.util.XPProperties;
import com.xp.util.db.M;
import com.xp.util.db.XpMap;

public class RunIOCardService {
	public static RunIOCardService service = new RunIOCardService();
	
	public void saveData(String msg){
		String cardNumber = XPProperties.get("iocard.cardNumber");
		String plantId = XPProperties.get("iocard.plantId");
		//序号
		int seq = Integer.valueOf(msg.substring(4,6),16);
		M dao = new M();
		dao.autoCommit(false);
		String sql = "select count(*) c "
				+ "from io_origin_rec "
				+ "where recSeq=? "
				+ "and cardNumber=? "
				+ "and plantId=? "
				+ "order by createTime desc "
				+ "limit 0,255";
		dao.doSqlFind(sql, "",cardNumber,plantId);
		int msgLength = msg.length();
		XpMap data = null;
		for(int i = msgLength - 4; i > 9; i = i - 2){
			String sign = msg.substring(i, i + 2);
			String recSeq = cardNumber+String.format("%03d", seq);
			data = new XpMap();
			data.put("recSeq", recSeq);
			data.put("plantId", plantId);
			data.put("cardNumber", cardNumber);
			String sign01 = String.format("%04d", Integer.parseInt(Integer.toBinaryString(Integer.valueOf(sign.substring(0,1),16))));
			String sign02 = String.format("%04d", Integer.parseInt(Integer.toBinaryString(Integer.valueOf(sign.substring(1,2),16))));
			data.put("sign", sign01+sign02);
			data.put("originStr", "");
			if(!"00000000".equals(sign01+sign02)){
				dao.table("io_origin_rec");
				dao.insert(data);
			}
			seq--;
			if(seq < 0){
				seq = 255 + seq + 1;
			}
		}
		dao.commit();
	}
	
//	public static void main(String[] s){
//		for(int i = 0; i < 10; i++){
//			String str = "DA7A0"+i+"000701EE";
//			RunIOCardService.service.saveData(str);
//		}
//	}
}
