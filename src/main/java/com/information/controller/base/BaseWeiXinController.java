package com.information.controller.base;

import java.io.IOException;

import com.information.dao.weixin.message.TextMessage;
import com.information.dao.weixin.message.response.ResponseImageMessage;
import com.information.dao.weixin.message.response.ResponseMusicMessage;
import com.information.dao.weixin.message.response.ResponseNewsMessage;
import com.information.dao.weixin.message.response.ResponseVideoMessage;
import com.information.dao.weixin.message.response.ResponseVoiceMessage;
import com.jfinal.core.Controller;

public abstract class BaseWeiXinController extends Controller{

	public abstract void sendTextMessge(TextMessage responseText,String fromUser,String toUser,String content) throws IOException;
	
	public abstract void sendImageMessage(ResponseImageMessage responseImage,String fromUser,String toUser) throws IOException;
	
	public abstract void sendGraphicMessage(ResponseNewsMessage responseGraphic,String fromUser,String toUser) throws IOException;
	
	public abstract void sendVoiceMessage(ResponseVoiceMessage responseVoice,String fromUser,String toUser) throws IOException;
	
	//public abstract void sendVideoMessage(ResponseVideoMessage responseViedo,String fromUser,String toUser) throws IOException;
	
	//public abstract void sendMusicMessage(ResponseMusicMessage responseMusic,String fromUser,String toUser) throws IOException;

}
