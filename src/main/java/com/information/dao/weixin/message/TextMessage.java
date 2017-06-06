package com.information.dao.weixin.message;

import com.information.dao.weixin.base.BaseMessae;

/**
 * 文本消息
 * @author zengjintao
 * @version 1.0
 * 2017年4月8日9:15
 * <xml>
	 <ToUserName><![CDATA[toUser]]></ToUserName>
	 <FromUserName><![CDATA[fromUser]]></FromUserName>
	 <CreateTime>1348831860</CreateTime>
	 <MsgType><![CDATA[text]]></MsgType>
	 <Content><![CDATA[this is a test]]></Content>
	 <MsgId>1234567890123456</MsgId>
  </xml>
 */
public class TextMessage extends BaseMessae{

	private String Content;//消息内容

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
