package com.qd.modules.sys.task;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.qd.modules.sys.entity.SysScheduleJob;
import com.qd.modules.sys.utils.ScheduleUtils;



public class QuartzJobFactory implements Job {
	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SysScheduleJob scheduleJob = (SysScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		ScheduleUtils.invokMethod(scheduleJob);
	}
}
