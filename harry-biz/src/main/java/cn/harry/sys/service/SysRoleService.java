package cn.harry.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.harry.sys.entity.SysRole;

import java.util.List;

/**
 * 后台用户角色表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页获取用户角色
     *
     * @param sysRole
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<SysRole> getPage(SysRole sysRole, Integer pageSize, Integer pageNum);

    /**
     * 创建
     *
     * @param sysRole
     * @return
     */
    int create(SysRole sysRole);

    /**
     * 更新
     *
     * @param id
     * @param sysRole
     * @return
     */
    int update(Long id, SysRole sysRole);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 批量删除角色信息
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改数据权限信息
     *
     * @param id
     * @param sysRole
     * @return
     */
    int dataScope(Long id, SysRole sysRole);

    /**
     * 修改角色状态
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateRoleStatus(SysRole role);

    /**
     * 获取所有的角色列表
     *
     * @return
     */
    List<SysRole> getListAll();
}

