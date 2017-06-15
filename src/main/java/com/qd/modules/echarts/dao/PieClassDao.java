/**
 * Copyright &copy; 2015-2020 <a href="http://www.qd.org/">qd</a> All rights reserved.
 */
package com.qd.modules.echarts.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.echarts.entity.PieClass;

/**
 * 班级DAO接口
 * @author lgf
 * @version 2016-05-26
 */
@MyBatisDao
public interface PieClassDao extends CrudDao<PieClass> {

	
}