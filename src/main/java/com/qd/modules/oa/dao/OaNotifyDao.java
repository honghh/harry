/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.oa.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.oa.entity.OaNotify;

/**
 * 通知通告DAO接口
 * 
 * @author Harry
 * @version 2014-05-16
 */
@MyBatisDao
public interface OaNotifyDao extends CrudDao<OaNotify> {

	/**
	 * 获取通知数目
	 * 
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(OaNotify oaNotify);

}