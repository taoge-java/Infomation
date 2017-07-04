package com.information.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期工具类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年5月6日 下午4:18:27
 */
public class DateUtil {
	
	private static final SimpleDateFormat FORMAT=getFormat("yyy-MM-dd HH:mm");
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getDate(){
		return new Date();
	}
	
	public synchronized static SimpleDateFormat getFormat(String format){
		return new SimpleDateFormat(format);
	}
	
	/**
	 * 将日期转换成字符串形式
	 * @return
	 */
	public synchronized static String getStrDate(Date date){
		return FORMAT.format(date);
	}
	
	/**
	 * 将日期转成字符串
	 * @return
	 */
	public static String toLocaleString() {
        DateFormat formatter = DateFormat.getDateTimeInstance();
        return formatter.format(getDate());
	}
	
	public static void main(String[] args) {
		System.err.println(toLocaleString());;
	}
}
