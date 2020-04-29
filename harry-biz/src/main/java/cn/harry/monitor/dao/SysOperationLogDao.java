package cn.harry.monitor.dao;

import cn.harry.monitor.entity.SysOperationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: SysOperationLogDao
 * Description:
 *
 * @author honghh
 * Date 2020/04/20 16:06
 * Copyright (C) www.honghh.top
 */
@Mapper
public interface SysOperationLogDao extends BaseMapper<SysOperationLog> {

    /**
     * 清空
     *
     * @return
     */
    void clean();
}
