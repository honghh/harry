package com.qd.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qd.common.config.Global;
import com.qd.common.persistence.Page;
import com.qd.common.web.BaseController;
import com.qd.common.utils.StringUtils;
import com.qd.modules.test.entity.TestData;
import com.qd.modules.test.service.TestDataService;

/**
 * 单表生成Controller
 * @author Harry
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testData")
public class TestDataController extends BaseController {

	@Autowired
	private TestDataService testDataService;
	
	@ModelAttribute
	public TestData get(@RequestParam(required=false) String id) {
		TestData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testDataService.get(id);
		}
		if (entity == null){
			entity = new TestData();
		}
		return entity;
	}
	
	/**
	 * 单表生成列表页面
	 */
	@RequiresPermissions("test:testData:list")
	@RequestMapping(value = {"list", ""})
	public String list(TestData testData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestData> page = testDataService.findPage(new Page<TestData>(request, response), testData); 
		model.addAttribute("page", page);
		return "modules/test/testDataList";
	}
	
	/**
	 * 查看，增加，编辑单表生成表单页面
	 */
	@RequiresPermissions(value={"test:testData:view","test:testData:add","test:testData:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(TestData testData, Model model) {
		model.addAttribute("testData", testData);
		return "modules/test/testDataForm";
	}
	
	/**
	 * 保存单表生成
	 */
	@RequiresPermissions(value={"test:testData:add","test:testData:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(TestData testData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testData)){
			return form(testData, model);
		}
		testDataService.save(testData);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testData/?repage";
	}
	
	/**
	 * 删除单表生成
	 */
	@RequiresPermissions("test:testData:del")
	@RequestMapping(value = "delete")
	public String delete(TestData testData, RedirectAttributes redirectAttributes) {
		testDataService.delete(testData);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testData/?repage";
	}
		
	/**
	 * 批量删除单表生成
	 */	
	@RequiresPermissions("test:testData:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		testDataService.deleteAll(ids);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testData/?repage";
	}

}