package cn.harry.sys.service.impl;

import cn.harry.common.utils.JwtTokenUtil;
import cn.harry.sys.dao.SysUserDao;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.service.SysUserRoleService;
import cn.harry.sys.service.SysUserService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台用户表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Slf4j
@Service("sysAdminService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private SysUserRoleService sysUserRoleService;


    @Override
    public SysUser getByUserName(String username) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(jwtTokenUtil.getTokenHead().length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public SysUser getUserById(Long id) {
        return getById(id);
    }


    @Override
    public int update(Long id, SysUser user) {
        user.setId(id);
        return this.baseMapper.updateById(user);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public IPage<SysUser> getPage(String name, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(name)) {
            wrapper.like(SysUser::getUsername, name).or().like(SysUser::getNickName, name);
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public int updateUserAndRole(SysUser user) {
        //先删除用户与角色关系
        sysUserRoleService.delAndCreateRole(user.getId(), user.getRoleIdList());
        // 更新用户
        return this.baseMapper.updateById(user);
    }

    @Override
    public int updatePasswordByUserId(Long userId, String newPassword) {
        SysUser user = new SysUser();
        user.setPassword(newPassword);
        user.setId(userId);
        return this.baseMapper.updateById(user);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setStatus(status);
        return this.baseMapper.updateById(user);
    }

}