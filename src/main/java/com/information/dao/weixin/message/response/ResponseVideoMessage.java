package com.information.dao.weixin.message.response;

import com.information.dao.weixin.base.BaseMessae;
import com.information.dao.weixin.message.Video;

/**
 * 回复视频消息
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月4日下午10:05:11
 */
public class ResponseVideoMessage extends BaseMessae{

	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
