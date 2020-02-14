package cn.harry.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.harry.common.constant.CommonConstant;
import cn.harry.sys.dao.SysMenuDao;
import cn.harry.sys.entity.SysMenu;
import cn.harry.sys.service.SysMenuService;
import cn.harry.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台用户权限表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> listChildrenMenuByParentId(Long pid) {
        return list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getPid, pid));
    }

    @Override
    public boolean deleteMenuAndRoleMenu(Long menuId) {
        //删除菜单与角色关联
        sysRoleMenuService.deleteByMenuId(menuId);
        //删除菜单
        return this.removeById(menuId);

    }

    @Override
    public List<SysMenu> listSimpleMenuNoButton() {
        return list(new LambdaQueryWrapper<SysMenu>().ne(SysMenu::getType, 2));
    }

    @Override
    public List<SysMenu> listMenuByUserId(Long userId) {
        // 用户的所有菜单信息
        List<SysMenu> sysMenus;
        //系统管理员，拥有最高权限
        if (userId == CommonConstant.SUPER_ADMIN_ID) {
            sysMenus = list();
        } else {
            sysMenus = this.baseMapper.listMenuByUserId(userId);
        }

        Map<Long, List<SysMenu>> sysMenuLevelMap = sysMenus.stream()
                .sorted(Comparator.comparing(SysMenu::getSort))
                .collect(Collectors.groupingBy(SysMenu::getPid));

        // 一级菜单
        List<SysMenu> rootMenu = sysMenuLevelMap.get(0L);
        if (CollectionUtil.isEmpty(rootMenu)) {
            return Collections.emptyList();
        }
        // 二级菜单
        for (SysMenu sysMenu : rootMenu) {
            sysMenu.setList(sysMenuLevelMap.get(sysMenu.getId()));
        }
        return rootMenu;
    }

    @Override
    public List<SysMenu> getPermissionList(Long userId) {
        if (userId == CommonConstant.SUPER_ADMIN_ID) {
            return list();
        }
        return this.baseMapper.getPermissionList(userId);
    }

}