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
    public IPage<SysConfig> getPage(String keyword, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(SysConfig::getParamKey, keyword)
                    .or()
                    .like(SysConfig::getParamName, keyword)
                    .or()
                    .like(SysConfig::getParamName, keyword);
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public int create(SysConfig sysConfig) {
        return this.baseMapper.insert(sysConfig);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
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
}