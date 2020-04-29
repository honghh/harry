package cn.harry.sys.service;

import cn.harry.sys.entity.SysDept;
import cn.harry.sys.vo.TreeSelect;

import java.util.List;
import java.util.Map;

/**
 * 部门表
 *
 * @author honghh
 * Date 2020-03-16 08:51:37
 * Copyright (C) www.tech-harry.cn
 */
public interface SysDeptService {


    /**
     * 新增部门信息
     *
     * @param sysDept
     * @return
     */
    int create(SysDept sysDept);

    /**
     * 修改部门状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 修改部门信息
     *
     * @param id
     * @param sysDept
     * @return
     */
    int update(Long id, SysDept sysDept);

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 查询部门管理数据
     *
     * @param params 部门信息
     * @return 部门信息集合
     */
    List<SysDept> selectDeptList(Map<String, Object> params);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    List<Integer> selectDeptListByRoleId(Long roleId);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts);

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    List<SysDept> buildDeptTree(List<SysDept> depts);

    /**
     * 根据部门编号获取详细信息
     *
     * @param id
     * @return
     */
    SysDept selectById(Long id);

    /**
     * 获取子部门ID，用于数据过滤
     *
     * @param deptId
     * @return
     */
    List<Long> getSubDeptIdList(Long deptId);
}

