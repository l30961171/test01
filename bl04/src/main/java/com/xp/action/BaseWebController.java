package com.xp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.xp.interceptor.CheckAuthInterceptor;
import com.xp.interceptor.CheckLoginInterceptor;
import com.xp.interceptor.GlobalExceptionInterceptor;
import com.xp.model.ViewListRoleMenu;
import com.xp.model.ViewListUser;
import com.xp.model.ViewListUserRole;
import com.xp.model.WebAccessLog;
import com.xp.model.WebAuthObj;
import com.xp.model.WebMenu;
import com.xp.model.WebRole;
import com.xp.model.WebRoleMenu;
import com.xp.model.WebSysParm;
import com.xp.model.WebUser;
import com.xp.model.WebUserRole;

@Before({
	GlobalExceptionInterceptor.class,
	CheckLoginInterceptor.class,
	CheckAuthInterceptor.class,
	Tx.class
	})
public class BaseWebController extends Controller {
	//**********************用户************************//
	
	public void listUser(){
		render("listUser.jsp");
	}
	
	public void listUserJson(){
		Page<ViewListUser> users = ViewListUser.dao.listUser(
				getParaToInt("page",1), 
				getParaToInt("rows",10),
				getPara("where",""),
				getPara("values","")
				);
		renderJson(users);
	}
	
	public void editUser(){
		WebUser row = WebUser.dao.findById(getParaToInt("userId"));
		List<WebRole> roles = WebRole.dao.listRole();
		List<ViewListUserRole> userRoles = ViewListUserRole.dao.listUserRole(getParaToInt("userId"));
		setAttr("roles", JsonKit.toJson(roles));
		setAttr("userRoles", JsonKit.toJson(userRoles));
		setAttr("row", row);
	}
	
	public void addUser(){
		boolean result = WebUser.dao
				.saveUser(getModel(WebUser.class,"user"));
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void updateUser(){
		boolean result = getModel(WebUser.class,"user")
				.update();
		renderText(result?"SUCCESS":"FAIL");
	}

	public void saveUserRole(){
		List<WebUserRole> rows = new ArrayList<>();
		int size = getParaToInt("size");
		int userId = getParaToInt("userId");
		for(int i = 0; i < size; i++){
			rows.add(getModel(WebUserRole.class,"userRole_"+i));
		}
		boolean result = WebUserRole.dao.saveUserRole(userId,rows);
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void resetPsw(){
		int userId = getParaToInt("userId");
		boolean result = WebUser.dao.resetPsw(userId);
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void delUser(){
		int userId = getParaToInt("userId");
		boolean result = WebUser.dao.delUser(userId);
		renderText(result?"SUCCESS":"FAIL");
	}
	
	//**********************角色*******************//
	
	public void listRole(){
		List<WebRole> roles = WebRole.dao.listRole();
		List<WebMenu> menus = WebMenu.dao.listMenu();
		setAttr("roles", JsonKit.toJson(roles));
		setAttr("menus", JsonKit.toJson(menus));
	}
	
	public void singleRoleJson(){
		int roleId = getParaToInt("roleId");
		TreeMap<String, Object> mapOut = new TreeMap<>();
		mapOut.put("role", WebRole.dao.findById(roleId));
		mapOut.put("roleMenus", ViewListRoleMenu.dao.listRoleMenu(roleId));
		mapOut.put("authObjs", WebAuthObj.dao.listAuthObj(roleId));
		renderText(JsonKit.toJson(mapOut));
	}
	
	public void updateRole(){
		WebRole role = getModel(WebRole.class,"role");
		boolean result = role.update();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void addRole(){
		WebRole role = getModel(WebRole.class,"role");
		role.set("roleCode",""); //继承时去掉父类的roleCode
		boolean result = role.save();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void delRole(){
		WebRole role = getModel(WebRole.class,"role");
		boolean result = role.delete();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	//******************菜单节点******************//
	
	public void listMenu(){
		List<WebMenu> rows = WebMenu.dao.listMenu();
		setAttr("rows", JsonKit.toJson(rows));
	}
	
	public void singleMenuJson(){
		WebMenu menu = WebMenu.dao.findById(getParaToInt("menuId"));
		renderJson(menu);
	}
	
	public void updateMenu(){
		boolean result = getModel(WebMenu.class,"row").update();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void addMenu(){
		boolean result = getModel(WebMenu.class,"row").save();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void delMenu(){
		boolean result = getModel(WebMenu.class,"row").delete();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	
	//*******************角色菜单******************//
	
	public void listRoleMenu(){
		List<ViewListRoleMenu> rows = ViewListRoleMenu.dao.listRoleMenu(getParaToInt("roleId"));
		renderJson(rows);
	}
	
	public void saveRoleMenu(){
		List<WebRoleMenu> rows = new ArrayList<>();
		int size = getParaToInt("size");
		int roleId = getParaToInt("roleId");
		for(int i = 0; i < size; i++){
			rows.add(getModel(WebRoleMenu.class,"menu_"+i));
		}
		boolean result = WebRoleMenu.dao.saveRoleMenu(roleId,rows);
		renderText(result?"SUCCESS":"FAIL");
	}
	
	//*******************权限对象********************//
	
	public void listAuthObj(){
		List<WebAuthObj> rows = WebAuthObj.dao.listAuthObj(getParaToInt("parmId"));
		renderJson(rows);
	}

	public void saveAuthObj(){
		List<WebAuthObj> rows = new ArrayList<>();
		int size = getParaToInt("size");
		int roleId = getParaToInt("roleId");
		for(int i = 0; i < size; i++){
			rows.add(getModel(WebAuthObj.class,"auth_"+i));
		}
		boolean result = WebAuthObj.dao.saveAuthObj(roleId,rows);
		renderText(result?"SUCCESS":"FAIL");
	}
	
	
	//*******************系统参数*******************//
	
	public void listSysParm(){
		List<WebSysParm> rows = WebSysParm.dao.listSysParm();
		setAttr("rows", rows);
	}
	
	public void editSysParm(){
		setAttr("row", WebSysParm.dao.findById(getParaToInt("parmId")));
	}
	
	public void updateSysParm(){
		boolean result = getModel(WebSysParm.class,"row").update();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void addSysParm(){
		boolean result = getModel(WebSysParm.class,"row").save();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void delSysParm(){
		boolean result = getModel(WebSysParm.class,"row").delete();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	//*******************登录记录**********************//
	public void loginLog(){
		
	}
	
	public void loginLogJson(){
		Page<WebAccessLog> logs = WebAccessLog.dao.logRecently(
				getParaToInt("page",1), 
				getParaToInt("rows",10),
				getPara("where",""),
				getPara("values","")
				);
		renderJson(logs);
	}
}
