package com.xp.action;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.xp.interceptor.CheckAuthInterceptor;
import com.xp.interceptor.CheckLoginInterceptor;
import com.xp.model.Notice;
import com.xp.model.NoticeType;

@Before({
	CheckLoginInterceptor.class
	,CheckAuthInterceptor.class
	})
public class NoticeController extends Controller {
	
	//公告类型
	
	public void listNoticeType(){
		List<NoticeType> rows = NoticeType.dao.listNoticeType();
		setAttr("rows", rows);
	}
	
	public void editNoticeType(){
		setAttr("row", NoticeType.dao.findById(getParaToInt("noticeTypeId")));
	}
	
	public void addNoticeType(){
		boolean result = getModel(NoticeType.class,"row").save();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void updateNoticeType(){
		boolean result = getModel(NoticeType.class,"row").update();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void delNoticeType(){
		boolean result = getModel(NoticeType.class,"row").delete();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	//公告列表
	
	public void listNotice(){
		
	}
	
	public void listNoticeJson(){
		Page<Notice> rows = Notice.dao.listNotice(
				getParaToInt("page",1), 
				getParaToInt("rows",10),
				getPara("where",""),
				getPara("values",""),
				null
				);
		renderJson(rows);
	}

	public void editNotice(){
		setAttr("types", NoticeType.dao.listNoticeType());
		setAttr("row", Notice.dao.findById(getParaToInt("noticeId")));
	}
	
	public void addNotice(){
		boolean result = getModel(Notice.class,"row").save();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void updateNotice(){
		boolean result = getModel(Notice.class,"row").update();
		renderText(result?"SUCCESS":"FAIL");
	}
	
	public void delNotice(){
		boolean result = getModel(Notice.class,"row").delete();
		renderText(result?"SUCCESS":"FAIL");
	}

}
