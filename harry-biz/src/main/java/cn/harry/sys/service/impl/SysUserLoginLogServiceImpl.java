package cn.harry.sys.service.impl;

import cn.harry.sys.entity.SysUserLoginLog;
import cn.harry.sys.service.SysUserLoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import cn.harry.sys.dao.SysUserLoginLogDao;

/**
 * 后台用户登录日志表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Service("sysUserLoginLogService")
public class SysUserLoginLogServiceImpl extends ServiceImpl<SysUserLoginLogDao, SysUserLoginLog> implements SysUserLoginLogService {


    @Override
    public int create(SysUserLoginLog sysUserLoginLog) {
        return this.baseMapper.insert(sysUserLoginLog);
    }

    @Override
    public boolean deleteByKey(String key) {
        return remove(new LambdaQueryWrapper<SysUserLoginLog>()
                .eq(SysUserLoginLog::getKeyword, key));
    }
}