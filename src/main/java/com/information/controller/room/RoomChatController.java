package com.information.controller.room;

import com.information.annotation.ControllerRoute;
import com.information.controller.base.BaseController;

/**
 * 聊天室
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年8月16日下午9:13:30
 */
@ControllerRoute(url="/system/room")
public class RoomChatController extends BaseController{

	public void index(){
		String userName = getCurrentUser().getLoginName();
		setAttr("userName", userName);
		rendView("/room/chat.vm");
	}
}
