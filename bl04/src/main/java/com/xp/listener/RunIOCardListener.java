package com.xp.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xp.running.RunIOCard;
import com.xp.util.XPProperties;


/**
	<!-- 在web.xml添加以下即可
	<listener>
		<listener-class>com.xp.listener.RunIOCardListener</listener-class>
	</listener> 
	-->
 * @author Administrator
 *
 */
public class RunIOCardListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		if("PC".equals(XPProperties.get("RUN_ENVIRONMENT"))){
			RunIOCard.main(null);
		}
	}
	

}
