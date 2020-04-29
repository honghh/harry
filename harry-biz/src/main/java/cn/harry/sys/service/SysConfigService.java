package cn.harry.sys.service;

import cn.harry.sys.entity.SysConfig;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 系统配置信息表/枚举值表
 *
 * @author honghh
 * Date 2019-11-25 16:36:48
 * Copyright (C) www.honghh.top
 */
public interface SysConfigService {
    /**
     * 分页获取字典
     *
     * @param sysConfig
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<SysConfig> getPage(SysConfig sysConfig, Integer pageSize, Integer pageNum);


    /**
     * 获取系统配置信息
     *
     * @param id
     * @return
     */
    SysConfig selectById(Long id);
    /**
     * 新建配置参数
     *
     * @param sysConfig
     * @return
     */
    int create(SysConfig sysConfig);

    /**
     * 修改配置参数状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 删除指定配置参数
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定配置参数
     *
     * @param id
     * @param sysConfig
     * @return
     */
    int update(Long id, SysConfig sysConfig);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

}

