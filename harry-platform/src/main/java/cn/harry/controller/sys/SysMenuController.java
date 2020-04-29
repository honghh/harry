package cn.harry.controller.sys;

import cn.harry.common.annotation.SysLog;
import cn.harry.common.api.CommonResult;
import cn.harry.common.enums.BusinessType;
import cn.harry.common.utils.SysUserUtils;
import cn.harry.sys.entity.SysMenu;
import cn.harry.sys.service.SysMenuService;
import cn.harry.sys.vo.RouterVo;
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
 * ClassName: SysMenuController
 * Description:
 *
 * @author honghh
 * Date 2019/10/17 10:00
 * Copyright (C) www.honghh.top
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "Sys-menu => 系统菜单管理")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @ApiOperation("list => 获取菜单列表")
    @GetMapping("list")
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    public CommonResult<List<SysMenu>> list(SysMenu menu) {
        List<SysMenu> menus = sysMenuService.selectMenuList(menu, SysUserUtils.getSysUser().getId());
        return CommonResult.success(menus);
    }

    @ApiOperation("{id} => 根据菜单ID获取详细信息")
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping("/{id}")
    public CommonResult<SysMenu> info(@PathVariable("id") Long id) {
        SysMenu menus = sysMenuService.getById(id);
        return CommonResult.success(menus);
    }

    @ApiOperation("getRouters => 获取路由信息")
    @GetMapping("getRouters")
    public CommonResult<List<RouterVo>> getRouters() {
        List<SysMenu> menus = sysMenuService.selectMenuTreeByUserId(SysUserUtils.getSysUser().getId());
        return CommonResult.success(sysMenuService.buildMenus(menus));
    }

    @ApiOperation("treeselect => 获取菜单下拉树列表")
    @GetMapping(value = "/treeselect")
    public CommonResult<List<TreeSelect>> treeselect(SysMenu menu) {
        List<SysMenu> menus = sysMenuService.selectMenuList(menu, SysUserUtils.getSysUser().getId());
        return CommonResult.success(sysMenuService.buildMenuTreeSelect(menus));
    }

    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    @ApiOperation("roleMenuTreeselect/{roleId} => 加载对应角色菜单列表树")
    public CommonResult<Map<String, Object>> roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        List<SysMenu> menus = sysMenuService.selectMenuList(SysUserUtils.getSysUser().getId());
        Map<String, Object> map = new HashMap<>();
        map.put("checkedKeys", sysMenuService.selectMenuListByRoleId(roleId));
        map.put("menus", sysMenuService.buildMenuTreeSelect(menus));
        return CommonResult.success(map);
    }


    @ApiOperation("create => 新增菜单")
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @SysLog(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody SysMenu menu) {
        int count = sysMenuService.create(menu);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update => 修改菜单")
    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @SysLog(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Long id, @RequestBody SysMenu menu) {
        int count = sysMenuService.update(id, menu);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("{id} => 删除菜单")
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @SysLog(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable("id") Long id) {
        int count = sysMenuService.deleteById(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
