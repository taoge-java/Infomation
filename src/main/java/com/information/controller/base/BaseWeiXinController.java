package com.information.controller.base;

import java.io.IOException;

import com.information.dao.weixin.message.TextMessage;
import com.information.dao.weixin.message.response.ResponseImageMessage;
import com.information.dao.weixin.message.response.ResponseNewsMessage;
import com.jfinal.core.Controller;

public abstract class BaseWeiXinController extends Controller{

	/**
	 * 回复文本信息
	 */
	public abstract void sendTextMessge(TextMessage text,String fromUser,String toUser,String content);
	
	/**
	 * 回复图片信息
	 * @throws IOException 
	 */
	public abstract void sendImageMessage(ResponseImageMessage image,String fromUser,String toUser) throws IOException;
	
	/**
	 * 回复图文信息
	 */
	public abstract void sendGraphicMessage(ResponseNewsMessage graphic,String fromUser,String toUser);
}
