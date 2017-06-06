package com.information.dao.weixin.message.request;

import com.information.dao.weixin.base.BaseMessae;

/**
 * 接收链接消息
 * @author zengjintao
 * @version 1.0
 * 2017年4月8日9:37
 * <xml>
	<ToUserName><![CDATA[toUser]]></ToUserName>
	<FromUserName><![CDATA[fromUser]]></FromUserName>
	<CreateTime>1351776360</CreateTime>
	<MsgType><![CDATA[link]]></MsgType>
	<Title><![CDATA[公众平台官网链接]]></Title>
	<Description><![CDATA[公众平台官网链接]]></Description>
	<Url><![CDATA[url]]></Url>
	<MsgId>1234567890123456</MsgId>
   </xml>
 */
public class RequestLinkMessage extends BaseMessae{

	private String Title;
	
	private String Description;
	
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

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
