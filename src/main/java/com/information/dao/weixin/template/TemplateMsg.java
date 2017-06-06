package com.information.dao.weixin.template;

import java.util.Map;


/**
 * 消息模板
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年5月15日 下午4:49:29
 */
public class TemplateMsg {
	
	private String touser;//微信接收者
	
	private String template_id;//消息模板id
	
	private String topcolor;
	
	private String url;//服务器地址
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}


	public Map<String, TemplateData> getData() {
		return data;
	}

	public void setData(Map<String, TemplateData> data) {
		this.data = data;
	}


	private Map<String, TemplateData> data;
	
	
	
}
