/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.cms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qd.common.service.BaseService;
import com.qd.common.utils.DateUtils;
import com.qd.modules.cms.dao.ArticleDao;
import com.qd.modules.cms.entity.Category;
import com.qd.modules.cms.entity.Site;
import com.qd.modules.sys.entity.Office;

/**
 * 统计Service
 * 
 * @author Harry
 * @version 2013-05-21
 */
@Service
@Transactional(readOnly = true)
public class StatsService extends BaseService {

	@Autowired
	private ArticleDao articleDao;

	public List<Category> article(Map<String, Object> paramMap) {
		Category category = new Category();

		Site site = new Site();
		site.setId(Site.getCurrentSiteId());
		category.setSite(site);

		Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
		if (beginDate == null) {
			beginDate = DateUtils.setDays(new Date(), 1);
			paramMap.put("beginDate",
					DateUtils.formatDate(beginDate, "yyyy-MM-dd"));
		}
		category.setBeginDate(beginDate);
		Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
		if (endDate == null) {
			endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
			paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd"));
		}
		category.setEndDate(endDate);

		String categoryId = (String) paramMap.get("categoryId");
		if (categoryId != null && !("".equals(categoryId))) {
			category.setId(categoryId);
			category.setParentIds(categoryId);
		}

		String officeId = (String) (paramMap.get("officeId"));
		Office office = new Office();
		if (officeId != null && !("".equals(officeId))) {
			office.setId(officeId);
			category.setOffice(office);
		} else {
			category.setOffice(office);
		}

		List<Category> list = articleDao.findStats(category);
		return list;
	}

}
