package cn.harry.sys.service;

import cn.harry.sys.param.SysUserCreatByMemberParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.harry.sys.entity.SysUser;

import java.util.List;

/**
 * 后台用户表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
 */
public interface SysUserService {

    /**
     * 根据用户名获取后台管理员
     *
     * @param username
     * @return
     */
    SysUser getByUserName(String username);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    SysUser getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<SysUser> list(String name, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, SysUser admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);


    /**
     * 分页获取数据
     *
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<SysUser> getPage(String name, Integer pageSize, Integer pageNum);

    int updateUserAndUserRole(SysUser user);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param newPassword 新密码
     */
    int updatePasswordByUserId(Long userId, String newPassword);

    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, Integer status);

    /**
     * 根据会员信息创建商家后台管理员
     * @param creatByMember
     * @return
     */
    int creatUserByMember(SysUserCreatByMemberParam creatByMember);
}

