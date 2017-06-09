/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.gen.web;

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
import com.qd.common.utils.StringUtils;
import com.qd.common.web.BaseController;
import com.qd.modules.gen.entity.GenScheme;
import com.qd.modules.gen.service.GenSchemeService;
import com.qd.modules.gen.service.GenTableService;
import com.qd.modules.gen.util.GenUtils;
import com.qd.modules.sys.entity.User;
import com.qd.modules.sys.utils.UserUtils;

/**
 * 生成方案Controller
 * 
 * @author Harry
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genScheme")
public class GenSchemeController extends BaseController {

	@Autowired
	private GenSchemeService genSchemeService;

	@Autowired
	private GenTableService genTableService;

	@ModelAttribute
	public GenScheme get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return genSchemeService.get(id);
		} else {
			return new GenScheme();
		}
	}

	@RequiresPermissions("gen:genScheme:view")
	@RequestMapping(value = { "list", "" })
	public String list(GenScheme genScheme, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			genScheme.setCreateBy(user);
		}
		Page<GenScheme> page = genSchemeService.find(new Page<GenScheme>(
				request, response), genScheme);
		model.addAttribute("page", page);

		return "modules/gen/genSchemeList";
	}

	@RequiresPermissions("gen:genScheme:view")
	@RequestMapping(value = "form")
	public String form(GenScheme genScheme, Model model) {
		if (StringUtils.isBlank(genScheme.getPackageName())) {
			genScheme.setPackageName("com.qd.modules");
		}
		// if (StringUtils.isBlank(genScheme.getFunctionAuthor())){
		// genScheme.setFunctionAuthor(UserUtils.getUser().getName());
		// }
		model.addAttribute("genScheme", genScheme);
		model.addAttribute("config", GenUtils.getConfig());
		model.addAttribute("tableList", genTableService.findAll());
		return "modules/gen/genSchemeForm";
	}

	@RequiresPermissions(value={"gen:genScheme:edit","gen:genScheme:add"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(GenScheme genScheme, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, genScheme)) {
			return form(genScheme, model);
		}

		String result = genSchemeService.save(genScheme);
		addMessage(redirectAttributes, "操作生成方案'" + genScheme.getName()
				+ "'成功<br/>" + result);
		return "redirect:" + adminPath + "/gen/genScheme/?repage";
	}

	@RequiresPermissions("gen:genScheme:del")
	@RequestMapping(value = "delete")
	public String delete(GenScheme genScheme,
			RedirectAttributes redirectAttributes) {
		genSchemeService.delete(genScheme);
		addMessage(redirectAttributes, "删除生成方案成功");
		return "redirect:" + adminPath + "/gen/genScheme/?repage";
	}
	/**
	 * 批量删除生成方案
	 */
	@RequiresPermissions({"gen:genScheme:del"})
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/gen/genScheme/?repage";
		}
		String idArray[] =ids.split(",");
		for(String id : idArray){
			GenScheme genScheme = genSchemeService.get(id);
			genSchemeService.delete(genScheme);
		}
		addMessage(redirectAttributes, "删除业务表成功");
		return "redirect:" + adminPath + "/gen/genScheme/?repage";
	}

}
