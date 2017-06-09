/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.cms.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.cms.entity.Site;

/**
 * 站点DAO接口
 * 
 * @author Harry
 * @version 2013-8-23
 */
@MyBatisDao
public interface SiteDao extends CrudDao<Site> {

}
