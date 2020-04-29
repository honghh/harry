package cn.harry.controller.monitor;

import cn.harry.common.annotation.SysLog;
import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.common.enums.BusinessType;
import cn.harry.sys.entity.SysUserLoginLog;
import cn.harry.sys.service.SysUserLoginLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName: SysLoginLogController
 * Description: 用户登陆日志
 *
 * @author honghh
 * Date 2020/04/28 11:09
 * Copyright (C) www.honghh.top
 */
@RestController
@Api(tags = "Monitor-loginLog => 用户登陆日志")
@RequestMapping("/monitor/loginLog")
public class SysLoginLogController {
    @Resource
    private SysUserLoginLogService sysUserLoginLogService;

    @ApiOperation("list => 根据关键字获取配置列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SysUserLoginLog>> list(SysUserLoginLog loginLog,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<SysUserLoginLog> page = sysUserLoginLogService.getPage(loginLog, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @SysLog(title = "登陆日志", businessType = BusinessType.DELETE)
    @ApiOperation("deleteByIds/{ids} => 删除指定登陆日志")
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = sysUserLoginLogService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("clean => 清空记录")
    @SysLog(title = "登陆日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public CommonResult<Integer> clean() {
        sysUserLoginLogService.clean();
        return CommonResult.success();
    }
}
