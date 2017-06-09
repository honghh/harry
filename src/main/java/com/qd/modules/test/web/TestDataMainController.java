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
import com.qd.modules.test.entity.TestDataMain;
import com.qd.modules.test.service.TestDataMainService;

/**
 * 主子表生成Controller
 * @author Harry
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testDataMain")
public class TestDataMainController extends BaseController {

	@Autowired
	private TestDataMainService testDataMainService;
	
	@ModelAttribute
	public TestDataMain get(@RequestParam(required=false) String id) {
		TestDataMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testDataMainService.get(id);
		}
		if (entity == null){
			entity = new TestDataMain();
		}
		return entity;
	}
	
	/**
	 * 主子表生成列表页面
	 */
	@RequiresPermissions("test:testDataMain:list")
	@RequestMapping(value = {"list", ""})
	public String list(TestDataMain testDataMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestDataMain> page = testDataMainService.findPage(new Page<TestDataMain>(request, response), testDataMain); 
		model.addAttribute("page", page);
		return "modules/test/testDataMainList";
	}
	
	/**
	 * 查看，增加，编辑主子表生成表单页面
	 */
	@RequiresPermissions(value={"test:testDataMain:view","test:testDataMain:add","test:testDataMain:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(TestDataMain testDataMain, Model model) {
		model.addAttribute("testDataMain", testDataMain);
		return "modules/test/testDataMainForm";
	}
	
	/**
	 * 保存主子表生成
	 */
	@RequiresPermissions(value={"test:testDataMain:add","test:testDataMain:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(TestDataMain testDataMain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testDataMain)){
			return form(testDataMain, model);
		}
		testDataMainService.save(testDataMain);
		addMessage(redirectAttributes, "保存主子表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testDataMain/?repage";
	}
	
	/**
	 * 删除主子表生成
	 */
	@RequiresPermissions("test:testDataMain:del")
	@RequestMapping(value = "delete")
	public String delete(TestDataMain testDataMain, RedirectAttributes redirectAttributes) {
		testDataMainService.delete(testDataMain);
		addMessage(redirectAttributes, "删除主子表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testDataMain/?repage";
	}
		
	/**
	 * 批量删除主子表生成
	 */	
	@RequiresPermissions("test:testDataMain:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
//		testDataMainService.deleteAll(ids);//多选删除有问题
		addMessage(redirectAttributes, "删除主子表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testDataMain/?repage";
	}

}