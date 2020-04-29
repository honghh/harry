package cn.harry.sys.service.impl;

import cn.harry.common.exception.ApiException;
import cn.harry.common.exption.SysExceptionEnum;
import cn.harry.common.utils.SecurityUtils;
import cn.harry.sys.dao.SysMenuDao;
import cn.harry.sys.entity.SysMenu;
import cn.harry.sys.enums.MenuTypeEnums;
import cn.harry.sys.service.SysMenuService;
import cn.harry.sys.service.SysRoleMenuService;
import cn.harry.sys.vo.MetaVo;
import cn.harry.sys.vo.RouterVo;
import cn.harry.sys.vo.TreeSelect;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户菜单权限表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Resource
    SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> selectMenuList(Long userId) {
        return selectMenuList(new SysMenu(), userId);
    }

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        List<SysMenu> menuList = null;
        // 管理员显示所有菜单信息
        if (SecurityUtils.isAdmin(userId)) {
            menuList = list();
        } else {
            menuList = this.baseMapper.getPermissionList(userId);
        }
        return menuList;
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus;
        if (SecurityUtils.isAdmin(userId)) {
            menus = list(new LambdaQueryWrapper<SysMenu>()
                    .in(SysMenu::getType, Arrays.asList(MenuTypeEnums.CATALOG.getValue(), MenuTypeEnums.MENU.getValue())));
        } else {
            menus = this.baseMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setName(StringUtils.capitalize(menu.getPath()));
            router.setPath(getRouterPath(menu));
            router.setComponent(StringUtils.isEmpty(menu.getUri()) ? "Layout" : menu.getUri());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            List<SysMenu> cMenus = menu.getChildren();
            if (CollectionUtil.isNotEmpty(cMenus) && MenuTypeEnums.CATALOG.getValue() == (menu.getType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            routers.add(router);
        }
        return routers;
    }


    @Override
    public List<Integer> selectMenuListByRoleId(Long roleId) {
        return this.baseMapper.selectMenuListByRoleId(roleId);
    }

    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus) {
        List<SysMenu> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        List<SysMenu> returnList = new ArrayList<>();
        for (SysMenu t : menus) {
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getPid() == 0) {
                recursionFn(menus, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    @Override
    public int create(SysMenu menu) {
        return this.baseMapper.insert(menu);
    }

    @Override
    public int update(Long id, SysMenu menu) {
        menu.setId(id);
        return this.baseMapper.updateById(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Long id) {
        if (hasChildByMenuId(id)) {
            throw new ApiException(SysExceptionEnum.MENU_SUBMENU_EXISTS);
        }
        if (checkMenuExistRole(id)) {
            throw new ApiException(SysExceptionEnum.MENU_IS_ASSIGNED);
        }
        return this.baseMapper.deleteById(id);
    }

    private boolean checkMenuExistRole(Long menuId) {
        int result = sysRoleMenuService.checkMenuExistRole(menuId);
        return result > 0;
    }

    private boolean hasChildByMenuId(Long menuId) {
        int result = this.baseMapper.selectCount(new LambdaQueryWrapper<SysMenu>()
                .eq(SysMenu::getPid, menuId));
        return result > 0;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录
        if (0 == menu.getPid() && "0".equals(menu.getOuterLink())) {
            routerPath = "/" + menu.getPath();
        }
        return routerPath;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
        List<SysMenu> returnList = new ArrayList<>();
        for (SysMenu t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getPid() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                for (SysMenu n : childList) {
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        for (SysMenu n : list) {
            if (n.getPid().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0;
    }


}