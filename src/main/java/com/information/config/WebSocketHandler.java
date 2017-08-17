package com.information.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

/**
 * websocket请求放行
 * @author zengjintao
 * @version 1.0
 * created 2017年8月17日上午8:33:11
 */
public class WebSocketHandler extends Handler{

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		if(target.startsWith("/chat")) {
			return;
		}
		next.handle(target, request, response, isHandled);
	}
}
