package cn.harry.sys.service.impl;

import cn.harry.sys.dao.SysConfigDao;
import cn.harry.sys.entity.SysConfig;
import cn.harry.sys.service.SysConfigService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * 系统配置信息表/枚举值表
 *
 * @author honghh
 * Date 2019-11-25 16:36:48
 * Copyright (C) www.honghh.top
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {


    @Override
    public IPage<SysConfig> getPage(SysConfig sysConfig, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>(sysConfig);

        if (StrUtil.isNotEmpty(sysConfig.getBeginTime())) {
            wrapper.gt(SysConfig::getCreateTime, sysConfig.getBeginTime());
        }

        if (StrUtil.isNotEmpty(sysConfig.getEndTime())) {
            wrapper.lt(SysConfig::getCreateTime, sysConfig.getEndTime());
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public SysConfig selectById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public int create(SysConfig sysConfig) {
        return this.baseMapper.insert(sysConfig);
    }

    @Override
    public int updateStatus(Long id, String status) {
        SysConfig config = new SysConfig();
        config.setId(id);
        config.setStatus(status);
        return this.baseMapper.updateById(config);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, SysConfig sysConfig) {
        sysConfig.setId(id);
        return this.baseMapper.updateById(sysConfig);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<SysConfig> getExportList(SysConfig sysConfig) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>(sysConfig);

        if (StrUtil.isNotEmpty(sysConfig.getBeginTime())) {
            wrapper.gt(SysConfig::getCreateTime, sysConfig.getBeginTime());
        }

        if (StrUtil.isNotEmpty(sysConfig.getEndTime())) {
            wrapper.lt(SysConfig::getCreateTime, sysConfig.getEndTime());
        }
        return list(wrapper);
    }
}