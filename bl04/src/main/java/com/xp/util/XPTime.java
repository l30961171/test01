package com.xp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class XPTime {
	/**
	 * Timer 定时器部分工具
	 */
	public static final int sec = 1000; //秒
	public static final int min = sec*60; //分
	public static final int hour = min*60; //时
	public static final int day = hour*24; //天
	public static int startTime(int target_hour,int target_minute,int target_second){
		Date nowdate = new Date();
		int H = Integer.parseInt(new SimpleDateFormat("HH").format(nowdate));
		int m = Integer.parseInt(new SimpleDateFormat("mm").format(nowdate));
		int s = Integer.parseInt(new SimpleDateFormat("ss").format(nowdate));
		int nowS = ( H * hour ) + ( m * min ) + s*sec;
		int target = ( target_hour * hour ) + ( target_minute * min ) + target_second*sec;
		int delay = target - nowS;
		if(delay < 0){
			//如果当前已经过了当天指定时间点，则到下一天的指定时间点
			delay += day;
		}
		return delay;
	}
	
	public static int startTime(int target_weekDay,int target_hour,int target_minute,int target_second){
		Date nowdate = new Date();
		int w = getWeekDay();
		int h = Integer.parseInt(new SimpleDateFormat("HH").format(nowdate));
		int m = Integer.parseInt(new SimpleDateFormat("mm").format(nowdate));
		int s = Integer.parseInt(new SimpleDateFormat("ss").format(nowdate));
		
		int nowS = ( w * day ) + ( h * hour ) + ( m * min ) + s*sec;
		int target = ( target_weekDay * day ) + ( target_hour * hour ) + ( target_minute * min ) + target_second*sec;
		int delay = target - nowS;
		if(delay < 0){
			//如果当前已经过了当周指定时间点，则到下一周的指定时间点
			delay += day * 7;
		}
		return delay;
	}
	
	private static int getWeekDay(){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		return gc.get(GregorianCalendar.DAY_OF_WEEK) - 1;
	}
	// end Timer 定时器部分工具
	
}
