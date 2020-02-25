package cn.harry.sys.dao;

import cn.harry.sys.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户权限表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu> {


    /**
     * 获取用户所有权限(包括+-权限)
     *
     * @param userId
     * @return
     */
    List<SysMenu> getPermissionList(@Param("userId") Long userId);

    /**
     * 查询用户的所有菜单ID
     * @param userId 用户id
     * @return 该用户所有可用的菜单
     */
    List<SysMenu> listMenuByUserId(@Param("userId") Long userId);
}
