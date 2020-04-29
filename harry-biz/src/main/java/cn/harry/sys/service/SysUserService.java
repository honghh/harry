package cn.harry.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.harry.sys.entity.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 后台用户表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
public interface SysUserService {

    /**
     * 根据用户名获取后台管理员
     *
     * @param username 用户名
     * @return cn.harry.sys.entity.SysUser
     */
    SysUser getByUserName(String username);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @param request
     * @return 生成的JWT的token
     */
    String login(String username, String password, HttpServletRequest request);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     * @return String
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     *
     * @param id
     * @return
     */
    SysUser getUserById(Long id);

    /**
     * 修改指定用户信息
     *
     * @param id
     * @param admin
     * @return
     */
    int update(Long id, SysUser admin);

    /**
     * 删除指定用户
     *
     * @param id
     * @return
     */
    int delete(Long id);


    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    IPage<SysUser> getPage(Map<String, Object> params);

    /**
     * 更新用户以及权限
     *
     * @param user
     * @return
     */
    int updateUserAndRole(SysUser user);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param newPassword 新密码
     * @return
     */
    int updatePasswordByUserId(Long userId, String newPassword);

    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 创建用户及权限
     *
     * @param user
     * @return
     */
    int insertUserAndRole(SysUser user);

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

}

