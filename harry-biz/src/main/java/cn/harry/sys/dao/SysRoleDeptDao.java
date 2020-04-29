package cn.harry.sys.dao;

import cn.harry.sys.entity.SysRoleDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色部门关联
 *
 * @author honghh
 * Date 2020-03-16 08:51:37
 * Copyright (C) www.tech-harry.cn
 */
@Mapper
public interface SysRoleDeptDao extends BaseMapper<SysRoleDept> {

    /**
     * 根据角色ID，获取部门ID列表
     *
     * @param roleIdList
     * @return
     */
    List<Long> queryDeptIdList(@Param("roleIdList") List<Long> roleIdList);
}
