package com.information.job.base;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
/**
 * 任务执行调度接口
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年5月22日 上午10:18:44
 */
public interface JobBuild {

	public void build (Scheduler scheduler)
			throws SchedulerException;
}
