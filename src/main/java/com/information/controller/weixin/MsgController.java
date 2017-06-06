 package com.information.controller.weixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.information.common.Constant;
import com.information.controller.base.BaseWeiXinController;
import com.information.dao.weixin.message.Articles;
import com.information.dao.weixin.message.Image;
import com.information.dao.weixin.message.TextMessage;
import com.information.dao.weixin.message.response.ResponseImageMessage;
import com.information.dao.weixin.message.response.ResponseNewsMessage;
import com.information.interceptor.WeiXinInterceptor;
import com.information.utils.XMLUtil;
import com.jfinal.aop.Before;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Log;
/**
 * 微信服务器接入唯一入口
 * @author zengjintao
 */
@ControllerBind(controllerKey="/weixin")
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
	   String Content=map.get("Content");
	//   String CreateTime=map.get("CreateTime");
	   if(Constant.MESSAGE_TEXT.equals(msgType)){//回复文本消息
		    sendTextMessge(new TextMessage(),fromUser,toUserName,"亲,感谢您的关注！赶快开启您的购物之旅吧");
	   }else if(Constant.MESSAGE_IMAGE.equals(msgType)){//回复图片
		    sendImageMessage(new ResponseImageMessage(), fromUser, toUserName);
	   }else if(Constant.MESSAGE_EVENT.equals(msgType)){//微信事件推送
		    String eventType= map.get("Event");//获取事件推送类型
		   if(eventType.equals(Constant.MESSAGE_SUBSCRIBE)){//如果是关注事件
			  sendTextMessge(new TextMessage(),fromUser,toUserName,"尊敬的用户您好,欢迎关注天下淘网络商城!更多便宜好货竟在天下淘商城!\n发送  “人工服务” 由美女客服为你服务!");
		   }else if(eventType.equals(Constant.MESSAGE_UNSUBSCRIBE)){
			  LOG.info("用户取消了关注");
			  renderText("");
		   }else if(eventType.equals(Constant.MESSAGE_CLICK)){
			  String key=map.get("EventKey");
			  if(key.equals("15")){//回复图文消息
				  sendGraphicMessage(new ResponseNewsMessage(),fromUser,toUserName);
			  }
		   }
	    }
	} 
    /**
     * 回复文本信息
     */
	@Override
	public void sendTextMessge(TextMessage text, String fromUser, String toUser,String content) {
		text.setFromUserName(toUser);
		text.setToUserName(fromUser);
		text.setMsgType(Constant.MESSAGE_TEXT);
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
		image.setMsgType(Constant.MESSAGE_IMAGE);
		Image img=new Image();
		//String result=weiXinService.foreverUpload("C:/Users/Administrator/git/Stroe-WeiXin1/src/main/webapp/resources/image/1.jpg",weiXinService.getAccesstoken().getAccessToken(),"image",Constant.FOREVER_UPLOAD_URL);
		//String MediaId=JSONObject.fromObject(result).getString("media_id");
		img.setMediaId("Ryb73rUpmkG3bjOpXmnC-Lc8q906I9Ey4AbSIt2zyiwT51bV6zejQaq-LkHBnwti");
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
		articles.setPicUrl("http://47.94.12.108/Stroe-WeiXin/resources/image/1.jpg");
		articles.setTitle("天下淘网络商城购物流程");
		articles.setUrl("http://47.94.12.108/Stroe-WeiXin/weixin/auth");
		list.add(articles);
		graphic.setCreateTime(System.currentTimeMillis()+"");
		graphic.setFromUserName(toUser);
		graphic.setToUserName(fromUser);
		graphic.setMsgType(Constant.MESAGE_NEWS);
		graphic.setArticleCount(list.size());
		graphic.setArticles(list);
		System.err.println(XMLUtil.messageToXml(graphic));
		renderText(XMLUtil.messageToXml(graphic));
	}
//	//发送模板消息
//	public void sendTemplate(String toUser){
//		TemplateMsg templateMsg=new TemplateMsg();
// 		
//		Map<String,TemplateData> map=new HashMap<String,TemplateData>();
//		TemplateData first = new TemplateData();  
//		first.setColor("#000000");    
//		first.setValue("您的户外旅行活动订单已经支付完成，可在我的个人中心中查看。");
//		map.put("first", first);
//		TemplateData keyword1 = new TemplateData();    
//		keyword1.setColor("#000000");    
//		keyword1.setValue("1.2发现尼泊尔—人文与自然的旅行圣地 ");    
//		map.put("keyword1", keyword1);  
//		  
//		TemplateData keyword2 = new TemplateData();    
//		keyword2.setColor("#000000");    
//		keyword2.setValue("5000元");    
//		map.put("keyword2", keyword2);  
//		  
//		TemplateData keyword3 = new TemplateData();    
//		keyword3.setColor("#000000");    
//		keyword3.setValue("2017.1.2");    
//		map.put("keyword3", keyword3);  
//		  
//		TemplateData keyword4 = new TemplateData();    
//		keyword4.setColor("#000000");    
//		keyword4.setValue("5");    
//		map.put("keyword4", keyword4);  
//		  
//		TemplateData remark = new TemplateData();    
//		remark.setColor("#000000");    
//		remark.setValue("请届时携带好身份证件准时到达集合地点，若临时退改将产生相应损失，敬请谅解,谢谢！");    
//		map.put("remark", remark);  
//		templateMsg.setData(map);
//		templateMsg.setTouser(toUser);
//		templateMsg.setTemplate_id(Constant.TEMPLATE_ID);
//		templateMsg.setUrl("http://www.baidu.com");
//		templateMsg.setTopcolor("#fff");
//		weiXinService.sendMessage(templateMsg);
//		renderText("");
//	}
}
