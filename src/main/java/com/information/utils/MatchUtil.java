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
	public static Matcher getMoblieMatcher(String mobile){
		Pattern pattern=Pattern.compile("(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])");
		Matcher mat=pattern.matcher(mobile);
		return mat;
	}
}
