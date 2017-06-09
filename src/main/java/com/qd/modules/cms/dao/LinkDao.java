/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.cms.dao;

import java.util.List;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.cms.entity.Link;

/**
 * 链接DAO接口
 * 
 * @author Harry
 * @version 2013-8-23
 */
@MyBatisDao
public interface LinkDao extends CrudDao<Link> {

	public List<Link> findByIdIn(String[] ids);

	// {
	// return find("front Like where id in (:p1)", new Parameter(new
	// Object[]{ids}));
	// }

	public int updateExpiredWeight(Link link);
	// {
	// return
	// update("update Link set weight=0 where weight > 0 and weightDate < current_timestamp()");
	// }
	// public List<Link> fjindListByEntity();
}
