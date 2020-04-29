package cn.harry.sys.service.impl;

import cn.harry.sys.dao.SysRoleDao;
import cn.harry.sys.entity.*;
import cn.harry.sys.enums.StatusEnums;
import cn.harry.sys.service.SysRoleDeptService;
import cn.harry.sys.service.SysRoleMenuService;
import cn.harry.sys.service.SysRoleService;
import cn.harry.sys.service.SysUserRoleService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleDeptService sysRoleDeptService;

    @Override
    public IPage<SysRole> getPage(SysRole sysRole, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>(sysRole);

        if (StrUtil.isNotEmpty(sysRole.getBeginTime())) {
            wrapper.gt(SysRole::getCreateTime, sysRole.getBeginTime());
        }

        if (StrUtil.isNotEmpty(sysRole.getEndTime())) {
            wrapper.lt(SysRole::getCreateTime, sysRole.getEndTime());
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(SysRole sysRole) {
        int re = this.baseMapper.insert(sysRole);
        sysRoleMenuService.insertRoleAndRoleMenu(sysRole.getId(), sysRole.getMenuIds());
        return re;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Long id, SysRole sysRole) {
        sysRole.setId(id);

        sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, id));
        if (!CollectionUtil.isEmpty(sysRole.getMenuIds())) {
            //保存角色与菜单关系
            sysRoleMenuService.insertRoleAndRoleMenu(sysRole.getId(), sysRole.getMenuIds());
        }

        return this.baseMapper.updateById(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        //删除角色与菜单关联
        sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, id));
        //删除角色与用户关联
        sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, id));
        //删除角色
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        for (Long id : ids) {
            //删除角色与菜单关联
            sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, id));
            //删除角色与用户关联
            sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, id));
        }
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int dataScope(Long roleId, SysRole sysRole) {
        sysRole.setId(roleId);
        // 修改角色信息
        updateById(sysRole);
        // 删除角色与部门关联
        sysRoleDeptService.deleteRoleDeptByRoleId(roleId);
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(sysRole);
    }

    @Override
    public int updateRoleStatus(SysRole role) {
        return this.baseMapper.updateById(role);
    }

    @Override
    public List<SysRole> getListAll() {
        return list(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getStatus, StatusEnums.ENABLE.getKey()));
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleDept(SysRole role) {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<SysRoleDept> list = new ArrayList<>();
        for (Long deptId : role.getDeptIds()) {
            SysRoleDept rd = new SysRoleDept();
            rd.setRoleId(role.getId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0) {
            rows = sysRoleDeptService.batchRoleDept(list);
        }
        return rows;
    }


}