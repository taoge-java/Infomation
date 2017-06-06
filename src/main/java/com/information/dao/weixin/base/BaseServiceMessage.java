package com.information.dao.weixin.base;
/**
 * 客服消息基类
 * @author Administrator
 *
 */
public abstract class BaseServiceMessage {

	private String access_token;
	
	private String touser;
	
	private String msgtype;

	public String getAccess_token() {
		return access_token;
	}
 
	public abstract String execute(BaseServiceMessage service);
	
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
}
