/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.sys.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * 
 * @author Harry
 * @version 2017-04-07
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

	/**
	 * 删除全部数据
	 */
	public void empty();
}
