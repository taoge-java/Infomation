package com.information.constant;
/**
 * 常量类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月4日 下午9:56:19
 */
public class Constant {

	    public static final String ONE_MINUTE="one.minute";
	
	   //微信appid
		public static final String WEIXIN_APPID="wxa1e17ea1f1343e7a";
		//微信公众好密钥
		public static final String WEIXIN_APPSECRET="34dc94b4bfcf78d5cc2902f8d6f0541a";
		//服务器接入token
	    public static final String TOKEN="hello";
		
		public static final String MESSAGE_TEXT="text";
		
		public static final String MESSAGE_IMAGE="image";
		
		public static final String MESSAGE_VOICE="voice";
		
		public static final String MESSAGE_VIDEO="video";
		
		public static final String MESSAGE_LINK="link";
		
		public static final String MESSAGE_LOCATION="location";
		
		public static final String MESSAGE_EVENT="event";
		
		public static final String MESSAGE_SUBSCRIBE="subscribe";
		
		public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
		
		public static final String MESSAGE_CLICK="CLICK";
		
		public static final String MESSAGE_VIEW="VIEW";
		
		public static final String MESAGE_NEWS="news";
		/**模板测试id**/
		public static final String TEMPLATE_ID="GNYiG5lPy9Btz45nf7FVvS4Ifu4eC3lt9ZRptdAxra0";
		
		/**微信素材接口 **/
		public static final String UPLOAD_URL="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		
		/** 微信菜单创建接口**/
		public static final String CREATE_MENU="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		
		/**微信模板消息接口**/
		public static final String TEMPLATE_MESSAGE_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		
		public static final String URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		/**微信永久素材接口 **/
		public static final String FOREVER_UPLOAD_URL="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
}
