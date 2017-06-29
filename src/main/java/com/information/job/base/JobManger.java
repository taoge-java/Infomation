package com.information.job.base;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import com.jfinal.log.Logger;
/**
 * 任务管理器
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年5月22日 上午10:19:16
 */
@Component
public class JobManger {

	public static final String DEFAULT_JOB="default_job";
	
	public static List<JobBuild> jobs=new ArrayList<JobBuild>();
	
	private static final Logger LOG=Logger.getLogger(JobManger.class);
	
	public void start(){
		try {
			Scheduler scheduler=new StdSchedulerFactory().getScheduler();
			for(JobBuild job:jobs){
				job.build(scheduler);
			}
			scheduler.start();
			LOG.info("任务启动成功");
		} catch (SchedulerException e) {
			LOG.error("job start error.........");
			e.printStackTrace();
		}
	}
}
