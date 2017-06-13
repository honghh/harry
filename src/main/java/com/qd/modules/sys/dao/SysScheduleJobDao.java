package com.qd.modules.sys.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.sys.entity.SysScheduleJob;

/**
 * 定时任务DAO接口
 * @author Harry
 * @version 2017-05-31
 */
@MyBatisDao
public interface SysScheduleJobDao extends CrudDao<SysScheduleJob> {
	
}