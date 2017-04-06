package com.xp.model;

import java.util.List;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;
import com.xp.config.WebConfig;
import com.xp.util.db.M;
import com.xp.util.db.XpMap;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class _JFinalDemoGenerator {
	
	public static DataSource getDataSource() {
		PropKit.use("xp.config");
		DruidPlugin c3p0Plugin = WebConfig.createDruidPlugin();
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}
	
	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.xp.model.base";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/xp/model/base";
		
		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.xp.model";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = baseModelOutputDir + "/..";
		
		// 创建生成器
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		// 添加不需要生成的表名
		addExcludedTable(gernerator,"LIKE 'flowtb_%'","LIKE 'view_%'","LIKE 'act_%'","LIKE 'flow_%'");
		
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(true);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		//gernerator.setRemovedTableNamePrefixes("t_");
		// 生成
		gernerator.generate();
	}
	
	// 添加不需要生成的表名
	private static void addExcludedTable(Generator gernerator,String... tables){
		M dao = new M();
		dao.autoCommit(false);
		List<XpMap> tbs = null;
		for(String table : tables){
			tbs = dao.doSqlSelect("SHOW TABLES " + table);
			if(!tbs.isEmpty()){
				String title = (String) tbs.get(0).keySet().toArray()[0];
				for(XpMap tb : tbs){
					gernerator.addExcludedTable(tb.get(title).toString());
				}
			}
		}
		dao.commit();
	}
}




