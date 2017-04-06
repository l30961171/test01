package com.xp.util.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeUtility;

import com.xp.util.XPFunctions;
import com.xp.util.db.M;
import com.xp.util.db.XpMap;

public class SentEmailTool {
	public static void main(String[] s){
//		XpMap mailServer = new XpMap();
//		mailServer.put("email_server_host", "smtp.qq.com");
//		mailServer.put("email_server_port", "25");
//		mailServer.put("email_user_num", "405285889@qq.com");
//		mailServer.put("email_user_password", "Daxiang2QQ");
//		mailServer.put("email_foot", "--------<br/>该邮件来自京泉华BPM系统，请勿回复！<br/>");
		
		M dao = new M();
		dao.autoCommit(false);
		List<XpMap> to = new ArrayList<>();
		String sign_station_str = "100001, 100007";
		if(!"".equals(sign_station_str)){
			String[] sign_station_arr = sign_station_str.split(",");
			String where = "";
			for(String sign_station : sign_station_arr){
				where += "a.station_id_="+sign_station.trim() +" OR ";
			}
			where = where.substring(0, where.length()-4); //去掉最后一个 OR
			dao.table("xp_station a");
			dao.fields("a.station_name_,b.user_name,b.user_email");
			dao.leftJoin("xp_user b ON b.user_id=a.user_id");
			dao.where(where);
			to = dao.select();
		}
		
		XpMap contentInfo = new XpMap();
		contentInfo.put("form_inst_id_", "999");
		contentInfo.put("form_name_", "表单名");
		contentInfo.put("inst_title_", "xxx");
		
		
		dao.table("xp_sys_parm");
		dao.where("group_=?", "system");
		List<XpMap> prams = dao.select();
		XpMap pram = XPFunctions.convertListToMap(prams, "key_", "value_");
		
		dao.closeDB();
		SentEmailTool.sendEmail(pram, to, contentInfo);
	}
	
	public static void sendEmail(XpMap mailServer,
			List<XpMap> to,XpMap contentInfo) {
		try {
			MailSenderInfo mailInfo = new MailSenderInfo();
			// 设置邮件服务器类型
			mailInfo.setMailServerHost((String)mailServer.get("email_server_host"));
			//设置端口号
			mailInfo.setMailServerPort((String)mailServer.get("email_server_port"));
			//设置是否验证
			mailInfo.setValidate(true);
			//设置用户名、密码、发送人地址
			mailInfo.setUserName((String)mailServer.get("email_user_num"));
			mailInfo.setPassword((String)mailServer.get("email_user_password"));// 您的邮箱密码
			String formAddress = MimeUtility.encodeText("京泉华BPM系统邮件")
					+"<"+mailServer.get("email_user_num")+">";
			mailInfo.setFromAddress(formAddress);
			//收件人邮箱
			int toSize = to.size();
			String[] mailToAddress = new String[toSize];
			for(int i = 0; i< toSize;i++){
				XpMap row = to.get(i);
				mailToAddress[i] = MimeUtility.encodeText(
						row.get("user_name")+"("+row.get("station_name_")+")")
						+ "<" + row.get("user_email") + ">";
			}
			mailInfo.setToAddress(mailToAddress);
			
			String content = "京泉华BPM:";
			content += contentInfo.get("form_name_").toString();
			content += "("+contentInfo.get("form_inst_id_")+")";
			mailInfo.setSubject(content+contentInfo.get("inst_title_").toString());
			
			content += mailServer.get("email_foot");
			mailInfo.setContent(content);
			
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			if(sms.sendHtmlMail(mailInfo)){
				System.out.println("mail send True!");
			}
			else{
				System.out.println("mail send False!");
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
}
