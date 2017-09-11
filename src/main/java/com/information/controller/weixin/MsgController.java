 package com.information.controller.weixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.information.annotation.ControllerMapping;
import com.information.constant.WeiXinConstant;
import com.information.controller.base.BaseWeiXinController;
import com.information.dao.weixin.message.Articles;
import com.information.dao.weixin.message.Image;
import com.information.dao.weixin.message.TextMessage;
import com.information.dao.weixin.message.Voice;
import com.information.dao.weixin.message.response.ResponseImageMessage;
import com.information.dao.weixin.message.response.ResponseNewsMessage;
import com.information.dao.weixin.message.response.ResponseVoiceMessage;
import com.information.interceptor.WeiXinInterceptor;
import com.information.utils.XMLUtil;
import com.jfinal.aop.Before;
import com.jfinal.log.Log;
/**
 * 微信服务器接入唯一入口
 * @author zengjintao
 */
@ControllerMapping(url="/weixin")
public class MsgController extends BaseWeiXinController{
	
	private static Log LOG=Log.getLog(MsgController.class);
	
	
	/**
	 * 接收微信消息
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	@Before(WeiXinInterceptor.class)
	public void index() throws IOException{
	   Map<String,String> map=XMLUtil.xmlTomap(getRequest());
	   String fromUser=map.get("FromUserName");//关注者账号
	   String toUserName=map.get("ToUserName");//微信测试号
	   String msgType=map.get("MsgType");
	   if(WeiXinConstant.MESSAGE_TEXT.equals(msgType)){
		   //msgType=WeiXinConstant.CUSTOMER_SERVICE;
		   sendTextMessge(new TextMessage(),fromUser,toUserName,"亲,感谢您的关注！赶快开启您的购物之旅吧");
	   }
	   String Content=map.get("Content");
	   if(WeiXinConstant.MESSAGE_TEXT.equals(msgType)){//回复文本消息
		    sendTextMessge(new TextMessage(),fromUser,toUserName,"亲,感谢您的关注！赶快开启您的购物之旅吧");
	   }else if(WeiXinConstant.MESSAGE_IMAGE.equals(msgType)){//回复图片
		    sendImageMessage(new ResponseImageMessage(), fromUser, toUserName);
	   }else if(WeiXinConstant.MESSAGE_VOICE.equals(msgType)){
		   sendVoiceMessage(new ResponseVoiceMessage(),fromUser,toUserName);
	   }else if(WeiXinConstant.MESSAGE_EVENT.equals(msgType)){//微信事件推送
		    String eventType= map.get("Event");//获取事件推送类型
		   if(eventType.equals(WeiXinConstant.MESSAGE_SUBSCRIBE)){//如果是关注事件
			  sendTextMessge(new TextMessage(),fromUser,toUserName,"尊敬的用户您好,欢迎关注天下淘网络商城!更多便宜好货竟在天下淘商城!\n发送  “人工服务” 由美女客服为你服务!");
		   }else if(eventType.equals(WeiXinConstant.MESSAGE_UNSUBSCRIBE)){
			  LOG.info("用户取消了关注");
			  renderText("");
		   }else if(eventType.equals(WeiXinConstant.MESSAGE_CLICK)){
			  String key=map.get("EventKey");
			  if(key.equals("15")){//回复图文消息
				  sendGraphicMessage(new ResponseNewsMessage(),fromUser,toUserName);
			  }
		   }
	    }else if(WeiXinConstant.CUSTOMER_SERVICE.equals(msgType)){
	    	
	    }
	} 
    /**
     * 回复文本信息
     */
	@Override
	public void sendTextMessge(TextMessage text, String fromUser, String toUser,String content) {
		text.setFromUserName(toUser);
		text.setToUserName(fromUser);
		text.setMsgType(WeiXinConstant.MESSAGE_TEXT);
		text.setContent(content);
		text.setCreateTime(System.currentTimeMillis()+"");
		System.err.println(XMLUtil.messageToXml(text));
		renderText(XMLUtil.messageToXml(text));
	}

	/**
	 * 回复图片消息
	 */
	@Override
	public void sendImageMessage(ResponseImageMessage image, String fromUser,
			String toUser) throws IOException {
		image.setFromUserName(toUser);
		image.setToUserName(fromUser);
		image.setCreateTime(System.currentTimeMillis()+"");
		image.setMsgType(WeiXinConstant.MESSAGE_IMAGE);
		Image img=new Image();
		//String result=weiXinService.foreverUpload("C:/Users/Administrator/git/Stroe-WeiXin1/src/main/webapp/resources/image/1.jpg",weiXinService.getAccesstoken().getAccessToken(),"image",Constant.FOREVER_UPLOAD_URL);
		//String MediaId=JSONObject.fromObject(result).getString("media_id");
		img.setMediaId("nz1_eK05HelIM3aII3HXW7VCnKzMF2d0xocJ4uzgKqiJQx1vFmgsnanI-vJd60HF");
		image.setImage(img);
		System.err.println(XMLUtil.messageToXml(image));
		renderText(XMLUtil.messageToXml(image));
	}
	/**
	 * 回复图文消息
	 */
	@Override
	public void sendGraphicMessage(ResponseNewsMessage graphic, String fromUser,
			String toUser) {
		List<Articles> list=new ArrayList<Articles>();
		Articles articles=new Articles();
		articles.setDescription("1、绑定帐户，进入公众号，点击【我的帐户】菜单，进入绑定页面。\n2、开单通知，绑定物流公司帐户后，在物流公司开单，微信会接收开单通知信息。\n3、我的运单，点击【我的运单】菜单进入查看自己在物流公司的托运单，以及跟踪托运单的状态。");
		articles.setPicUrl("http://china234.xicp.io/Information/resources/image/001.jpg");
		articles.setTitle("天下淘网络商城购物流程");
		articles.setUrl("http://china234.xicp.io/Information/weixin/auth");
		list.add(articles);
		graphic.setCreateTime(System.currentTimeMillis()+"");
		graphic.setFromUserName(toUser);
		graphic.setToUserName(fromUser);
		graphic.setMsgType(WeiXinConstant.MESAGE_NEWS);
		graphic.setArticleCount(list.size());
		graphic.setArticles(list);
		System.err.println(XMLUtil.messageToXml(graphic));
		renderText(XMLUtil.messageToXml(graphic));
	}
	
	/**
	 * 回复语音
	 */
	@Override
	public void sendVoiceMessage(ResponseVoiceMessage responseVoice, String fromUser, String toUser) throws IOException {
		responseVoice.setFromUserName(toUser);
		responseVoice.setToUserName(fromUser);
		responseVoice.setMsgType(WeiXinConstant.MESSAGE_VOICE);
		Voice voice=new Voice();
		voice.setMediaId("CMwM0ocJt5It4Ag9jzjXDUYc-ckxux5FsfAydN59rZJCFzjImJYCEn12MlYBlv5o");
		responseVoice.setVoice(voice);
		responseVoice.setCreateTime(System.currentTimeMillis()+"");
		System.err.println(XMLUtil.messageToXml(responseVoice));
		renderText(XMLUtil.messageToXml(responseVoice));
	}
	
	/**
	 * 回复视频
	 */
//	@Override
//	public void sendVideoMessage(ResponseVideoMessage responseViedo, String fromUser, String toUser)
//			throws IOException {
//		
//	}
	
	/**
	 * 回复音乐
	 */
//	@Override
//	public void sendMusicMessage(ResponseMusicMessage responseMusic, String fromUser, String toUser)
//			throws IOException {
//		
//	}
}
