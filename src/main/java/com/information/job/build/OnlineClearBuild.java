package com.information.job.build;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.information.job.base.BaseJob;
import com.information.job.base.JobBuild;
import com.information.job.base.JobManger;

public class OnlineClearBuild implements JobBuild{

	/**
	 * 单例模式
	 */
	static{
		JobManger.jobs.add(new OnlineClearBuild());
	}
	
	@Override
	public void build(Scheduler scheduler) throws SchedulerException {
		JobDetail Job=JobBuilder.newJob(BaseJob.class).withIdentity(BaseJob.class.getName(),JobManger.DEFAULT_JOB).build();
		Trigger trigger=TriggerBuilder.newTrigger()
		.forJob(Job.getKey())
		.startAt(new Date(System.currentTimeMillis()+10*1000))
		.withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(30)).build();
		//.withSchedule(CronScheduleBuilder.cronSchedule("0 58 09 * * ? ")).build();//每天10:56分执行
		scheduler.scheduleJob(Job, trigger);
	}

}
