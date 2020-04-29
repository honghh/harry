package cn.harry.sys.service.impl;

import cn.harry.sys.dao.SysRoleDeptDao;
import cn.harry.sys.entity.SysRoleDept;
import cn.harry.sys.service.SysRoleDeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色部门关联
 *
 * @author honghh
 * Date 2020-03-16 08:51:37
 * Copyright (C) www.tech-harry.cn
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptDao, SysRoleDept> implements SysRoleDeptService {

    @Override
    public int deleteRoleDeptByRoleId(Long roleId) {
        return this.baseMapper.delete(new LambdaQueryWrapper<SysRoleDept>()
                .eq(SysRoleDept::getRoleId, roleId));
    }

    @Override
    public int batchRoleDept(List<SysRoleDept> list) {
        return saveBatch(list) ? 1 : 0;
    }

    @Override
    public List<Long> queryDeptIdList(List<Long> roleIdList) {
        return baseMapper.queryDeptIdList(roleIdList);
    }
}