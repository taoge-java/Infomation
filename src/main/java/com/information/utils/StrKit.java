package com.information.utils;

/**
 * 字符串处理工具类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月18日 下午10:02:37
 */
public class StrKit {
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	@SuppressWarnings("null")
	public static boolean isEmpoty(String str){
		return str==null &&str.trim().length() == 0 ? true:false;
	}
	
	@SuppressWarnings("null")
	public static boolean isEmpoty(Object object){
		return object==null &&object.toString().trim().length() == 0 ? true:false;
	}
	
	public static boolean isNotEmpoty(String str){
		return str!=null && str.trim().length() > 0 ? true:false;
	}
	
	@SuppressWarnings("null")
	public static boolean isNotEmpoty(Object object){
		return object !=null ||object.toString().trim().length()!=0 ? true:false;
	}
	
	/**
	 * 将字符串转换成数组
	 * @param str
	 * @return
	 */
	public static String[] spilt(String str){
		if(str == null)
			throw new NullPointerException("str can not be null");
		return str.split(",");
	}
	
	public static String[] spilt(String str,String separator){
		if(str == null)
			throw new NullPointerException("str can not be null");
		return str.split(separator);
	}
}
