package com.information.dao.weixin.message.request;

import com.information.dao.weixin.base.BaseMessae;

/**
 * 接收视频消息
 * @author zengjintao
 * @version 1.0
 * 2017年4月8日9:25
 * 
 * <xml>
	<ToUserName><![CDATA[toUser]]></ToUserName>
	<FromUserName><![CDATA[fromUser]]></FromUserName>
	<CreateTime>1357290913</CreateTime>
	<MsgType><![CDATA[video]]></MsgType>
	<MediaId><![CDATA[media_id]]></MediaId>
	<ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
	<MsgId>1234567890123456</MsgId>
   </xml>
 */
public class RequestVideoMessage extends BaseMessae{

	private String MediaId;//视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	
	private String ThumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
