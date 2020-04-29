package cn.harry.component.security.service;

import cn.harry.common.exception.ApiException;
import cn.harry.common.exption.SysExceptionEnum;
import cn.harry.common.utils.BeanUtils;
import cn.harry.common.utils.SysUserDetails;
import cn.harry.sys.entity.SysMenu;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.service.SysMenuService;
import cn.harry.sys.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: UserDetailsServiceImpl
 * Description:
 *
 * @author honghh
 * Date 2020/04/13 11:01
 * Copyright (C) www.honghh.top
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取登录用户信息
        SysUser user = sysUserService.getByUserName(username);
        if (BeanUtils.isNull(user)) {
            throw new ApiException(SysExceptionEnum.WRONG_USERNAME_OR_PASSWORD);
        }

        List<SysMenu> permissions = sysMenuService.selectMenuList(user.getId());
        return new SysUserDetails(user, permissions);
    }
}
