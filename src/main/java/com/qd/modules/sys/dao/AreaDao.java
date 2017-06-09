/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.sys.dao;

import com.qd.common.persistence.TreeDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * 
 * @author Harry
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {

}
