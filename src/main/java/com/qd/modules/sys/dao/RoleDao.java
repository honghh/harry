/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.modules.sys.dao;

import com.qd.common.persistence.CrudDao;
import com.qd.common.persistence.annotation.MyBatisDao;
import com.qd.modules.sys.entity.Role;

/**
 * 角色DAO接口
 * 
 * @author Harry
 * @version 2013-12-05
 */
@MyBatisDao
public interface RoleDao extends CrudDao<Role> {

	public Role getByName(Role role);

	public Role getByEnname(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * 
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);

	/**
	 * 维护角色与公司部门关系
	 * 
	 * @param role
	 * @return
	 */
	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);

}
