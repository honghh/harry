package cn.harry.sys.service.impl;

import cn.harry.sys.dao.SysUserLoginLogDao;
import cn.harry.sys.entity.SysUserLoginLog;
import cn.harry.sys.service.SysUserLoginLogService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public IPage<SysUserLoginLog> getPage(SysUserLoginLog loginLog, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SysUserLoginLog> wrapper = new LambdaQueryWrapper<>(loginLog);
        if (StrUtil.isNotEmpty(loginLog.getBeginTime())) {
            wrapper.gt(SysUserLoginLog::getCreateTime, loginLog.getBeginTime());
        }

        if (StrUtil.isNotEmpty(loginLog.getEndTime())) {
            wrapper.lt(SysUserLoginLog::getCreateTime, loginLog.getEndTime());
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int clean() {
        return this.baseMapper.clean();
    }

    @Override
    public List<SysUserLoginLog> getExportList(SysUserLoginLog loginLog) {
        LambdaQueryWrapper<SysUserLoginLog> wrapper = new LambdaQueryWrapper<>(loginLog);
        if (StrUtil.isNotEmpty(loginLog.getBeginTime())) {
            wrapper.gt(SysUserLoginLog::getCreateTime, loginLog.getBeginTime());
        }

        if (StrUtil.isNotEmpty(loginLog.getEndTime())) {
            wrapper.lt(SysUserLoginLog::getCreateTime, loginLog.getEndTime());
        }
        return list(wrapper);
    }
}