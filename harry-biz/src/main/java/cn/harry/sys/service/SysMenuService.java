package cn.harry.sys.service;

import cn.harry.sys.entity.SysMenu;
import cn.harry.sys.vo.RouterVo;
import cn.harry.sys.vo.TreeSelect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 后台用户权限表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取用户所有权限 包括：根/菜单/按钮
     *
     * @param userId
     * @return
     */
    List<SysMenu> selectMenuList(Long userId);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId
     * @return
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<SysMenu> menus);


    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu   菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu, Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<Integer> selectMenuListByRoleId(Long roleId);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus);

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    List<SysMenu> buildMenuTree(List<SysMenu> menus);

    /**
     * 新增保存菜单信息
     *
     * @param menu
     * @return
     */
    int create(SysMenu menu);

    /**
     * 修改保存菜单信息
     *
     * @param id
     * @param menu
     * @return
     */
    int update(Long id, SysMenu menu);

    /**
     * 删除菜单管理信息
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
}

