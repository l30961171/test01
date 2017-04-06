package com.xp.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.ViewType;
import com.xp.action.*;
import com.xp.action.center.ApiCenter;
import com.xp.action.center.ImportKanBanController;
import com.xp.action.center.ImportKuCunController;
import com.xp.action.center.KanBanController;
import com.xp.action.plant.ApiPlant;
import com.xp.model.ViewListRoleMenu;
import com.xp.model.ViewListUser;
import com.xp.model.ViewListUserAuth;
import com.xp.model.ViewListUserMenu;
import com.xp.model.ViewListUserRole;
import com.xp.model._MappingKit;

/**
 * API引导式配置
 */
public class WebConfig extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("xp.config");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP); 							// 设置视图类型为Jsp，否则默认为FreeMarker
		me.setBaseUploadPath(PropKit.get("baseUploadPath","C://xpupload")); 	//默认文件上传路径
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class,"/WEB-INF/jsp/Index");	// 第三个参数为该Controller的视图存放路径
		me.add("/Index", IndexController.class,"/WEB-INF/jsp/Index");	
		me.add("/Login", LoginController.class,"/WEB-INF/jsp/Login");	
		me.add("/BaseWeb", BaseWebController.class,"/WEB-INF/jsp/BaseWeb");
		me.add("/Notice", NoticeController.class,"/WEB-INF/jsp/Notice");
		me.add("/ImportKuCun", ImportKuCunController.class,"/WEB-INF/jsp/app.ImportKuCun");
		me.add("/ImportKanBan", ImportKanBanController.class,"/WEB-INF/jsp/app.ImportKanBan");
		me.add("/KanBan", KanBanController.class,"/WEB-INF/jsp/app.KanBan");
		me.add("/ApiPlant", ApiPlant.class,"/WEB-INF/jsp/");
		me.add("/ApiCenter", ApiCenter.class,"/WEB-INF/jsp/");
	}
	
	
	public static DruidPlugin createDruidPlugin(){
		DruidPlugin dp = new DruidPlugin(
				PropKit.get("jdbc.url"), 
				PropKit.get("jdbc.username"), 
				PropKit.get("jdbc.password").trim());
		dp.setTimeBetweenEvictionRunsMillis(600000);
		
		// DruidPlugin
		dp.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		dp.addFilter(wall);
		return dp;
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置druid数据库连接池插件
		DruidPlugin dp = createDruidPlugin();
		me.add(dp);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setShowSql(PropKit.getBoolean("show_sql", false));//这句话就是ShowSql
		me.add(arp);
		
		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
		arp.addMapping("view_list_role_menu", ViewListRoleMenu.class);
		arp.addMapping("view_list_user", ViewListUser.class);
		arp.addMapping("view_list_user_auth", ViewListUserAuth.class);
		arp.addMapping("view_list_user_menu", ViewListUserMenu.class);
		arp.addMapping("view_list_user_role", ViewListUserRole.class);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		DruidStatViewHandler dvh =  new DruidStatViewHandler("/xpdruid");
		me.add(dvh);
	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}
}
