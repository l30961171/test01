package com.xp.action;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.xp.interceptor.CheckLoginInterceptor;
import com.xp.model.Notice;
import com.xp.model.NoticeType;
import com.xp.model.WebAccessLog;
import com.xp.model.WebUser;

@Before(CheckLoginInterceptor.class)
public class IndexController extends Controller {
	public void index(){
		Object userMenu = getSession().getAttribute("userMenu");
		setAttr("userMenu", JsonKit.toJson(userMenu));
		render("index.jsp");
	}
	
	//欢迎页
	public void welcome(){
		List<NoticeType> types = NoticeType.dao.listNoticeType();
		List<Notice> notices = new ArrayList<>();
		
		for(NoticeType type : types){
			int typeId = type.getNoticeTypeId();
			List<Notice> noticeById = Notice.dao.listNoticeByTypeId(typeId);
			for(Notice notice : noticeById){
				notices.add(notice);
			}
		}
		
		setAttr("types", types);
		setAttr("notices", notices);
	}
	
	//用户信息
	public void userInfo(){
		WebUser user = (WebUser) getSession().getAttribute("User");
		setAttr("infoType", getPara("infoType"));
		setAttr("user", user);
	}
	
	public void updateUserPsw(){
		WebUser loginUser = (WebUser) getSession().getAttribute("User");
		WebUser userInfo = getModel(WebUser.class,"user");
		userInfo.setUserId(loginUser.getUserId());
		boolean result = userInfo.updateUserPsw(userInfo);
		loginUser = WebUser.dao.findById(loginUser.getUserId());
		getSession().setAttribute("User", loginUser);
		renderText(result?"SUCCESS":"FAIL");
	}
	
	
	//阅读公告
	public void viewNotice(){
		setAttr("row", Notice.dao.findById(getParaToInt("noticeId")));
	}
	
	public void moreNotice(){
		setAttr("type", NoticeType.dao.findById(getParaToInt("noticeTypeId")));
	}
	
	public void moreNoticeJson(){
		Page<Notice> rows = Notice.dao.listNotice(
				getParaToInt("page",1), 
				getParaToInt("rows",10),
				getPara("where",""),
				getPara("values",""),
				"a.lastUpdate desc"
				);
		renderJson(rows);
	}
	
	//登录记录
	public void loginLog(){
		WebUser user = (WebUser) getSession().getAttribute("User");
		int days = getParaToInt("days",30);
		List<WebAccessLog> rows = WebAccessLog.dao.logRecently(user.getUserId(),days);
		setAttr("rows", rows);
	}
}
