package com.qd.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qd.test.base.BaseTest;

/**
 * 
 * @author Harry
 *
 */
public class ActivitiTest extends BaseTest {

	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FormService formService;
	@Autowired
	private HistoryService historyService;

	/** 查询部署对象信息，对应表（act_re_deployment） */
	@Test
	public void findDeploymentListTest() {
		List<Deployment> list = repositoryService.createDeploymentQuery()// 创建部署对象查询
				.orderByDeploymenTime().asc()//
				.list();
		for (Deployment deployment : list) {
			System.out.println(deployment.toString());
		}
	}

	/** 查询部署对象信息，对应表（act_re_deployment） */
	@Test
	public void startProcess() {
		String key = "Test";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyUserId", "applyUserId");
		runtimeService.startProcessInstanceByKey(key, map);
	}

	/**
	 * 完成我的个人任务
	 */
	@Test
	public void completeMyTask() {
		String taskId = "4202";
		taskService.complete(taskId);

	}

}
