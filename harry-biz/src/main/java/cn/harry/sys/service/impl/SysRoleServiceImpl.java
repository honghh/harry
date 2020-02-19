package cn.harry.sys.service.impl;

import cn.harry.sys.dao.SysRoleDao;
import cn.harry.sys.dao.SysRoleMenuDao;
import cn.harry.sys.dao.SysUserRoleDao;
import cn.harry.sys.entity.SysRole;
import cn.harry.sys.entity.SysRoleMenu;
import cn.harry.sys.entity.SysUserRole;
import cn.harry.sys.service.SysRoleService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 后台用户角色表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public IPage<SysRole> getPage(String name, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(name)) {
            wrapper.like(SysRole::getName, name);
        }
        return page(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(SysRole sysRole) {
       int re = this.baseMapper.insert(sysRole);
        sysRoleMenuDao.insertRoleAndRoleMenu(sysRole.getId(), sysRole.getMenuIdList());
        return re;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Long id, SysRole sysRole) {
        sysRole.setId(id);

        sysRoleMenuDao.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, id));
        if (!CollectionUtil.isEmpty(sysRole.getMenuIdList())) {
            //保存角色与菜单关系
            sysRoleMenuDao.insertRoleAndRoleMenu(sysRole.getId(), sysRole.getMenuIdList());
        }

        return this.baseMapper.updateById(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {


        //删除角色与菜单关联
        sysRoleMenuDao.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, id));

        //删除角色与用户关联
        sysUserRoleDao.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, id));
        //删除角色
        return  this.baseMapper.deleteById(id);
    }


}