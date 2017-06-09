/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.gen.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.gen.entity.GenScheme;

/**
 * 生成方案DAO接口
 * 
 * @author Harry
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenSchemeDao extends CrudDao<GenScheme> {

}
