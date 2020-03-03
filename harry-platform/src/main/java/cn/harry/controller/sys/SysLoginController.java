package cn.harry.controller.sys;

import cn.harry.common.api.CommonResult;
import cn.harry.common.utils.JwtTokenUtil;
import cn.harry.common.utils.SysUserUtils;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.param.SysUserLoginParam;
import cn.harry.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SysLoginController
 * Description:
 *
 * @author honghh
 * Date 2019/10/15 12:42
 * Copyright (C) www.honghh.top
 */
@RestController
@Api(tags = "Sys-admin => 后台用户管理")
@RequestMapping("/admin")
public class SysLoginController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "login => 登录以后返回token")
    @PostMapping(value = "/login")
    public CommonResult<Map<String, String>> login(@RequestBody SysUserLoginParam sysUserLoginParam) {
        String token = sysUserService.login(sysUserLoginParam.getUsername(), sysUserLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", jwtTokenUtil.getTokenHead());
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
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "info => 获取当前登录用户信息")
    @GetMapping(value = "/info")
    public CommonResult<Map<String, Object>> getInfo(Principal principal) {
        String username = principal.getName();
        SysUser umsAdmin = sysUserService.getByUserName(username);
        Map<String, Object> data = new HashMap<>();
        data.put("name", umsAdmin.getUsername());
        data.put("roles", SysUserUtils.getAuthorities());
        data.put("avatar", umsAdmin.getIcon());
        return CommonResult.success(data);
    }

    @ApiOperation(value = "logout => 登出功能")
    @PostMapping(value = "/logout")
    public CommonResult logout() {
        SecurityContextHolder.clearContext();
        return CommonResult.success(null);
    }


}

