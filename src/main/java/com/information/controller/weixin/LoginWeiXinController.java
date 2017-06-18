 package com.information.controller.weixin;

import java.net.URLEncoder;

import com.information.common.Constant;
import com.information.utils.HttpClientUtil;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

import net.sf.json.JSONObject;

/**
 * 微信授权登录
 * @author zengjintao
 * @version 1.0
 * 2017年4月10日 下午13:51
 */
@ControllerBind(controllerKey="/auth")
public class LoginWeiXinController extends Controller{

	public void index() throws Exception{
		String callback="http://china234.xicp.io/Information/auth/callback";
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Constant.WEIXIN_APPID
				+ "&redirect_uri="+URLEncoder.encode(callback,"UTF-8")
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo"
				+ "&state=STATE#wechat_redirect";
		redirect(url);
	}
	
	public void callback(){
		String code=getPara("code");
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Constant.WEIXIN_APPID
				+ "&secret="+Constant.WEIXIN_APPSECRET
				+ "&code="+code
				+ "&grant_type=authorization_code";
		String result=HttpClientUtil.httpGet(url);
		JSONObject json=JSONObject.fromObject(result);
		String accessToken=json.getString("access_token");
		String opendId=json.getString("openid");
		String info_url="https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken
				+ "&openid="+opendId
				+ "&lang=zh_CN";
		String user_info=HttpClientUtil.httpGet(info_url);
		if(user_info==null)
			renderHtml("授权失败");
		JSONObject session=JSONObject.fromObject(user_info);//获取微信用户信息
		setAttr("session", session);
		System.out.println(session);
		renderNull();
	}
}
