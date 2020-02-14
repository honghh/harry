package cn.harry.sys.dao;

import cn.harry.sys.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户角色和权限关系表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {
    /**
     * 根据角色id 批量添加角色与菜单关系
     * @param roleId
     * @param menuIdList
     */
    void insertRoleAndRoleMenu(@Param("roleId") Long roleId, @Param("menuIdList") List<Long> menuIdList);
}
