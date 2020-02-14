package cn.harry.sys.service;

import cn.harry.sys.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 后台用户权限表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 根据一级菜单id 获取二级菜单
     *
     * @param pid 一级菜单id
     * @return 二级菜单列表
     */
    List<SysMenu> listChildrenMenuByParentId(Long pid);

    /**
     * 删除 菜单，与角色菜单之间的关系
     *
     * @param menuId
     * @return
     */
    boolean deleteMenuAndRoleMenu(Long menuId);

    /**
     * 获取简单的menu tree 用于在 ele-ui tree中显示，根据orderNum排序
     *
     * @return ztreeDto列表
     */
    List<SysMenu> listSimpleMenuNoButton();


    /**
     * 获取用户菜单列表
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    List<SysMenu> listMenuByUserId(Long userId);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     *
     * @param id
     * @return
     */
    List<SysMenu> getPermissionList(Long id);

}

