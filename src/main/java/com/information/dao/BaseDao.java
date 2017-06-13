package com.information.dao;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class BaseDao {

	private Map<String,Object>  map=new HashMap<String,Object>();
	
	/**
	 * 设置值
	 * @param key
	 * @param value
	 */
	public void set(String key,Object value){
		map.put(key, value);
	}
	
	/**
	 * 取值
	 * @param key
	 * @return
	 */
	public Object get(String key){
		return map.get(key);
	}
	
	
	public void put(String key,Object value){
		map.put(key, value);
	}
	
	public Integer getInt(String key){
		return (Integer)map.get(key);
	}
	
	public String getStr(String key){
		return (String)map.get(key);
	}
	
	public Float getFloat(String key){
		return (Float)map.get(key);
	}
	
	/**
	 * 转成Json
	 * @return
	 */
	public JSONObject toJson(){
		return JSONObject.fromObject(this);
	}
}
