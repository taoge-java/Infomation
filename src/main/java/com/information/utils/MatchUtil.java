package com.information.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 正则表达式工具类
 * @author Administrator
 *
 */
public class MatchUtil {
	/**
	 * 日期校验
	 * @param time
	 * @return
	 */
	public static Matcher getMatcher(String time){
		Pattern pattern=Pattern.compile("(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])");
		Matcher mat=pattern.matcher(time);
		return mat;
	}
	/**
	 * 手机号码校验
	 * @param mobile
	 * @return
	 */
	public static boolean moblieMatcher(String mobile){
		Pattern pattern=Pattern.compile("13\\d{9}||18\\d{9}||15\\d{9}||17\\d{9}");
		Matcher mat=pattern.matcher(mobile);
		if(!mat.matches()){
			return false;
		}
		return true;
	}
	
	/**
	 * 数值校验(整数或小数(最多两位小数))
	 * @param number
	 * @return
	 */
	public static Matcher numberMatcher(String number){
		Pattern pattern=Pattern.compile("^[0-9]+\\.?[0-9]{2}*$");
		Matcher mat=pattern.matcher(number);
		return mat;
	}
}
