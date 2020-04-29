package cn.harry.sys.service;

import cn.harry.sys.entity.SysRoleDept;

import java.util.List;

/**
 * 角色部门关联
 *
 * @author honghh
 * Date 2020-03-16 08:51:37
 * Copyright (C) www.tech-harry.cn
 */
public interface SysRoleDeptService {

    /**
     * 通过角色ID删除角色和部门关联
     *
     * @param roleId
     * @return
     */
    int deleteRoleDeptByRoleId(Long roleId);

    /**
     * 批量新增角色部门信息
     *
     * @param list
     * @return
     */
    int batchRoleDept(List<SysRoleDept> list);

    /**
     * 根据角色ID，获取部门ID列表
     * @param roleIdList
     * @return
     */
    List<Long> queryDeptIdList(List<Long> roleIdList);
}

