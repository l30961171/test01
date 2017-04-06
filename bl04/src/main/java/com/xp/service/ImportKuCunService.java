package com.xp.service;

import java.io.File;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import jxl.Sheet;
import jxl.Workbook;

public class ImportKuCunService {
	public static ImportKuCunService service = new ImportKuCunService();

	public void execute(String filePath) throws Exception  {
			//先清空数据
			String sql = "delete from a_mat_kucun";
			Db.update(sql);
			Workbook book = Workbook.getWorkbook(new File(filePath));
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			int totalRows = sheet.getRows();
			Record row = null;
			int i = 0;
			for (int rowNum = 1; rowNum < totalRows; rowNum++) {
				row = new Record();
				i = 0;
				row.set("matName", sheet.getCell(i++, rowNum).getContents());
				row.set("matNo", sheet.getCell(i++, rowNum).getContents());
				row.set("kucun", sheet.getCell(i++, rowNum).getContents());
				Db.save("a_mat_kucun", row);
			}
			book.close();
	}

}
