package cn.harry.common.utils;

import cn.harry.sys.entity.SysUser;
import cn.harry.sys.vo.SysUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * ClassName: BeanUtils
 * Description: 获取当前登陆用户信息
 *
 * @author honghh
 * Date 2019/08/14 15:00
 * Copyright (C) www.honghh.top
 */
public class SysUserUtils {

    public static SysUser getSysUser() {
        try {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication auth = ctx.getAuthentication();
            SysUserDetails memberDetails = (SysUserDetails) auth.getPrincipal();
            return memberDetails.getSysUser();
        } catch (Exception e) {
            return new SysUser();
        }
    }

    public static Collection<? extends GrantedAuthority> getAuthorities() {
        try {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication auth = ctx.getAuthentication();
            SysUserDetails memberDetails = (SysUserDetails) auth.getPrincipal();
            return memberDetails.getAuthorities();
        } catch (Exception e) {
            return null;
        }
    }
}
