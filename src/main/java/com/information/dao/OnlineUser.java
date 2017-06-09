package com.information.dao;

import java.util.HashMap;
import java.util.Map;

import com.information.utils.Md5Utils;

/**
 * 在线用户管理
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月4日 下午4:52:09
 */
public class OnlineUser {
	
	private Map<String,Object> map=new HashMap<String,Object>();
	
	public static final String SESSION_ID_KEY="session_id_key";
	
	public OnlineUser(){
		setSessionAttr(SESSION_ID_KEY,Md5Utils.generatorKey());
	}
	
	public void setSessionAttr(String key,String code){
		map.put(key,code);
	}
	
	public String getSessionId(){
		return (String) map.get(SESSION_ID_KEY);
	}
}
