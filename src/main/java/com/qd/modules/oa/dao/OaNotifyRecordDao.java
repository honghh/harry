/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.oa.dao;

import java.util.List;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.oa.entity.OaNotifyRecord;

/**
 * 通知通告记录DAO接口
 * 
 * @author Harry
 * @version 2014-05-16
 */
@MyBatisDao
public interface OaNotifyRecordDao extends CrudDao<OaNotifyRecord> {

	/**
	 * 插入通知记录
	 * 
	 * @param oaNotifyRecordList
	 * @return
	 */
	public int insertAll(List<OaNotifyRecord> oaNotifyRecordList);

	/**
	 * 根据通知ID删除通知记录
	 * 
	 * @param oaNotifyId
	 *            通知ID
	 * @return
	 */
	public int deleteByOaNotifyId(String oaNotifyId);

}