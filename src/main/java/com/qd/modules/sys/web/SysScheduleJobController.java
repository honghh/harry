/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qd.modules.sys.web;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronScheduleBuilder;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qd.common.config.Global;
import com.qd.common.persistence.Page;
import com.qd.common.web.BaseController;
import com.qd.common.utils.SpringContextHolder;
import com.qd.common.utils.StringUtils;
import com.qd.modules.sys.entity.RetObj;
import com.qd.modules.sys.entity.SysScheduleJob;
import com.qd.modules.sys.service.SysScheduleJobService;

/**
 * 定时任务Controller
 * @author Harry
 * @version 2017-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysScheduleJob")
public class SysScheduleJobController extends BaseController {

	@Autowired
	private SysScheduleJobService sysScheduleJobService;
	
	@ModelAttribute
	public SysScheduleJob get(@RequestParam(required=false) String id) {
		SysScheduleJob entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysScheduleJobService.get(id);
		}
		if (entity == null){
			entity = new SysScheduleJob();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysScheduleJob:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysScheduleJob sysScheduleJob, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysScheduleJob> page = sysScheduleJobService.findPage(new Page<SysScheduleJob>(request, response), sysScheduleJob); 
		model.addAttribute("page", page);
		return "modules/sys/sysScheduleJobList";
	}

	@RequiresPermissions("sys:sysScheduleJob:view")
	@RequestMapping(value = "form")
	public String form(SysScheduleJob sysScheduleJob, Model model,RedirectAttributes redirectAttributes) {
		if(StringUtils.isNotEmpty(sysScheduleJob.getId())){
			SysScheduleJob job=sysScheduleJobService.get(sysScheduleJob.getId());
			if(job.getJobStatus().equals(SysScheduleJob.STATUS_RUNNING)){
				addMessage(redirectAttributes, "请先停止该定时任务，再执行修改操作");
				return "redirect:"+Global.getAdminPath()+"/sys/sysScheduleJob/?repage";
			}
		}
		model.addAttribute("sysScheduleJob", sysScheduleJob);
		return "modules/sys/sysScheduleJobForm";
	}

	@RequiresPermissions("sys:sysScheduleJob:edit")
	@RequestMapping(value = "save")
	public String save(SysScheduleJob sysScheduleJob, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysScheduleJob)){
			return form(sysScheduleJob, model,redirectAttributes);
		}
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysScheduleJob.getCronExpression());
		} catch (Exception e) {
			model.addAttribute("message", "cron表达式有误，不能被解析！");
			return form(sysScheduleJob, model,redirectAttributes);
		}
		Object obj = null;
		try {
			if (StringUtils.isNotBlank(sysScheduleJob.getSpringId())) {
				obj = SpringContextHolder.getBean(sysScheduleJob.getSpringId());
			} else {
				Class clazz = Class.forName(sysScheduleJob.getBeanClass());
				obj = clazz.newInstance();
			}
		} catch (Exception e) {
		}
		if (obj == null) {
			model.addAttribute("message", "未找到定时任务类");
			return form(sysScheduleJob, model,redirectAttributes);
		} else {
			Class clazz = obj.getClass();
			Method method = null;
			try {
				method = clazz.getMethod(sysScheduleJob.getMethodName(), null);
			} catch (Exception e) {
			}
			if (method == null) {
				model.addAttribute("message", "未找到定时任务方法");
				return form(sysScheduleJob, model,redirectAttributes);
			}
		}
		sysScheduleJobService.save(sysScheduleJob);
		addMessage(redirectAttributes, "保存定时任务成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysScheduleJob/?repage";
	}
	
	@RequiresPermissions("sys:sysScheduleJob:edit")
	@RequestMapping(value = "delete")
	public String delete(SysScheduleJob sysScheduleJob, RedirectAttributes redirectAttributes) {
		sysScheduleJob=sysScheduleJobService.get(sysScheduleJob.getId());
		if(sysScheduleJob.getJobStatus().equals(SysScheduleJob.STATUS_RUNNING)){
			addMessage(redirectAttributes, "请先停止该定时任务，再执行删除");
			return "redirect:"+Global.getAdminPath()+"/sys/sysScheduleJob/?repage";
		}
		sysScheduleJobService.delete(sysScheduleJob);
		addMessage(redirectAttributes, "删除定时任务成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysScheduleJob/?repage";
	}
	
	@RequiresPermissions("sys:sysScheduleJob:edit")
	@RequestMapping("changeJobStatus")
	@ResponseBody
	public RetObj changeJobStatus(HttpServletRequest request, String jobId, String cmd) {
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			sysScheduleJobService.changeStatus(jobId, cmd);
		} catch (SchedulerException e) {
			retObj.setMsg("任务状态改变失败！");
			return retObj;
		}
		retObj.setFlag(true);
		return retObj;
	}

}