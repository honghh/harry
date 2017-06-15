/**
 * Copyright &copy; 2015-2020 <a href="http://www.qd.org/">qd</a> All rights reserved.
 */
package com.qd.modules.echarts.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.echarts.entity.ChinaWeatherDataBean;

/**
 * 城市气温DAO接口
 * @author lgf
 * @version 2016-06-02
 */
@MyBatisDao
public interface ChinaWeatherDataBeanDao extends CrudDao<ChinaWeatherDataBean> {

	
}