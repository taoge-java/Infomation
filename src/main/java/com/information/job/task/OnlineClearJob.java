package com.information.job.task;


import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.information.dao.OnlineManger;
import com.information.dao.UserSession;
import com.information.job.base.BaseJob;
import com.information.spring.SpringBeanManger;
import com.jfinal.log.Log;
/**
 * 在线任务清理
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月4日 下午9:40:15
 */
public class OnlineClearJob extends BaseJob{

	private Log LOG=Log.getLog(OnlineClearJob.class);
	
	@Override
	public void execute(JobExecutionContext context) 
			throws JobExecutionException {
		OnlineManger onlineManger=(OnlineManger) SpringBeanManger.getBean("onlineManger");
		List<UserSession> list=onlineManger.getAllUserSession();
		/**
		 * 30分钟无任何操作则剔除用户
		 */
		for(UserSession userSession:list){
		   if(System.currentTimeMillis()-userSession.getHeartTime()>60*1000*30){
			   onlineManger.remove(userSession);
			   LOG.warn("用户心跳超时，强行剔除["+userSession.toString()+"]");
		   }
		}
	}

}
