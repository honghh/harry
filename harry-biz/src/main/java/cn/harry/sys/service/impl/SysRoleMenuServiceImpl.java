package cn.harry.sys.service.impl;

import cn.harry.sys.dao.SysRoleMenuDao;
import cn.harry.sys.entity.SysRoleMenu;
import cn.harry.sys.service.SysRoleMenuService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import java.util.ArrayList;
import java.util.List;

/**
 * 后台用户角色和权限关系表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {


    @Override
    public void deleteByMenuId(Long menuId) {
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getMenuId, menuId);
        this.baseMapper.delete(wrapper);
    }

    @Override
    public List<Long> listMenuIdByRoleId(Long roleId) {
        List<Long> ids = new ArrayList<>();
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> sysRoleMenus = list(wrapper);
        if (CollectionUtil.isNotEmpty(sysRoleMenus)) {
            sysRoleMenus.forEach(item -> {
                ids.add(item.getMenuId());
            });
            return ids;
        }
        return null;
    }

    @Override
    public int checkMenuExistRole(Long menuId) {
        return count(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getMenuId, menuId));
    }

    @Override
    public void insertRoleAndRoleMenu(Long id, List<Long> menuIds) {
        this.baseMapper.insertRoleAndRoleMenu(id,menuIds);
    }
}