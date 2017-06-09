/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.cms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qd.common.service.CrudService;
import com.qd.modules.cms.dao.ArticleDataDao;
import com.qd.modules.cms.entity.ArticleData;

/**
 * 站点Service
 * 
 * @author Harry
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class ArticleDataService extends
		CrudService<ArticleDataDao, ArticleData> {

}
