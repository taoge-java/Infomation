package com.information.dao;

import java.util.HashSet;
import java.util.Set;
/**
 * 保存用户登录信息
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年5月6日 下午4:16:11
 */
public class UserSession {
	
    private int sessionId;//用户sessionId
	
	private int userId;//用户id
	
	private String loginName;//登录名
	
	private String nickName;//昵称
	
	private String realName;//真实姓名
	
	private String mobile;//手机号
	
	private String mail;//邮箱号
	
	private boolean superFlag;//是否超级管理员
	
	private String last_login_time;//登录时间
	
	private String last_login_ip;//用户登录ip
	
	private Set<String> menuCode;//菜单code
	
	private Set<String> operCode;//操作code
	
	
	public String getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}

	public boolean isSuperFlag() {
		return superFlag;
	}
	
	public void setSuperFlag(boolean superFlag) {
		this.superFlag = superFlag;
	}
	
	/**
	 * 操作列表
	 */
	private Set<String> operCodeSet = new HashSet<String>();
	/**
	 * 菜单列表
	 */
	private Set<String> menuCodeSet = new HashSet<String>();
	
	public int getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getRealName() {
		return realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getLast_login_ip() {
		return last_login_ip;
	}
	
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	
	public Set<String> getMenuCode() {
		return menuCode;
	}
	
	public void setMenuCode(Set<String> menuCode) {
		this.menuCode = menuCode;
	}
	
	public Set<String> getOperCode() {
		return operCode;
	}
	
	public void setOperCode(Set<String> operCode) {
		this.operCode = operCode;
	}
	
	public Set<String> getOperCodeSet() {
		return operCodeSet;
	}
	
	public void setOperCodeSet(Set<String> operCodeSet) {
		this.operCodeSet = operCodeSet;
	}
	
	public Set<String> getMenuCodeSet() {
		return menuCodeSet;
	}
	
	public void setMenuCodeSet(Set<String> menuCodeSet) {
		this.menuCodeSet = menuCodeSet;
	}
	
    public boolean HasMenu(){
    	 if(isSuperFlag())
    		 return true;
    	 return menuCodeSet.contains(menuCode);
    }

    public boolean hasOper(String code){
    	if(isSuperFlag())
   		   return true;
   	    return operCodeSet.contains(operCode);
    }
    
    public boolean hasAnyOper(String[] operCode){
    	for(String code:operCode){
    		if(hasOper(code)){
    			return true;
    		}
    	}
    	return false;
    }
}
