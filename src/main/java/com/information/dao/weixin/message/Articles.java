package com.information.dao.weixin.message;


/**
 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
 * @author zengjintao
 * @version 1.0
 */
public class Articles {
	
	private String Title;
	
    private String Description;
	
	private String PicUrl;
	
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
