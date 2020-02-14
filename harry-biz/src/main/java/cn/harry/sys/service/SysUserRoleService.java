package cn.harry.sys.service;

import cn.harry.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 后台用户和角色关系表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId 用户id
     * @return 角色id列表
     */
    List<Long> listRoleIdByUserId(Long userId);

    /**
     * 根据用户id 批量添加用户角色关系
     * @param userId
     * @param roleIdList
     */
    void insertUserAndUserRole(Long userId, List<Long> roleIdList);
}

