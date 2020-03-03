package cn.harry.controller.sys;

import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.common.utils.SysUserUtils;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.param.SysUserUpdatePasswordParam;
import cn.harry.sys.service.SysUserRoleService;
import cn.harry.sys.service.SysUserService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * ClassName: SysUserController
 * Description:
 *
 * @author honghh
 * Date 2019/10/15 12:42
 * Copyright (C) www.honghh.top
 */
@RestController
@Api(tags = "Sys-user => 后台用户管理")
@RequestMapping("/sys/user")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("list => 根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SysUser>> list(@RequestParam(value = "keyword", required = false) String name,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<SysUser> adminList = sysUserService.getPage(name, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("info/{id} => 用户信息")
    @GetMapping("/info/{id}")
    public CommonResult<SysUser> info(@PathVariable("id") Long id) {
        SysUser user = sysUserService.getUserById(id);
        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.listRoleIdByUserId(id);
        user.setRoleIdList(roleIdList);
        return CommonResult.success(user);
    }

    @ApiOperation("update/{id} => 修改指定用户信息")
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SysUser user) {
        String password = user.getPassword();
        SysUser dbUserNameInfo = sysUserService.getByUserName(user.getUsername());

        if (dbUserNameInfo != null && !Objects.equals(dbUserNameInfo.getId(), user.getId())) {
            return CommonResult.failed("该用户已存在");
        }

        if (StrUtil.isBlank(password)) {
            user.setPassword(null);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        int count = sysUserService.updateUserAndRole(user);

        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改用户状态")
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, Integer status) {
        int count = sysUserService.updateStatus(id, status);
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

    @ApiOperation("delete/{id} => 删除指定用户信息")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = sysUserService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}

