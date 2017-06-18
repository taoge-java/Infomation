package com.information.job.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.information.common.Constant;
import com.information.dao.weixin.template.TemplateData;
import com.information.dao.weixin.template.TemplateMsg;
import com.information.job.base.BaseJob;
import com.information.model.weixin.WeiXinUser;
import com.information.service.weixin.WeiXinService;

public class OnlineSendJob extends BaseJob{
	
	@Autowired
	private WeiXinService weiXinService;
	
	@Override
	public void execute(JobExecutionContext context) 
			throws JobExecutionException {
		List<WeiXinUser> list=WeiXinUser.dao.find("select * from weixin_user");
		for(WeiXinUser user:list){
			TemplateMsg templateMsg=new TemplateMsg();
			Map<String,TemplateData> map=new HashMap<String,TemplateData>();
			TemplateData first = new TemplateData();  
			first.setColor("#000000");    
			first.setValue("您的户外旅行活动订单已经支付完成，可在我的个人中心中查看。");
			map.put("first", first);
			TemplateData keyword1 = new TemplateData();    
			keyword1.setColor("#000000");    
			keyword1.setValue("山亚三天游 ");    
			map.put("keyword1", keyword1);  
			  
			TemplateData keyword2 = new TemplateData();    
			keyword2.setColor("#000000");    
			keyword2.setValue("5000元");    
			map.put("keyword2", keyword2);  
			  
			TemplateData keyword3 = new TemplateData();    
			keyword3.setColor("#000000");    
			keyword3.setValue("2017.1.2");    
			map.put("keyword3", keyword3);  
			  
			TemplateData keyword4 = new TemplateData();    
			keyword4.setColor("#000000");    
			keyword4.setValue("5");    
			map.put("keyword4", keyword4);  
			  
			TemplateData remark = new TemplateData();    
			remark.setColor("#000000");    
			remark.setValue("请届时携带好身份证件准时到达集合地点，若临时退改将产生相应损失，敬请谅解,谢谢！");    
			map.put("remark", remark);  
			templateMsg.setData(map);
			templateMsg.setTouser(user.getStr("openid"));
			templateMsg.setTemplate_id(Constant.TEMPLATE_ID);
			templateMsg.setUrl("http://www.baidu.com");
			templateMsg.setTopcolor("#fff");
			weiXinService.sendMessage(templateMsg);
		}
	}
}
