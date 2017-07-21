package com.information.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.information.utils.StrKit;
import com.jfinal.handler.Handler;
/**
 * 项目路径处理器
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月11日 下午6:47:35
 */
public class ContextPathHandler extends Handler{

	private String contextPath;
	
	public ContextPathHandler(){
		this.contextPath="BASE_PATH";
	}
	
	public ContextPathHandler(String contextPath){
		this.contextPath=contextPath;
	}
	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		if(StrKit.isEmpoty(contextPath))
			throw new IllegalArgumentException("contextPathName can not be blank.");
		request.setAttribute(contextPath, request.getContextPath());
		next.handle(target, request, response, isHandled);
	}

}
