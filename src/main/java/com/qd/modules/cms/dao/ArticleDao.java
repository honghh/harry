/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.cms.dao;

import java.util.List;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.cms.entity.Article;
import com.qd.modules.cms.entity.Category;

/**
 * 文章DAO接口
 * 
 * @author Harry
 * @version 2013-8-23
 */
@MyBatisDao
public interface ArticleDao extends CrudDao<Article> {

	public List<Article> findByIdIn(String[] ids);

	// {
	// return find("from Article where id in (:p1)", new Parameter(new
	// Object[]{ids}));
	// }

	public int updateHitsAddOne(String id);

	// {
	// return update("update Article set hits=hits+1 where id = :p1", new
	// Parameter(id));
	// }

	public int updateExpiredWeight(Article article);

	public List<Category> findStats(Category category);
	// {
	// return
	// update("update Article set weight=0 where weight > 0 and weightDate < current_timestamp()");
	// }

}
