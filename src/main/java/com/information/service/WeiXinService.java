package com.information.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.information.common.Constant;
import com.information.dao.weixin.AccessToken;
import com.information.dao.weixin.Menu;
import com.information.dao.weixin.base.BaseButton;
import com.information.dao.weixin.event.ClickButton;
import com.information.dao.weixin.event.ViewButton;
import com.information.dao.weixin.template.TemplateMsg;
import com.information.utils.HttpClientUtil;
import com.jfinal.log.Logger;

import net.sf.json.JSONObject;
/**
 * 微信公众号服务接口
 * @author zengjintao
 * @version 1.0
 * 2017年4月9日上午19:51
 */
public class WeiXinService {

	private static final Logger LOG=Logger.getLogger(WeiXinService.class);
	
	/**
	 * 获取AccessToken
	 * @return
	 */
	public  AccessToken  getAccesstoken(){
		AccessToken accessToken=new AccessToken();
		String url=Constant.URL.replace("APPID",Constant.WEIXIN_APPID).replace("APPSECRET", Constant.WEIXIN_APPSECRET);
		String result=HttpClientUtil.httpGet(url);
		JSONObject json=JSONObject.fromObject(result);
		if(json!=null){
			accessToken.setAccessToken(json.getString("access_token"));
			accessToken.setTime_out(json.getInt("expires_in"));
		}
		return accessToken;
	}
	
	/**
	 * 发送模板消息
	 */
	public  void sendMessage(TemplateMsg msg){
		JSONObject json=JSONObject.fromObject(msg);
		String request_url=Constant.TEMPLATE_MESSAGE_URL.replace("ACCESS_TOKEN", this.getAccesstoken().getAccessToken());
	    String result= HttpClientUtil.httpPost(request_url, json.toString());
	    if(result!=null){
	    	JSONObject jsonObject=JSONObject.fromObject(result);
	    	if(jsonObject.getInt("errcode")==0){
	    		LOG.info("消息发送成功。。。。");
	    	}else{
	    		LOG.error("消息发送异常。。。。");
	    	}
	    }
	}
	
	/**
	 * 创建菜单     调用微信创建菜单接口
	 * @param access_token
	 * @param menu
	 * @return
	 */
	public  int createMenu(String  access_token,String menu){
		int count=0;
		String url=Constant.CREATE_MENU.replace("ACCESS_TOKEN", access_token);
	    String result=HttpClientUtil.httpPost(url, menu);
	    JSONObject json=JSONObject.fromObject(result);
	    if(json!=null){
	    	count=json.getInt("errcode");
	    }
	    return count;
	}
	
	/**
	 * 生成微信公众号菜单
	 */
	public  Menu generateMenu(){
		ClickButton click1=new ClickButton();
		click1.setName("新品上市");
		click1.setType("click");
		click1.setKey("15");
		
		ClickButton click2=new ClickButton();
		click2.setName("天下淘商城");
		click2.setType("click");
		click2.setKey("2");
		
		ViewButton sub_click1=new ViewButton();
		sub_click1.setName("个人中心");
		sub_click1.setType("view");
		sub_click1.setUrl("http://china234.xicp.io/Information/auth");
		
		ClickButton sub_click2=new ClickButton();
		sub_click2.setName("已支付订单");
		sub_click2.setType("location_select");
		sub_click2.setKey("32");
		
		BaseButton base=new BaseButton();
		base.setName("我的账户");
		base.setSub_button(new BaseButton[]{sub_click1,sub_click2});
		Menu menu=new Menu();
		menu.setButton(new BaseButton[]{click1,click2,base});
		return menu;
	}
	
	
	/**
	 * 微信图片上传
	 * @param file_path
	 * @param access_token
	 * @param type
	 * @param uploadUrl
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public String  uploadImg(String file_path,String access_token,String type,String uploadUrl) throws IOException{
		File file=new File(file_path);
		if(!file.exists()||!file.isFile()){
			throw new IOException("file is not to exist");
		}
		String upload_url=uploadUrl.replace("ACCESS_TOKEN", access_token).replace("TYPE", type);
		URL url=new URL(upload_url);
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
	    connection.setDoInput(true);
	    connection.setDoOutput(true);
	    connection.setUseCaches(false);//设置不要缓存
	    connection.setRequestProperty("Connection", "Keep-Alive");
	    connection.setRequestProperty("charset", "utf-8");
	    String  BOUNDARY="--------------"+System.currentTimeMillis();
	    //boundary就是request头和上传文件内容的分隔符  
	    connection.setRequestProperty("Content-type", "multipart/form-data;boundary="+BOUNDARY);
	    StringBuilder sb=new StringBuilder();
	    sb.append("--");
	    sb.append(BOUNDARY);
	    sb.append("\r\n");
	    sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""  
        + file.getName() + "\"\r\n");  
        sb.append("Content-Type:application/octet-stream\r\n\r\n");  
        System.out.println(sb.toString());
        byte[] by= sb.toString().getBytes("UTF-8");
      //获取输出流
	    OutputStream out=connection.getOutputStream();
	    //输出表头
	    out.write(by);
	    /**
	     * 文件正文部分
	     */
	    DataInputStream data=new DataInputStream(new FileInputStream(file));
	    int i=0;
	    byte[] bytes=new byte[1024];
	    while((i=data.read(bytes, 0, 1024))!=-1){
	    	out.write(bytes, 0, i);
	    }
	    if(data!=null){
	    	data.close();
	    }
	    /**
	     * 文件结尾部分
	     */
	    byte[] foot = ("\r\n--" +BOUNDARY+ "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
	    out.write(foot);
	    out.flush();
	    out.close();
	    StringBuffer buffer=new StringBuffer();
	    BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String line=null;
	    if((line=reader.readLine())!=null){
	    	buffer.append(line);
	    }
	    reader.close();
	    if(buffer!=null)
		   return buffer.toString();
	    return null;
	}
}
