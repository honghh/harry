package cn.harry.controller.sys;

import cn.harry.common.annotation.SysLog;
import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.common.enums.BusinessType;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.service.SysUserRoleService;
import cn.harry.sys.service.SysUserService;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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

    /**
     * @param params 参数内容
     *               pageNum
     *               pageSize
     *               username 用户名称
     *               phone 手机号
     *               deptId 部门ID
     * @return cn.harry.common.api.CommonPage
     */
    @ApiOperation("list => 根据用户名或姓名分页获取用户列表")
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SysUser>> list(@RequestParam Map<String, Object> params) {
        IPage<SysUser> page = sysUserService.getPage(params);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("info/{id} => 用户信息")
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping("/info/{id}")
    public CommonResult<SysUser> info(@PathVariable("id") Long id) {
        SysUser user = sysUserService.getUserById(id);
        if (id != null) {
            //获取用户所属的角色列表
            List<Long> roleIdList = sysUserRoleService.listRoleIdByUserId(id);
            user.setRoleIds(roleIdList);
        }
        return CommonResult.success(user);
    }

    @ApiOperation("update/{id} => 修改指定用户信息")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @SysLog(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Long id, @RequestBody SysUser user) {
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

    @ApiOperation("create => 创建用户信息")
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @SysLog(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody SysUser user) {
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

        int count = sysUserService.insertUserAndRole(user);

        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改用户状态")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @SysLog(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = sysUserService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "resetPwd => 修改密码", notes = "重置密码")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @SysLog(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/resetPwd")
    public CommonResult<Integer> resetPwd(@RequestBody SysUser user) {
        // 新密码
        String newPass = passwordEncoder.encode(user.getPassword());
        // 更新密码
        int result = sysUserService.updatePasswordByUserId(user.getId(), newPass);
        if (result > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();

    }

    @ApiOperation("deleteByIds/{ids} => 删除指定用户信息")
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @SysLog(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = sysUserService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("export => 按条件导出（不分页）")
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @SysLog(title = "用户管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(HttpServletResponse response,@RequestParam Map<String, Object> params) {
        List<SysUser> list = sysUserService.getExportList(params);
        try {
            EasyExcel.write(response.getOutputStream(), SysUser.class).sheet("用户管理").doWrite(list);
            return CommonResult.success();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();

    }

    @ApiOperation("importTemplate => 下载模板")
    @SysLog(title = "用户管理", businessType = BusinessType.EXPORT)
    @GetMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        EasyExcel.write(response.getOutputStream(), SysUser.class).sheet("用户管理").doWrite(null);
    }
}

