/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.test.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.test.entity.Test;

/**
 * 测试DAO接口
 * 
 * @author Harry
 * @version 2013-10-17
 */
@MyBatisDao
public interface TestDao extends CrudDao<Test> {

}
