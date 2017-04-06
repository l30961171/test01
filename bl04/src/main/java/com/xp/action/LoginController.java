package com.xp.action;


import com.xp.model.WebUser;
import com.jfinal.core.Controller;
import com.xp.model.WebAccessLog;
import com.xp.util.XPFunctions;
import com.xp.util.XPNet;

public class LoginController extends Controller {
	public void index(){
		String userAgent = getRequest().getHeader("user-agent").toLowerCase();
		//MicroMessenger
		if(userAgent.contains("mobile")){
			render("loginM.jsp");
		}else{
			render("login.jsp");
		}
	}
	
	public void loginM(){
		render("loginM.jsp");
	}
	
	public void validateCode(){
		renderCaptcha();
	}
	
	/**
	 * 提交登陆
	 */
	public void loginPost(){
//        boolean validate = validateCaptcha("validateCode");
//        
//		if(!validate){
//			renderText("FAIL");
//			return ;
//		}
		
		String userNum = getPara("userNum");
		String userPsw = getPara("userPsw");
		userPsw = XPFunctions.saltMD5(userPsw);
		
		WebUser user = WebUser.dao.singleUserByUserNum(userNum);
		if(user != null && user.getUserPsw().equals(userPsw)){
			WebUser.initUserInfoIntoSession(user,getSession());
			String clientIp = XPNet.getClientIp(getRequest());
			WebAccessLog.dao.addLog(clientIp, user.getInt("userId"));
			renderText("SUCCESS");
		}
		else{
			renderText("FAIL");
		}
	}
	
	/**
	 * 提交登陆-从微信端登录,免验证码验证
	 */
	public void loginPostFromWeChat(){
//        boolean validate = validateCaptcha("validateCode");
//        
//		if(!validate){
//			renderText("FAIL");
//			return ;
//		}
		
		String userNum = getPara("userNum");
		String userPsw = getPara("userPsw");
		userPsw = XPFunctions.saltMD5(userPsw);
		
		WebUser user = WebUser.dao.singleUserByUserNum(userNum);
		if(user != null && user.getUserPsw().equals(userPsw)){
			WebUser.initUserInfoIntoSession(user,getSession());
			String clientIp = XPNet.getClientIp(getRequest());
			WebAccessLog.dao.addLog(clientIp, user.getInt("userId"));
			renderText("SUCCESS");
		}
		else{
			renderText("FAIL");
		}
	}
	
	/**
	 * 退出登陆
	 */
	public void logout(){
		getSession().invalidate();
		index();
	}
}
