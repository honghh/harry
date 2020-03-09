package cn.harry.sys.service;

import cn.harry.sys.entity.SysUserLoginLog;

/**
 * 后台用户登录日志表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
public interface SysUserLoginLogService {

    /**
     * 创建用户登陆历史
     *
     * @param sysUserLoginLog
     * @return
     */
    int create(SysUserLoginLog sysUserLoginLog);

    /**
     * 根据关键字删除key
     *
     * @param key
     */
    boolean deleteByKey(String key);
}

