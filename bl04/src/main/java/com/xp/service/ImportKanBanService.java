package com.xp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xp.model.WebUser;

import jxl.Sheet;
import jxl.Workbook;

public class ImportKanBanService {
	public static ImportKanBanService service = new ImportKanBanService();

	public void execute(String filePath,WebUser creator) throws Exception  {
			
			List<Record> rows = new ArrayList<>();
			Workbook book = null;
			try {
				book = Workbook.getWorkbook(new File(filePath));
				// 获得第一个工作表对象
				Sheet sheet = book.getSheet(0);
				int totalRows = sheet.getRows();
				int i = 0;
				for (int rowNum = 1; rowNum < totalRows; rowNum++) {
					Record row = new Record();
					i = 0;
					row.set("banCiDate", 		sheet.getCell(i++, rowNum).getContents());
					row.set("macNo", 			sheet.getCell(i++, rowNum).getContents());
					row.set("banBie", 			sheet.getCell(i++, rowNum).getContents());
					row.set("zhuangTai", 		sheet.getCell(i++, rowNum).getContents());
					row.set("matName", 			sheet.getCell(i++, rowNum).getContents());
					row.set("biaoZhunChanNeng",	sheet.getCell(i++, rowNum).getContents());
					row.set("zhuSuQty", 		sheet.getCell(i++, rowNum).getContents());
					row.set("yaJianQty", 		sheet.getCell(i++, rowNum).getContents());
					row.set("zhuangXiangQty", 	sheet.getCell(i++, rowNum).getContents());
					row.set("buLiangQty", 		sheet.getCell(i++, rowNum).getContents());
					row.set("createUser", 		creator.getUserName());
					row.set("id", row.getStr("macNo") + row.getStr("banCiDate") + row.getStr("banBie"));
					rows.add(row);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(book != null)
					book.close();
			}
			
			String sql = "";
			for(Record row : rows){
				//先清空数据
				sql = "delete from kb_dianshi_01_rec where id=?";
				Db.update(sql,row.getStr("id"));
				
				Db.save("kb_dianshi_01_rec", row);
			}
			
			sql = "delete from kb_dianshi_01";
			Db.update(sql);
			
			String commField = "macNo,zhuangTai,matName,biaoZhunChanNeng,"
					+ "zhuSuQty,yaJianQty,zhuangXiangQty,buLiangQty,"
					+ "banCiDate,banBie,createUser,id ";
			sql = "insert into kb_dianshi_01 ("+commField+") "
					+ "(select " + commField + " from kb_dianshi_01_rec "
					+ "WHERE id IN (SELECT max(id) FROM kb_dianshi_01_rec GROUP BY macNo)) ";
			Db.update(sql);
	}

}
