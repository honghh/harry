package cn.harry.controller.sys;

import cn.harry.common.annotation.SysLog;
import cn.harry.common.api.CommonResult;
import cn.harry.common.enums.BusinessType;
import cn.harry.common.utils.SysUserUtils;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.param.SysUserUpdatePasswordParam;
import cn.harry.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName: SysProfileController
 * Description:
 *
 * @author honghh
 * Date 2020/04/29 13:42
 * Copyright (C) www.honghh.top
 */
@RestController
@Api(tags = "Sys-user => 后台用户管理")
@RequestMapping("/sys/user/profile")
public class SysProfileController {
    @Resource
    private SysUserService sysUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("获取个人信息")
    @GetMapping
    public CommonResult<SysUser> getPersonalInfo() {
        // 1. 获取用户基本信息
        SysUser user = SysUserUtils.getSysUser();
        return CommonResult.success(user);
    }

    @ApiOperation("update => 修改指定用户信息")
    @SysLog(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update")
    public CommonResult<Integer> update(@RequestBody SysUser user) {
        int count = sysUserService.update(user.getId(),user);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "updatePassword => 修改密码", notes = "修改当前登陆用户的密码")
    @PutMapping(value = "/updatePassword")
    public CommonResult<Integer> updatePassword(@RequestBody SysUserUpdatePasswordParam passwordParam) {
        Long userId = SysUserUtils.getSysUser().getId();
        SysUser dbUser = sysUserService.getUserById(userId);
        if (!passwordEncoder.matches(passwordParam.getPassword(), dbUser.getPassword())) {
            return CommonResult.failed("原密码不正确");
        }
        // 新密码
        String newPass = passwordEncoder.encode(passwordParam.getNewPassword());
        // 更新密码
        int result = sysUserService.updatePasswordByUserId(userId, newPass);
        if (result > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();

    }
}
