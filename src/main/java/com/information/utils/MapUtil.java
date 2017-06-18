package com.information.utils;

import java.math.BigDecimal;
import java.util.HashMap;
/**
 * map工具类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月17日 下午11:24:12
 */
import java.util.Map;


@SuppressWarnings({ "rawtypes", "serial" })
public class MapUtil extends HashMap{

	private MapUtil(){
		
	}
	
	public static MapUtil create(){
		return new MapUtil();
	}	
	
	@SuppressWarnings("static-access")
	public static MapUtil set(String key,Object value){
		return new  MapUtil().set(key, value);
	}
	
	/**
	 * 获取字符串
	 * @param key
	 * @return
	 */
	public String getStr(String key){
		return (String) get(key);
	}
	
	/**
	 * 获取整型
	 */
	public Integer getInt(String key){
		return (Integer) get(key);
	}
	
	/**
	 * 获取整型
	 */
	public Double getDouble(String key){
		return (Double) get(key);
	}
	
	/**
	 * 获取
	 */
	public BigDecimal getBigDecimal(String key){
		return (BigDecimal)get(key);
	}
	
	@SuppressWarnings("unchecked")
	public MapUtil set(Map map){
		putAll(map);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public MapUtil set(MapUtil map){
		putAll(map);
		return this;
	}
	
}
