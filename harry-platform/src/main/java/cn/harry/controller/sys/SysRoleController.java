package cn.harry.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.sys.entity.SysRole;
import cn.harry.sys.service.SysRoleMenuService;
import cn.harry.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: SysRoleController
 * Description:
 *
 * @author honghh
 * Date 2019/10/17 10:00
 * Copyright (C) www.tech-harry.cn
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "SysRoleController", description = "角色管理")
public class SysRoleController {
    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private SysRoleService sysRoleService;

    @ApiOperation("根据名获取角色列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SysRole>> list(@RequestParam(value = "keyword", required = false) String name,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<SysRole> roles = sysRoleService.getPage(name, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roles));
    }

    @ApiOperation("修改指定角色信息")
    @PostMapping(value = "/create")
    public CommonResult update(@RequestBody SysRole sysRole) {
        int count = sysRoleService.create(sysRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定角色信息")
    @PutMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody SysRole sysRole) {
        int count = sysRoleService.update(id, sysRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除指定角色信息")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = sysRoleService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("角色信息")
    @GetMapping("/info/{roleId}")
    public CommonResult<SysRole> info(@PathVariable("roleId") Long roleId){
        SysRole role = sysRoleService.getById(roleId);

        //查询角色对应的菜单
        List<Long> menuList = sysRoleMenuService.listMenuIdByRoleId(roleId);
        role.setMenuIdList(menuList);

        return CommonResult.success(role);
    }


}
