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
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<SysConfig> getPage(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 新建配置参数
     * @param sysConfig
     * @return
     */
    int create(SysConfig sysConfig);

    /**
     * 修改配置参数状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, Integer status);

    /**
     * 删除指定配置参数
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定配置参数
     * @param id
     * @param sysConfig
     * @return
     */
    int update(Long id, SysConfig sysConfig);
}

