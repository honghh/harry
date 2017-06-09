package com.qd.modules.test.dao;

import com.qd.common.persistence.TreeDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.test.entity.TestTree;

/**
 * 树结构生成DAO接口
 * @author Harry
 * @version 2017-06-01
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}