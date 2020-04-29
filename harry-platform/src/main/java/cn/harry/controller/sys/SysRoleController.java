package cn.harry.controller.sys;

import cn.harry.common.annotation.SysLog;
import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.common.enums.BusinessType;
import cn.harry.sys.entity.SysRole;
import cn.harry.sys.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: SysRoleController
 * Description:
 *
 * @author honghh
 * Date 2019/10/17 10:00
 * Copyright (C) www.honghh.top
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "Sys-role => 角色管理")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @ApiOperation("list => 根据名获取角色列表")
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SysRole>> list(SysRole sysRole,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<SysRole> roles = sysRoleService.getPage(sysRole, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roles));
    }


    @ApiOperation("listAll => 获取所有角色列表")
    @GetMapping(value = "/listAll")
    public CommonResult<List<SysRole>> listAll() {
        List<SysRole> roles = sysRoleService.getListAll();
        return CommonResult.success(roles);
    }

    @ApiOperation("/{id} => 根据角色ID获取详细信息")
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping("/{id}")
    public CommonResult<SysRole> info(@PathVariable("id") Long id) {
        SysRole role = sysRoleService.getById(id);
        return CommonResult.success(role);
    }

    @ApiOperation("创建指定角色信息")
    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @SysLog(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("create")
    public CommonResult<Integer> create(@RequestBody SysRole sysRole) {
        int count = sysRoleService.create(sysRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("update/{id} => 修改指定角色信息")
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @SysLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SysRole sysRole) {
        int count = sysRoleService.update(id, sysRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("dataScope/{id} => 修改保存数据权限")
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @SysLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/dataScope/{id}")
    public CommonResult<Integer> dataScope(@PathVariable Long id, @RequestBody SysRole sysRole) {
        int count = sysRoleService.dataScope(id, sysRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("delete/{ids} => 删除指定角色信息")
    @PreAuthorize("@ss.hasPermi('system:role:remove')")
    @SysLog(title = "角色管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/delete/{ids}")
    public CommonResult<Integer> delete(@PathVariable Long[] ids) {
        int count = sysRoleService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @SysLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @ApiOperation("changeStatus => 状态修改")
    @PutMapping("/changeStatus")
    public CommonResult<Integer> changeStatus(@RequestBody SysRole role) {
        return CommonResult.success(sysRoleService.updateRoleStatus(role));
    }
}
