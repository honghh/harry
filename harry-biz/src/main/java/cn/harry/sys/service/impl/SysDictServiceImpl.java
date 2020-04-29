package cn.harry.sys.service.impl;

import cn.harry.common.exception.ApiException;
import cn.harry.common.exption.SysExceptionEnum;
import cn.harry.sys.dao.SysDictDao;
import cn.harry.sys.entity.SysDict;
import cn.harry.sys.service.SysDictService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 数据字典
 *
 * @author honghh
 * Date 2020-03-16 09:53:38
 * Copyright (C) www.tech-harry.cn
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDict> implements SysDictService {

    @Override
    public IPage<SysDict> getPage(SysDict sysDict, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>(sysDict);
        if (StrUtil.isNotEmpty(sysDict.getBeginTime())) {
            wrapper.gt(SysDict::getCreateTime, sysDict.getBeginTime());
        }
        if (StrUtil.isNotEmpty(sysDict.getEndTime())) {
            wrapper.lt(SysDict::getCreateTime, sysDict.getEndTime());
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public int create(SysDict sysDict) {
        if (checkDictTypeUnique(null, sysDict.getType())) {
            throw new ApiException(SysExceptionEnum.DICT_TYPE_EXISTS);
        }
        return this.baseMapper.insert(sysDict);
    }


    @Override
    public int updateStatus(Long id, String status) {
        return 0;
    }

    @Override
    public int update(Long id, SysDict sysDict) {
        sysDict.setId(id);
        if (checkDictTypeUnique(id, sysDict.getType())) {
            throw new ApiException(SysExceptionEnum.DICT_TYPE_EXISTS);
        }
        return this.baseMapper.updateById(sysDict);
    }

    @Override
    public SysDict selectById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }


    /**
     * 查询字典类型是否存在
     *
     * @param id
     * @param type
     * @return
     */
    private boolean checkDictTypeUnique(Long id, String type) {
        Long dictId = id == null ? -1L : id;
        SysDict sysDict = this.baseMapper.selectOne(new LambdaQueryWrapper<SysDict>().eq(SysDict::getType, type));
        return sysDict != null && !dictId.equals(sysDict.getId());
    }
}