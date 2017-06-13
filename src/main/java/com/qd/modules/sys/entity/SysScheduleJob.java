package com.qd.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.qd.common.persistence.DataEntity;


/**
 * 定时任务Entity
 * @author
 * @version 
 */
public class SysScheduleJob extends DataEntity<SysScheduleJob> {
	
	private static final long serialVersionUID = 1L;
	private String jobName;		// 任务名称
	private String jobGroup;		// 任务分组
	private String jobStatus;		// 任务状态
	private String cronExpression;		// cron表达式
	private String description;		// 描述
	private String beanClass;		// 执行class类
	private String isConcurrent;		// 是否同步
	private String springId;		// spring_id
	private String methodName;		// 方法名称
	public static final String STATUS_RUNNING = "1";//启用
	public static final String STATUS_NOT_RUNNING = "0";//暂停
	
	public static final String CONCURRENT_IS = "1";//异步
	public static final String CONCURRENT_NOT = "0";//同步
	
	public SysScheduleJob() {
		super();
	}

	public SysScheduleJob(String id){
		super(id);
	}

	@Length(min=0, max=255, message="任务名称长度必须介于 0 和 255 之间")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Length(min=0, max=255, message="任务分组长度必须介于 0 和 255 之间")
	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	@Length(min=0, max=255, message="任务状态长度必须介于 0 和 255 之间")
	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	@Length(min=0, max=255, message="cron表达式长度必须介于 0 和 255 之间")
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="执行class类长度必须介于 0 和 255 之间")
	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	
	@Length(min=0, max=255, message="是否同步长度必须介于 0 和 255 之间")
	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	
	@Length(min=0, max=255, message="spring_id长度必须介于 0 和 255 之间")
	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}
	
	@Length(min=0, max=255, message="方法名称长度必须介于 0 和 255 之间")
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}