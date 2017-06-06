package com.information.interceptor;

import com.information.controller.base.BaseWeiXinController;
import com.information.utils.EncryptUtil;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
/**
 * 微信接入拦截器
 * @author zengjintao
 * @version 1.0
 * 2017年4月7日上午8:30
 */
public class WeiXinInterceptor implements Interceptor{

	public void intercept(Invocation inv) {
		Controller controller=inv.getController();
		String type=controller.getRequest().getMethod();//判断客户端请求方式
		if(!(controller instanceof BaseWeiXinController))
			throw new RuntimeException("控制器需要继承BaseWeiXinController");
		/**
		 * 如果是get请求,则配置服务器并返回
		 */
		if(type.equals("get")||type.equals("GET")){
			configServer(controller);
			return ;
		}
		if(checkSignature(controller)){
			inv.invoke();
		}else{
			controller.renderText("签名校验失败,请检查参数...");
		}
	}
	
    private void configServer(Controller controller) {
    	String signature=controller.getPara("signature");
		String timestamp=controller.getPara("timestamp");
		String nonce=controller.getPara("nonce");
		String echostr=controller.getPara("echostr");
		String sha=EncryptUtil.checkSignature(timestamp, nonce);
	    if(sha.equals(signature)){
	    	controller.renderText(echostr);
	    }else{
	    	controller.renderText("签名校验失败");
	    }
	}
	/**
     * 签名校验
     * @param controller
     * @return
     */
	private boolean checkSignature(Controller controller){
		String signature=controller.getPara("signature");
		String timestamp=controller.getPara("timestamp");
		String nonce=controller.getPara("nonce");
		String echostr=controller.getPara("echostr");
	    String sha=EncryptUtil.checkSignature(timestamp, nonce);
	    if(sha.equals(signature)){
	    	controller.renderText(echostr);
	    	return true;
	    }else{
	    	controller.renderText("签名校验失败");
	    }
		return false;
	}
}
