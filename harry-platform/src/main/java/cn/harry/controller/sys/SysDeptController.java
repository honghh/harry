package cn.harry.controller.sys;

import cn.harry.common.annotation.SysLog;
import cn.harry.common.api.CommonResult;
import cn.harry.common.enums.BusinessType;
import cn.harry.sys.entity.SysDept;
import cn.harry.sys.service.SysDeptService;
import cn.harry.sys.vo.TreeSelect;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 部门表
 *
 * @author honghh
 * Date 2020-03-16 08:51:37
 * Copyright (C) www.tech-harry.cn
 */
@Api(tags = "Sys-dept => 部门管理")
@RestController
@RequestMapping("sys/dept")
public class SysDeptController {
    @Resource
    private SysDeptService sysDeptService;

    @ApiOperation("list => 根据关键字获取部门列表")
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping(value = "/list")
    public CommonResult<List<SysDept>> list(Map<String, Object> params) {
        List<SysDept> list = sysDeptService.selectDeptList(params);
        return CommonResult.success(list);
    }

    @ApiOperation("roleDeptTreeselect/{roleId} => 加载对应角色部门列表树")
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public CommonResult<Map<String, Object>> roleDeptTreeselect(@PathVariable("roleId") Long roleId) {

        List<SysDept> depts = sysDeptService.selectDeptList(new HashMap<>());
        Map<String, Object> map = new HashMap<>();
        map.put("checkedKeys", sysDeptService.selectDeptListByRoleId(roleId));
        map.put("depts", sysDeptService.buildDeptTreeSelect(depts));
        return CommonResult.success(map);
    }

    @ApiOperation("treeselect => 获取部门下拉树列表")
    @GetMapping(value = "/treeselect")
    public CommonResult<List<TreeSelect>> treeselect(Map<String, Object> params) {
        List<SysDept> depts = sysDeptService.selectDeptList(params);
        return CommonResult.success(sysDeptService.buildDeptTreeSelect(depts));
    }

    @ApiOperation("{id} => 根据部门编号获取详细信息")
    @PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "/{id}")
    public CommonResult<SysDept> getInfo(@PathVariable Long id) {
        return CommonResult.success(sysDeptService.selectById(id));
    }

    @ApiOperation("create => 新建部门")
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @SysLog(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody SysDept sysDept) {
        int count = sysDeptService.create(sysDept);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改部门状态")
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @SysLog(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = sysDeptService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 修改指定部门")
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @SysLog(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SysDept sysDept) {
        int count = sysDeptService.update(id, sysDept);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("delete/{id} => 删除指定部门")
    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    @SysLog(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = sysDeptService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
