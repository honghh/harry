package cn.harry.sys.service;

import cn.harry.sys.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 后台用户角色和权限关系表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据菜单id 删除菜单关联角色信息
     *
     * @param menuId
     */
    void deleteByMenuId(Long menuId);

    /**
     * 根据角色ID，获取菜单列表
     *
     * @param roleId 角色id
     * @return 角色所拥有的菜单id列表
     */
    List<Long> listMenuIdByRoleId(Long roleId);

    /**
     * 查询菜单使用数量
     *
     * @param menuId
     * @return
     */
    int checkMenuExistRole(Long menuId);

    /**
     * 根据角色id 批量添加角色与菜单关系
     * @param id
     * @param menuIds
     */
    void insertRoleAndRoleMenu(Long id, List<Long> menuIds);
}

