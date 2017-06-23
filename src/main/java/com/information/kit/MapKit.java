package com.information.kit;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings({ "rawtypes", "serial" })
public class MapKit extends HashMap{

    private MapKit(){
		
	}
	
	public static MapKit create(){
		return new MapKit();
	}	
	
	@SuppressWarnings("static-access")
	public static MapKit set(String key,Object value){
		return new  MapKit().set(key, value);
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
	
	@SuppressWarnings({ "unchecked"})
	public MapKit set(Map map){
		putAll(map);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public MapKit set(MapKit map){
		putAll(map);
		return this;
	}
}
