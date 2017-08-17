package com.information.controller;


import org.springframework.beans.factory.annotation.Autowired;

import com.information.annotation.ControllerRoute;
import com.information.controller.base.BaseController;
import com.information.dao.OnlineManger;
import com.information.dao.UserSession;
import com.information.utils.ResultCode;
/**
 * 系统首页
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月26日 下午4:36:21
 */
@ControllerRoute(url="/")
public class IndexController extends BaseController{

	@Autowired
	private OnlineManger onlineManger;
	
	public void index(){
		
	}
	/**
	 * 顶部页面
	 */
	public void top(){
		rendView("/top.vm");
	}
	/**
	 * 左部页面
	 */
    public void left(){
    	rendView("/left.vm");
	}
    /**
	 * 右部页面
	 */
    public void right(){
    	rendView("/right.vm");
   	}
	/**
	 * 首页
	 */
	public void success(){
//		HttpServletRequest request=getRequest();
//		Cookie[] cookie=request.getCookies();
//		String gotoURL = request.getRequestURL().toString();
//		if(cookie!=null){
//			for(Cookie c:cookie){
//				if(c.getName().equals(SysConfig.cookie_name)){
//					break;
//				}
//			}
			rendView("/index.vm");
//		}else{
//			redirect("http://localhost:8080/Auth-SSO/account"+"?action=success"+"&gotoUrL="+gotoURL);
//		}
	}
	
	public void main(){
		rendView("/main.vm");
	}
	
	/**
	 * 客户端向服务器发送心跳包
	 */
	public void heart(){
		UserSession session=getCurrentUser();
		UserSession onlineUser=onlineManger.getUserSession(session.getSessionId());
		if(onlineUser==null){
			renderJson(new ResultCode(ResultCode.FAIL, "您的帐号已在别处登录，请重新登录！"));
			return;
		}
		renderNull();
	}
}
