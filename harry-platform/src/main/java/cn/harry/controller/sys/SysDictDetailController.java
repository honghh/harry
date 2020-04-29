package cn.harry.controller.sys;

import cn.harry.common.annotation.SysLog;
import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.common.enums.BusinessType;
import cn.harry.sys.entity.SysDictDetail;
import cn.harry.sys.service.SysDictDetailService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 数据字典详情详情详情
 *
 * @author honghh
 * Date 2020-03-16 09:53:38
 * Copyright (C) www.tech-harry.cn
 */
@Api(tags = "Sys-dict => 字典详情管理")
@RestController
@RequestMapping("sys/dict/detail")
public class SysDictDetailController {
    @Resource
    private SysDictDetailService sysDictDetailService;

    @ApiOperation("list => 全局 根据字典详情类型查询字典详情数据信息")
    @GetMapping(value = "/dictType/{dictType}")
    public CommonResult<List<SysDictDetail>> dictType(@PathVariable String dictType) {
        return CommonResult.success(sysDictDetailService.selectDictDataByType(dictType));
    }

    @ApiOperation("list => 根据关键字获取字典详情列表")
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SysDictDetail>> list(SysDictDetail dictDetail,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<SysDictDetail> page = sysDictDetailService.getPage(dictDetail, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("{id} => 获取字典详情信息")
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{id}")
    public CommonResult<SysDictDetail> getInfo(@PathVariable Long id) {
        return CommonResult.success(sysDictDetailService.selectById(id));
    }

    @ApiOperation("create => 新建字典详情")
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @SysLog(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody SysDictDetail dictDetail) {
        int count = sysDictDetailService.create(dictDetail);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改字典详情状态")
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @SysLog(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = sysDictDetailService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 修改指定字典详情")
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @SysLog(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SysDictDetail dictDetail) {
        int count = sysDictDetailService.update(id, dictDetail);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("deleteByIds/{ids} => 删除指定字典详情")
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @SysLog(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = sysDictDetailService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


}
