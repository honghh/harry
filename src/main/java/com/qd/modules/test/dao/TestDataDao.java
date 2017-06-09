package com.qd.modules.test.dao;

import java.util.List;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.test.entity.TestData;

/**
 * 单表生成DAO接口
 * @author Harry
 * @version 2017-06-01
 */
@MyBatisDao
public interface TestDataDao extends CrudDao<TestData> {
	/**
	 * 功能：批量删除
	 */
	void deleteAll(List<String> idList);
	
}