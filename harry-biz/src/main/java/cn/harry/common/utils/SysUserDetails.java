package cn.harry.common.utils;

import cn.harry.sys.entity.SysMenu;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.enums.StatusEnums;
import cn.hutool.core.util.StrUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: SpringSecurity 需要的用户详情
 *
 * @author honghh
 * Date 2019/08/14 15:00
 * Copyright (C) www.honghh.top
 */
public class SysUserDetails implements UserDetails {
    private SysUser sysUser;
    private List<SysMenu> permissions;

    public SysUserDetails(SysUser sysUser, List<SysMenu> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissions.stream().filter(item -> item != null && StrUtil.isNotEmpty(item.getValue()))
                .map(item -> new SimpleGrantedAuthority(item.getValue()))
                .collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return StatusEnums.ENABLE.getKey().equals(sysUser.getStatus());
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysMenu> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysMenu> permissions) {
        this.permissions = permissions;
    }
}
