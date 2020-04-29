package cn.harry.controller.sys;

import cn.harry.common.api.CommonResult;
import cn.harry.common.utils.JwtTokenUtil;
import cn.harry.common.utils.SecurityUtils;
import cn.harry.common.utils.SysUserUtils;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.param.SysUserLoginParam;
import cn.harry.sys.service.OnlineUserService;
import cn.harry.sys.service.SysCaptchaService;
import cn.harry.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName: SysLoginController
 * Description:
 *
 * @author honghh
 * Date 2019/10/15 12:42
 * Copyright (C) www.honghh.top
 */
@RestController
@Api(tags = "Sys-admin => 后台登陆注销管理")
@RequestMapping("/admin")
public class SysLoginController {
    @Resource
    private SysCaptchaService sysCaptchaService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "login => 登录以后返回token")
    @PostMapping(value = "/login")
    public CommonResult<Map<String, String>> login(@RequestBody SysUserLoginParam sysUserLoginParam, HttpServletRequest request) {
        boolean captcha = sysCaptchaService.validate(sysUserLoginParam.getUuid(), sysUserLoginParam.getCode());
        if (!captcha) {
            return CommonResult.validateFailed("验证码不正确");
        }

        String token = sysUserService.login(sysUserLoginParam.getUsername(), sysUserLoginParam.getPassword(), request);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", jwtTokenUtil.getTokenHead());
        tokenMap.put("expiration", jwtTokenUtil.getExpiration());
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "token/refresh => 刷新token")
    @GetMapping(value = "/token/refresh")
    public CommonResult<Map<String, String>> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(jwtTokenUtil.getTokenHeader());
        String refreshToken = sysUserService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed();
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", jwtTokenUtil.getTokenHead());
        tokenMap.put("expiration", jwtTokenUtil.getExpiration());
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "info => 获取当前登录用户信息")
    @GetMapping(value = "/info")
    public CommonResult<Map<String, Object>> getInfo() {
        SysUser user = SysUserUtils.getSysUser();
        Map<String, Object> data = new HashMap<>();
        Set<String> perms = new HashSet<>();
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (SecurityUtils.isAdmin(user.getId())) {
            perms.add("*:*:*");
            roles.add("admin");
        } else {
            roles.add("other");
            perms = SecurityUtils.getUserDetails().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        }
        data.put("name", user.getUsername());
        data.put("roles", roles);
        data.put("permissions", perms);
        data.put("avatar", user.getIcon());
        return CommonResult.success(data);
    }

    @ApiOperation(value = "logout => 登出功能")
    @PostMapping(value = "/logout")
    public CommonResult logout(HttpServletRequest request) throws Exception {
        onlineUserService.logout(jwtTokenUtil.getToken(request));
        SecurityContextHolder.clearContext();
        return CommonResult.success();
    }


}

