/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.sys.dao;

import java.util.List;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.sys.entity.Menu;

/**
 * 菜单DAO接口
 * 
 * @author Harry
 * @version 2014-05-16
 */
@MyBatisDao
public interface MenuDao extends CrudDao<Menu> {

	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByUserId(Menu menu);

	public int updateParentIds(Menu menu);

	public int updateSort(Menu menu);

}
