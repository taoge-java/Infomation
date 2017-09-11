package com.information.controller.room;

import com.information.annotation.ControllerMapping;
import com.information.controller.base.BaseController;

/**
 * 聊天室
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年8月16日下午9:13:30
 */
@ControllerMapping(url="/system/room")
public class RoomChatController extends BaseController{

	public void index(){
		String userName =getUserName();
		setAttr("userName", userName);
		rendView("/room/chat.vm");
	}
}
