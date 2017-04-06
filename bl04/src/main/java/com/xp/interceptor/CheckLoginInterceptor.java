package com.xp.interceptor;

import java.util.Base64;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.xp.model.WebUser;
import com.xp.util.XPFunctions;

/**
 * 登录验证
 */
public class CheckLoginInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
		//执行action前的动作↓
		//System.out.println("Before invoking " + inv.getActionKey());
		
		Controller controller = inv.getController();
		HttpSession session = controller.getSession();
		//没有登录信息并且没有通过Authorization认证
		if(session.getAttribute("User") == null 
				&& !loginByAuthorization(inv)){
			//返回错误401 - Unauthorized 访问被拒绝，客户试图未经授权访问受密码保护的 页面。
			controller.getResponse().setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			String requestMethod = controller.getRequest().getMethod().toUpperCase();
			if("POST".equals(requestMethod)){
				controller.renderError(401);
			}
			else{
				session.setAttribute("redirectUrl", inv.getActionKey());
				controller.redirect("/Login/index");
			}
			return ;
		}
		
		//执行action前的动作↑
		//执行下一个拦截器，如果后面没有拦截器，则执行指定的Action方法
		inv.invoke();
		
		//执行action后的动作↓
		//System.out.println("After invoking " + inv.getActionKey());
		//执行action后的动作↑
	}
	
	/**
	 * Authorization认证方式登录
	 * @param user
	 */
	private boolean loginByAuthorization(Invocation inv){
		String authorization = inv.getController().getRequest().getHeader("Authorization");
		if(StrKit.notBlank(authorization)){
			String base64 = authorization.split(" ")[1];
			base64 = new String(Base64.getDecoder().decode(base64));
			String[] loginInfo = base64.split(":");
			String userNum = loginInfo[0];
			String userPsw = loginInfo[1];
			userPsw = XPFunctions.saltMD5(userPsw);
			WebUser user = WebUser.dao.singleUserByUserNum(userNum);
			if(user != null && user.getUserPsw().equals(userPsw)){
				WebUser.initUserInfoIntoSession(user,inv.getController().getSession());
				return true;
			}
		}
		return false;
	}
}
