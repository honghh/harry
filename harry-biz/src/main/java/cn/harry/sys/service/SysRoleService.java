package cn.harry.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.harry.sys.entity.SysRole;

/**
 * 后台用户角色表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页获取用户角色
     *
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<SysRole> getPage(String name, Integer pageSize, Integer pageNum);

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

}

