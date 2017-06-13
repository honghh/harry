package com.qd.modules.sys.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.sys.entity.SystemConfig;

/**
 * 系统配置DAO接口
 * @author Harry
 * @version 2016-02-07
 */
@MyBatisDao
public interface SystemConfigDao extends CrudDao<SystemConfig> {
	
}