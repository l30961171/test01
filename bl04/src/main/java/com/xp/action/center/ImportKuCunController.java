package com.xp.action.center;

import java.io.File;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.upload.UploadFile;
import com.xp.interceptor.CheckAuthInterceptor;
import com.xp.interceptor.CheckLoginInterceptor;
import com.xp.interceptor.GlobalExceptionInterceptor;
import com.xp.service.ImportKuCunService;
import com.xp.util.XPFunctions;

/**
 * 机台表维护
 * @author JunBin
 *
 */
@Before({
	GlobalExceptionInterceptor.class,
	CheckLoginInterceptor.class,
	CheckAuthInterceptor.class
	})
public class ImportKuCunController extends Controller {

	public void index() {
	}

	@Before(Tx.class)
	public void upload() throws Exception  {
		String uploadPath = XPFunctions.getCurTime("yyyy/MM/dd");
		UploadFile uploadFile = getFile("excelFile", uploadPath);
		String filePath = uploadFile.getUploadPath()
				+File.separator+uploadFile.getFileName();
			ImportKuCunService.service.execute(filePath);
		renderText("上传成功");
	}
}
