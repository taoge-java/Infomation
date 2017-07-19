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
	
	private static final SimpleDateFormat SECOND=getFormat("yyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat DAY=getFormat("yyy-MM-dd");
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getDate(){
		return new Date();
	}
	/**
	 * 返回指定日期格式
	 * @param format
	 * @return
	 */
	public synchronized static SimpleDateFormat getFormat(String format){
		return new SimpleDateFormat(format);
	}
	
	/**
	 * 将日期转换成 yyy-MM-dd HH:mm 字符串形式
	 * @return
	 */
	public synchronized static String getFormatDate(Date date){
		return FORMAT.format(date);
	}
	
	/**
	 * 将日期转换成 yyy-MM-dd HH:mm:ss 字符串形式
	 * @return
	 */
	public synchronized static String getSecondDate(Date date){
		return SECOND.format(date);
	}
	/**
	 * 将日期转换成 yyy-MM-dd HH:mm:ss 字符串形式
	 * @return
	 */
	public synchronized static String getDayDate(Date date){
		return DAY.format(date);
	}
	/**
	 * 将日期转成字符串
	 * @return
	 */
	public static String toLocaleString() {
        DateFormat formatter = DateFormat.getDateTimeInstance();
        return formatter.format(getDate());
	}
}
