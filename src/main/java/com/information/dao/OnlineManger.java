package com.information.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 在线用户管理器
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月4日 下午6:06:34
 */
@Service
public class OnlineManger {

	private  Map<String,UserSession> session=new HashMap<String,UserSession>();
	
	/**
	 * 添加用户
	 * @param userSession
	 */
	public void add(UserSession userSession){
		session.put(userSession.getSessionId(), userSession);
	}
	
	/**
	 * 移除用户
	 * @param userSession
	 */
	public void remove(UserSession userSession){
		session.remove(userSession.getSessionId());
	}
	
	/**
	 * 获取所有在线用户
	 * @return
	 */
	public List<UserSession> getAllUserSession(){
		return new ArrayList<UserSession>(session.values());
	}
	
	/**
	 * 跟据sessionId获取用户信息
	 * @param sessionId
	 * @return
	 */
	public UserSession getUserSession(String sessionId){
		return session.get(sessionId);
	}
	
	/**
	 * 跟据userId获取用户信息
	 * @param userId
	 * @return
	 */
	public UserSession getUserSessionById(int userId){
		for(String key:session.keySet()){
			UserSession userSession=session.get(key);
			if(userSession.getUserId()==userId){
				return userSession;
			}
		}
		return null;
	}
}
