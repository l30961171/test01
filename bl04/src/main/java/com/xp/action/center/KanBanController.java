package com.xp.action.center;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.xp.service.KanBanService;

/**
 * 看板
 * @author JunBin
 *
 */
public class KanBanController extends Controller {

	/**
	 * 所有机台看板
	 */
	public void kb01() {
		List<Record> rows = KanBanService.service.kb01();
		setAttr("rows",rows);
	}
	
	public void kb01Json(){
		List<Record> rows = KanBanService.service.kb01Json();
		renderJson(rows);
	}
	
	public void listKb01Rec(){
		
	}
	
	public void listKb01RecJson(){
		List<Record> rows = KanBanService.service
				.listKb01Rec(getPara("where",""),
				getPara("values",""));
		renderJson(rows);
	}
}
