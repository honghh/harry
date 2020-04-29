package cn.harry.monitor.service.impl;

import cn.harry.monitor.dao.SysOperationLogDao;
import cn.harry.monitor.entity.SysOperationLog;
import cn.harry.monitor.service.SysOperationLogService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * ClassName: SysOperationLogServiceImpl
 * Description:
 *
 * @author honghh
 * Date 2020/04/20 16:02
 * Copyright (C) www.honghh.top
 */
@Service("sysOperationLogService")
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogDao, SysOperationLog> implements SysOperationLogService {
    @Override
    public void insertLog(SysOperationLog log) {
        this.baseMapper.insert(log);
    }

    @Override
    public IPage<SysOperationLog> getPage(SysOperationLog sysOperationLog, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SysOperationLog> wrapper = new LambdaQueryWrapper<>(sysOperationLog);
        if (StrUtil.isNotEmpty(sysOperationLog.getBeginTime())) {
            wrapper.gt(SysOperationLog::getCreateTime, sysOperationLog.getBeginTime());
        }

        if (StrUtil.isNotEmpty(sysOperationLog.getEndTime())) {
            wrapper.lt(SysOperationLog::getCreateTime, sysOperationLog.getEndTime());
        }

        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public void clean() {
        this.baseMapper.clean();
    }
}
