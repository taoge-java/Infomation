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
		
	}
	
	public void setSessionAttr(){
		map.put(SESSION_ID_KEY, Md5Utils.generatorKey());
	}
	
	public String getSessionId(){
		return (String) map.get(SESSION_ID_KEY);
	}
//	/**
//	 * 添加用户
//	 * @param session
//	 */
//	public void add(String key,UserSession session){
//         map.put(key, session);
//	}
//	
//	/**
//	 * 移除在线用户
//	 * @param session
//	 */
//	public void remove(String key){
//		map.remove(key);
//	}
//	
//	/**
//	 * 获取在线用户人数
//	 * @return
//	 */
//	public int getOlineCount(){
//		LOG.info("当前在线人数:"+map.size());
//		return map.size();
//	}
//	
//	/**
//	 * 获取所有在线用户
//	 * @return
//	 */
//	public List<UserSession> getAllUserSession(){
//		return new ArrayList<UserSession>(map.values());
//	}
//	
//	public UserSession getUserSessionByid(String key){
//		return map.get(key);
//	}
}
