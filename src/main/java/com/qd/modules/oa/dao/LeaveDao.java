/**
 * There are <a href="https://github.com/jeeqd">jeeqd</a> code generation
 */
package com.qd.modules.oa.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.oa.entity.Leave;

/**
 * 请假DAO接口
 * 
 * @author liuj
 * @version 2013-8-23
 */
@MyBatisDao
public interface LeaveDao extends CrudDao<Leave> {

	/**
	 * 更新流程实例ID
	 * 
	 * @param leave
	 * @return
	 */
	public int updateProcessInstanceId(Leave leave);

	/**
	 * 更新实际开始结束时间
	 * 
	 * @param leave
	 * @return
	 */
	public int updateRealityTime(Leave leave);

}
