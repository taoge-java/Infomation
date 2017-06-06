package com.information.dao.weixin.message.request;

import com.information.dao.weixin.base.BaseMessae;

/**
 * 接收图片消息
 * @author zengjintao
 * @version 1.0
 * 2017年4月9日19:11
 *  <xml>
	 <ToUserName><![CDATA[toUser]]></ToUserName>
	 <FromUserName><![CDATA[fromUser]]></FromUserName>
	 <CreateTime>1348831860</CreateTime>
	 <MsgType><![CDATA[image]]></MsgType>
	 <PicUrl><![CDATA[this is a url]]></PicUrl>
	 <MediaId><![CDATA[media_id]]></MediaId>
	 <MsgId>1234567890123456</MsgId>
    </xml>
 * 
 */
public class RequestImageMessage extends BaseMessae{

	private String PicUrl;
	
	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	private String MediaId;
	
	private String MsgId;
}
