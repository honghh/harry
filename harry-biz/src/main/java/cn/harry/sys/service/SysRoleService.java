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

    IPage<SysRole> getPage(String name, Integer pageSize, Integer pageNum);

    int create(SysRole sysRole);

    int update(Long id, SysRole sysRole);

    int delete(Long id);

}

