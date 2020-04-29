package cn.harry.sys.dao;

import cn.harry.sys.entity.SysUserLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户登录日志表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Mapper
public interface SysUserLoginLogDao extends BaseMapper<SysUserLoginLog> {

    /**
     * 清空
     *
     * @return
     */
    int clean();
}
