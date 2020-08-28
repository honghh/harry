package cn.harry.controller.monitor;

import cn.harry.common.annotation.SysLog;
import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.common.enums.BusinessType;
import cn.harry.monitor.entity.SysOperationLog;
import cn.harry.monitor.service.SysOperationLogService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: SysOperationLogController
 * Description: 操作日志
 *
 * @author honghh
 * Date 2020/04/28 11:10
 * Copyright (C) www.honghh.top
 */
@RestController
@Api(tags = "Monitor-operlog => 用户操作日志")
@RequestMapping("/monitor/operlog")
public class SysOperationLogController {
    @Resource
    private SysOperationLogService sysOperationLogService;

    @ApiOperation("list => 根据关键字获取配置列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SysOperationLog>> list(SysOperationLog sysOperationLog,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<SysOperationLog> page = sysOperationLogService.getPage(sysOperationLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @SysLog(title = "操作日志", businessType = BusinessType.DELETE)
    @ApiOperation("deleteByIds/{ids} => 删除指定操作日志")
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = sysOperationLogService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("clean => 清空记录")
    @SysLog(title = "操作日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public CommonResult clean() {
        sysOperationLogService.clean();
        return CommonResult.success();

    }

    @ApiOperation("export => 按条件导出（不分页）")
    @SysLog(title = "操作日志", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(HttpServletResponse response, SysOperationLog sysOperationLog) {
        List<SysOperationLog> list = sysOperationLogService.getExportList(sysOperationLog);
        try {
            EasyExcel.write(response.getOutputStream(), SysOperationLog.class).sheet("操作日志").doWrite(list);
            return CommonResult.success();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();

    }
}
