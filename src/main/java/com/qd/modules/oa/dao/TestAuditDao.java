/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.oa.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.oa.entity.TestAudit;

/**
 * 审批DAO接口
 * 
 * @author Harry
 * @version 2014-05-16
 */
@MyBatisDao
public interface TestAuditDao extends CrudDao<TestAudit> {

	public TestAudit getByProcInsId(String procInsId);

	public int updateInsId(TestAudit testAudit);

	public int updateHrText(TestAudit testAudit);

	public int updateLeadText(TestAudit testAudit);

	public int updateMainLeadText(TestAudit testAudit);

}
