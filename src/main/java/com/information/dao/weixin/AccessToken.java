package com.information.dao.weixin;
/**
 * 获取access_token
 * @author zengjintao
 * @version 1.0
 * 2017年4月7日下午22:55
 */
public class AccessToken {
	
    private String accessToken;
	
	private int time_out;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getTime_out() {
		return time_out;
	}

	public void setTime_out(int time_out) {
		this.time_out = time_out;
	}
}
