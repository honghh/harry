package cn.harry.sys.service;

import java.util.List;

/**
 * 后台用户角色和权限关系表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
 */
public interface SysRoleMenuService {

    /**
     * 根据菜单id 删除菜单关联角色信息
     * @param menuId
     */
    void deleteByMenuId(Long menuId);

    /**
     * 根据角色ID，获取菜单列表
     * @param roleId 角色id
     * @return 角色所拥有的菜单id列表
     */
    List<Long> listMenuIdByRoleId(Long roleId);
}

