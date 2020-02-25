package cn.harry.controller.sys;

import cn.harry.common.api.CommonResult;
import cn.harry.common.exception.ApiException;
import cn.harry.sys.enums.MenuTypeEnums;
import cn.hutool.core.util.StrUtil;
import cn.harry.common.constant.CommonConstant;
import cn.harry.common.utils.SysUserUtils;
import cn.harry.sys.entity.SysMenu;
import cn.harry.sys.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

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
@Api(tags = "SysMenuController", description = "系统菜单管理")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @GetMapping("/nav")
    @ApiOperation(value = "获取用户所拥有的菜单和权限", notes = "通过登陆用户的userId获取用户所拥有的菜单和权限")
    public CommonResult nav() {
        List<SysMenu> menuList = sysMenuService.listMenuByUserId(SysUserUtils.getSysUser().getId());
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", menuList);
        return CommonResult.success(map);
    }


    @ApiOperation("所有菜单列表")
    @GetMapping("/list")
    public CommonResult<List<SysMenu>> list() {
        List<SysMenu> menuList = sysMenuService.list();
        return CommonResult.success(menuList);
    }

    /**
     * 所有菜单列表(用于新建、修改角色时 获取菜单的信息)
     */
    @GetMapping("/listSimpleMenuNoButton")
    @ApiOperation(value = "获取用户所拥有的菜单(不包括按钮)", notes = "通过登陆用户的userId获取用户所拥有的菜单和权限")
    public CommonResult<List<SysMenu>> listSimpleMenuNoButton() {
        List<SysMenu> sysMenuList = sysMenuService.listSimpleMenuNoButton();
        return CommonResult.success(sysMenuList);
    }

    @ApiOperation("菜单信息")
    @GetMapping("/info/{id}")
    public CommonResult<SysMenu> info(@PathVariable("id") Long id) {
        SysMenu menu = sysMenuService.getById(id);
        return CommonResult.success(menu);
    }

    @ApiOperation("删除指定菜单信息")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        if (id <= CommonConstant.SYS_MENU_MAX_ID) {
            return CommonResult.failed("系统菜单，不能删除");
        }
        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.listChildrenMenuByParentId(id);
        if (menuList.size() > 0) {
            return CommonResult.failed("请先删除子菜单或按钮");
        }
        boolean re = sysMenuService.deleteMenuAndRoleMenu(id);
        if (re) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    /**
     * 保存
     */

    @ApiOperation("保存指定菜单信息")
    @PostMapping(value = "/create")
    public CommonResult save(@Valid @RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);
        sysMenuService.save(menu);
        return CommonResult.success("");
    }

    /**
     * 修改
     */

    @ApiOperation("修改指定菜单信息")
    @PutMapping(value = "/update/{id}")
    public CommonResult<String> update(@PathVariable Long id, @Valid @RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);

        if (menu.getType() == MenuTypeEnums.MENU.getValue()) {
            if (StrUtil.isBlank(menu.getUri())) {
                return CommonResult.validateFailed("菜单URL不能为空");
            }
        }
        menu.setId(id);
        sysMenuService.updateById(menu);

        return CommonResult.success("");
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu) {

        if (menu.getType() == MenuTypeEnums.MENU.getValue()) {
            if (StrUtil.isBlank(menu.getUri())) {
                throw new ApiException("菜单URL不能为空");
            }
        }
        if (Objects.equals(menu.getId(), menu.getPid())) {
            throw new ApiException("自己不能是自己的上级");
        }

        //上级菜单类型
        int parentType = MenuTypeEnums.CATALOG.getValue();
        if (menu.getPid() != 0) {
            SysMenu parentMenu = sysMenuService.getById(menu.getPid());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == MenuTypeEnums.CATALOG.getValue() ||
                menu.getType() == MenuTypeEnums.MENU.getValue()) {
            if (parentType != MenuTypeEnums.CATALOG.getValue()) {
                throw new ApiException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == MenuTypeEnums.BUTTON.getValue()) {
            if (parentType != MenuTypeEnums.MENU.getValue()) {
                throw new ApiException("上级菜单只能为菜单类型");
            }
        }
    }
}
