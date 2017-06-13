package com.qd.modules.sys.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.qd.common.utils.SpringContextHolder;
import com.qd.modules.sys.entity.SysScheduleJob;

/**
 * 定时任务工具类
 * @author Harry
 * 
 */
public class ScheduleUtils {
	public final static Logger log = Logger.getLogger(ScheduleUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(SysScheduleJob sysScheduleJob) {
		Object object = null;
		Class clazz = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(sysScheduleJob.getSpringId())) {
			object = SpringContextHolder.getBean(sysScheduleJob.getSpringId());
		} else if (org.apache.commons.lang3.StringUtils.isNotBlank(sysScheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(sysScheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (object == null) {
			log.error("任务名称 = [" + sysScheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(sysScheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + sysScheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("任务名称 = [" + sysScheduleJob.getJobName() + "]----------启动成功");
	}
}
