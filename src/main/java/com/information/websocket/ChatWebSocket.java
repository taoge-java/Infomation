package com.information.websocket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.information.dao.websocket.Message;
import com.jfinal.log.Log;
/**
 * websocket连接通道
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月13日 下午10:41:45
 */
@ServerEndpoint("/chat")
public class ChatWebSocket {

	private static final Log LOG=Log.getLog(ChatWebSocket.class);
	
	private static List<Session> session=new ArrayList<Session>();
	
    private static List<String> names=new ArrayList<String>();
	
	private String name;
	
	private static int onineCount=0;
	
	/**
	 * 连接websocket
	 * @param session
	 */
	@SuppressWarnings("static-access")
	@OnOpen
	public void onopen(Session session){
		name=session.getQueryString();
		this.names.add(name);
		this.session.add(session);
		String msg="欢迎"+name+"进入聊天室";
		getOnlineCount(true);
		System.out.println(msg);
		Message message=new Message();
		message.setWelcome(msg);
		message.setName(names);
		broadcast(this.session,message.tojson());
	}
	
	/**
	 * 广播信息
	 * @param s
	 * @param msg
	 */
	public void broadcast(List<Session> s,String msg){
		for(Iterator<Session> it=s.iterator();it.hasNext();){
			Session session=it.next();
			try {
				session.getBasicRemote().sendText(msg);
			} catch (Exception e) {
				LOG.error("广播信息异常",e);
			}
		}
	}
	
	/**
	 * WebSocket连接出错
	 */
	public void onerror(){
		System.err.println("websocket连接异常");
	}
	
	/**
	 * 关闭websocket
	 */
	@SuppressWarnings("static-access")
	public void onclose(Session session){
		String msg=name+"离开了聊天室";
		this.session.remove(session);
		this.names.remove(this.name);
		getOnlineCount(false);
		Message message=new Message();
		message.setWelcome(msg);
		message.setName(names);
		broadcast(this.session,message.tojson());
	}
	
	
	@SuppressWarnings("static-access")
	@OnMessage
	public void onmessage(Session session,String msg){
		Message message=new Message();
		message.setContent(msg, this.name);
		broadcast(this.session,message.tojson());
	}
	
	/**
	 * 获取当前在线人数
	 * @param flag
	 * @return
	 */
	public int getOnlineCount(boolean flag){
		if(flag){
			return onineCount++;
		}
		return onineCount--;
	}
}
