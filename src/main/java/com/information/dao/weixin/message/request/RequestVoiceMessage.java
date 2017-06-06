package com.information.dao.weixin.message.request;

import com.information.dao.weixin.base.BaseMessae;

/**
 * 接收语音消息
 * @author zengjintao
 * @version 1.0
 * 2017年4月8日9:21
 * 
 * <xml>
	<ToUserName><![CDATA[toUser]]></ToUserName>
	<FromUserName><![CDATA[fromUser]]></FromUserName>
	<CreateTime>1357290913</CreateTime>
	<MsgType><![CDATA[voice]]></MsgType>
	<MediaId><![CDATA[media_id]]></MediaId>
	<Format><![CDATA[Format]]></Format>
	<MsgId>1234567890123456</MsgId>
   </xml>
 */
public class RequestVoiceMessage extends BaseMessae{
	
	private String MediaId;	//语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	
	private String Format;//	语音格式，如amr，speex等
	
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}
}
