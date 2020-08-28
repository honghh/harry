package cn.harry.controller.sys;

import cn.harry.common.annotation.SysLog;
import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.common.enums.BusinessType;
import cn.harry.sys.entity.SysDict;
import cn.harry.sys.service.SysDictService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 数据字典
 *
 * @author honghh
 * Date 2020-03-16 09:53:38
 * Copyright (C) www.tech-harry.cn
 */
@Api(tags = "Sys-dict => 字典管理")
@RestController
@RequestMapping("sys/dict")
public class SysDictController {
    @Resource
    private SysDictService sysDictService;

    @ApiOperation("list => 根据关键字获取字典列表")
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SysDict>> list(SysDict sysDict,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<SysDict> page = sysDictService.getPage(sysDict, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * 查询字典详细
     */
    @ApiOperation("{id} => 获取字典信息")
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{id}")
    public CommonResult<SysDict> getInfo(@PathVariable Long id) {
        return CommonResult.success(sysDictService.selectById(id));
    }

    @ApiOperation("create => 新建字典")
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody SysDict sysDict) {
        int count = sysDictService.create(sysDict);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改字典状态")
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @SysLog(title = "字典数据", businessType = BusinessType.INSERT)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = sysDictService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 修改指定字典")
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @SysLog(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SysDict sysDict) {
        int count = sysDictService.update(id, sysDict);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("deleteByIds/{ids} => 删除指定字典")
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @SysLog(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = sysDictService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("export => 按条件导出（不分页）")
    @SysLog(title = "字典数据", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:dict:export')")
    @GetMapping("/export")
    public CommonResult export(HttpServletResponse response, SysDict sysDict) {
        List<SysDict> list = sysDictService.getExportList(sysDict);
        try {
            EasyExcel.write(response.getOutputStream(), SysDict.class).sheet("字典数据").doWrite(list);
            return CommonResult.success();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();

    }

}
