package com.xp.listener;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xp.running.RunSendDataPCToPLANT01;
import com.xp.util.XPProperties;
import com.xp.util.XPTime;

/**
	<!-- 在web.xml添加以下即可
	<listener>
		<listener-class>com.xp.listener.XPListener</listener-class>
	</listener> 
	-->
 * @author Administrator
 *
 */
public class XPListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		doCancelAllTask();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//PLANT TO CENTER
		if("PLANT".equals(XPProperties.get("RUN_ENVIRONMENT"))){
			//主动推送数据给到云中心 - 采集部分,10秒同步一次
			doTask("RunSendDataPCToPLANT01",new RunSendDataPCToPLANT01(),10,10);
		}
		//doTask("SendMsg02Task",new SendMsg02Task(),15,60); //delay秒后开始，period秒周期
	}
	
	private TreeMap<String, Timer> mapTimer = new TreeMap<>();
	
	/**
	 * delay秒后开始，period秒周期
	 * @param key
	 * @param timerTask
	 * @param delay
	 * @param period
	 */
	public void doTask(String key,TimerTask timerTask,long delay, long period){
		doCancelTask(key);
		Timer timer = new Timer(true);
		timer.schedule(
				timerTask,
				delay * XPTime.sec, //n秒后执行
				period * XPTime.sec //每n秒执行一次
				);
		mapTimer.put(key,timer);
	}
	
	private void doCancelTask(String key){
		if(mapTimer.get(key) != null){
			mapTimer.get(key).cancel();
		}
	}
	
	private void doCancelAllTask(){
		Set<String> keySet = mapTimer.keySet();
		for(String key : keySet){
			doCancelTask(key);
		}
	}
}
