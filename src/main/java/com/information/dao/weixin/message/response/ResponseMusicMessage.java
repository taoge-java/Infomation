package com.information.dao.weixin.message.response;

import com.information.dao.weixin.base.BaseMessae;
import com.information.dao.weixin.message.Music;
/**
 * 回复音乐
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月4日下午10:11:21
 * 
 * <xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[music]]></MsgType>
		<Music>
		<Title><![CDATA[TITLE]]></Title>
		<Description><![CDATA[DESCRIPTION]]></Description>
		<MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
		<HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
		<ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
		</Music>
	</xml>
 */
public class ResponseMusicMessage extends BaseMessae{

	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
