package com.xp.util.mail;

//import java.io.File;
import java.io.UnsupportedEncodingException;
//import java.util.Vector;
import java.util.Date;
import java.util.Properties;
 



import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class SendMailDemo {
	public static void main(String[] args) {
		try {
			MailSenderInfo mailInfo = new MailSenderInfo();
			// 设置邮件服务器类型
			mailInfo.setMailServerHost("smtp.qq.com");
			//设置端口号
			mailInfo.setMailServerPort("25");
			//设置是否验证
			mailInfo.setValidate(true);
			//设置用户名、密码、发送人地址
			mailInfo.setUserName("405285889@qq.com");
			mailInfo.setPassword("Daxiang2QQ");// 您的邮箱密码
			mailInfo.setFromAddress("405285889@qq.com");
			/**
			 * 附件
			 */
//			Vector<File> file = new Vector<File>();
//			File f1 = new File("d:/1.xlsx");
//			File f2 = new File("d:/2.lrc");
//			file.add(f1) ;
//			file.add(f2) ;
//			mailInfo.setFile(file) ;
			/**
			 * 收件人邮箱
			 */
			String[] mailToAddress = {MimeUtility.encodeText("张龙qq")+"张三<30961171@qq.com>",
					MimeUtility.encodeText("赵虎qq")+"<l30961171@yeah.net>"} ;
			mailInfo.setToAddress(mailToAddress);
			mailInfo.setSubject("测试");
			mailInfo.setContent("注册地址 <a href=\"www.baidu.com\" target='_blank'>百度</a>");
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			// sms.sendTextMail(mailInfo);// 发送文体格式
			if(sms.sendHtmlMail(mailInfo)){
				System.out.println("mail send True!");
			}// 发送html格式
			else{
				System.out.println("mail send False!");
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	static Authenticator auth = new Authenticator() {
		 
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("405285889@qq.com", "Daxiang2QQ");
        }
 
    };
 
    public static void main2(String[] args) throws UnsupportedEncodingException {
 
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.from", "405285889@qq.com");
        Session session = Session.getInstance(props, auth);
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO, 
            		InternetAddress.parse( 
            				MimeUtility.encodeText("张益达qq") + "<30961171@qq.com>"+","+
            				MimeUtility.encodeText("张益达qq2") + "<l30961171@yeah.net>"
            		));
            msg.setSubject("JavaMail hello world example");
            msg.setSentDate(new Date());
            msg.setText("Hello, world!\n");
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
 
    }
}