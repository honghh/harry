/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.gen.dao;

import java.util.List;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.gen.entity.GenTable;
import com.qd.modules.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * 
 * @author Harry
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenDataBaseDictDao extends CrudDao<GenTableColumn> {

	/**
	 * 查询表列表
	 * 
	 * @param genTable
	 * @return
	 */
	List<GenTable> findTableList(GenTable genTable);

	/**
	 * 获取数据表字段
	 * 
	 * @param genTable
	 * @return
	 */
	List<GenTableColumn> findTableColumnList(GenTable genTable);

	/**
	 * 获取数据表主键
	 * 
	 * @param genTable
	 * @return
	 */
	List<String> findTablePK(GenTable genTable);

}
