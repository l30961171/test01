package com.xp.interceptor;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.xp.util.XPAuth;

/**
 * 权限验证
 */
public class CheckAuthInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
		//执行action前的动作↓
		Controller controller = inv.getController();
		if(!XPAuth.checkUrl(inv, inv.getActionKey())){
			//返回错误412 - Precondition Failed 请求头中指定的一些前提条件失败（HTTP 1.1新）。
			controller.getResponse().setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			controller.renderText("没有权限,请联系管理员");
			System.out.println("没有权限访问：" + inv.getActionKey());
			return ;
		}
		
		//执行action前的动作↑
		//执行下一个拦截器，如果后面没有拦截器，则执行指定的Action方法
		inv.invoke();
		
		//执行action后的动作↓
		//System.out.println("After invoking " + inv.getActionKey());
		//执行action后的动作↑
	}
}
