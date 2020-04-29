package cn.harry.sys.service;

import cn.harry.sys.entity.SysDict;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 数据字典
 *
 * @author honghh
 * Date 2020-03-16 09:53:38
 * Copyright (C) www.tech-harry.cn
 */
public interface SysDictService {

    /**
     * 根据关键字查询字典信息
     *
     * @param sysDict
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<SysDict> getPage(SysDict sysDict, Integer pageSize, Integer pageNum);

    /**
     * 创建字典信息
     *
     * @param sysDict
     * @return
     */
    int create(SysDict sysDict);

    /**
     * 修改字典状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 更新字典
     *
     * @param id
     * @param sysDict
     * @return
     */
    int update(Long id, SysDict sysDict);

    /**
     * 根据ID 获取字典信息
     *
     * @param id
     * @return
     */
    SysDict selectById(Long id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);
}

