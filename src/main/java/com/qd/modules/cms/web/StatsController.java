/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.cms.web;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qd.common.web.BaseController;
import com.qd.modules.cms.entity.Category;
import com.qd.modules.cms.service.StatsService;

/**
 * 统计Controller
 * 
 * @author Harry
 * @version 2013-5-21
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/stats")
public class StatsController extends BaseController {

	@Autowired
	private StatsService statsService;

	/**
	 * 文章信息量
	 * 
	 * @param paramMap
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cms:stats:article")
	@RequestMapping(value = "article")
	public String article(@RequestParam Map<String, Object> paramMap,
			Model model) {
		List<Category> list = statsService.article(paramMap);
		model.addAttribute("list", list);
		model.addAttribute("paramMap", paramMap);
		return "modules/cms/statsArticle";
	}

}
