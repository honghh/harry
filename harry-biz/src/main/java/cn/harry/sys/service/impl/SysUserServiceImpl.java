package cn.harry.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import cn.harry.common.constant.CommonConstant;
import cn.harry.common.utils.JwtTokenUtil;
import cn.harry.sys.dao.SysUserDao;
import cn.harry.sys.dao.SysUserLoginLogDao;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.entity.SysUserLoginLog;
import cn.harry.sys.entity.SysUserRole;
import cn.harry.sys.param.SysUserCreatByMemberParam;
import cn.harry.sys.service.SysUserRoleService;
import cn.harry.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 后台用户表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
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
    private SysUserLoginLogDao adminLoginLogDao;
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
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
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
    public SysUser getItem(Long id) {
        return getById(id);
    }

    @Override
    public List<SysUser> list(String name, Integer pageSize, Integer pageNum) {
        return null;
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
    public int updateUserAndUserRole(SysUser user) {
        //先删除用户与角色关系
        sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, user.getId()));

        if (CollUtil.isNotEmpty(user.getRoleIdList())) {
            //保存用户与角色关系
            sysUserRoleService.insertUserAndUserRole(user.getId(), user.getRoleIdList());
        }
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int creatUserByMember(SysUserCreatByMemberParam creatByMember) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(creatByMember, sysUser);
        this.baseMapper.insert(sysUser);
        sysUserRoleService.insertUserAndUserRole(sysUser.getId(), Lists.newArrayList(CommonConstant.DEFAULT_ROLE));
        return 1;
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        SysUser admin = getByUserName(username);
        SysUserLoginLog loginLog = new SysUserLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        adminLoginLogDao.insert(loginLog);
    }

}