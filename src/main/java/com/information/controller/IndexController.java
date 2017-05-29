package com.information.controller;

import com.information.common.BaseController;
import com.jfinal.ext.route.ControllerBind;
/**
 * 系统首页
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月26日 下午4:36:21
 */
@ControllerBind(controllerKey="/")
public class IndexController extends BaseController{

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
		rendView("/index.vm");
	}
	
	public void main(){
		rendView("/main.vm");
	}
}
