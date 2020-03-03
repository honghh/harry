package cn.harry.controller.sys;

import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.sys.entity.SysConfig;
import cn.harry.sys.service.SysConfigService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName: SysConfigController
 * Description: 系统配置信息表/枚举值表
 *
 * @author honghh
 * Date 2020/02/18 13:57
 * Copyright (C) www.honghh.top
 */
@RestController
@Api(tags = "Sys-config => 系统配置信息")
@RequestMapping("/sys/config")
public class SysConfigController {
    @Resource
    private SysConfigService sysConfigService;


    @ApiOperation("list => 根据关键字获取配置列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SysConfig>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<SysConfig> page = sysConfigService.getPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("create => 新建配置参数")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody SysConfig sysConfig) {
        int count = sysConfigService.create(sysConfig);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改配置参数状态")
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, Integer status) {
        int count = sysConfigService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 修改指定配置参数")
    @PutMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody SysConfig sysConfig) {
        int count = sysConfigService.update(id, sysConfig);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("delete/{id} => 删除指定配置参数")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = sysConfigService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}

