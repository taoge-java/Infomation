package com.information.dao.weixin.message.response;

import com.information.dao.weixin.base.BaseMessae;
import com.information.dao.weixin.message.Image;
/**
 * 回复图片消息
 * @author zengjintao
 * @version 1.0 
 * 2017年4月8日9:21
 */
public class ResponseImageMessage extends BaseMessae {

	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		this.Image = image;
	}
}
