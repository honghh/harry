package cn.harry.sys.vo;

import cn.harry.sys.entity.SysMenu;
import cn.harry.sys.entity.SysUser;
import cn.hutool.core.util.StrUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: SpringSecurity需要的用户详情
 *
 * @author honghh
 * Date 2019/08/14 15:00
 * Copyright (C) www.tech-harry.cn
 */
public class SysUserDetails implements UserDetails {
    private SysUser sysUser;
    private List<SysMenu> permissionList;

    public SysUserDetails(SysUser sysUser, List<SysMenu> permissionList) {
        this.sysUser = sysUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(item -> item != null && StrUtil.isNotEmpty(item.getValue()))
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getStatus().equals(1);
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysMenu> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysMenu> permissionList) {
        this.permissionList = permissionList;
    }
}
