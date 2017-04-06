package com.xp.util;

import java.util.TreeMap;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Invocation;

/**
 * 权限处理类，用户登录时，有记录如下SESSION
	session.setAttribute("User", user);
	session.setAttribute("userMenu", listUserMenu);
	session.setAttribute("userCanAccess", listUserCanAccess);
 * @author Administrator
 *
 */
public class XPAuth {
	
	/**
	 * 检查权限,根据用户和目标url,兔死狗烹，鸟尽弓藏，卸磨杀驴，过河拆桥
	 * 如果用户拥有的角色中有目标url，则返回true，否则返回false
	 * @param user 用户SESSION
	 * @param target 目标角色
	 */
	@SuppressWarnings("unchecked")
	public static boolean checkUrl(Invocation inv,String target){
		HttpSession session = inv.getController().getSession();
		TreeMap<String, String> rows = (TreeMap<String, String>) session.getAttribute("userCanAccess");
		if("Y".equals(rows.get(target))){
			return true;
		}
		return false;
	}
}
