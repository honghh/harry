package cn.harry.controller.oss;


import cn.harry.common.api.CommonResult;
import cn.harry.oss.impl.OssServiceImpl;
import cn.harry.oss.vo.OssCallbackResult;
import cn.harry.oss.vo.OssPolicyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: OssController
 * Description: Oss相关操作接口
 *
 * @author honghh
 * Date 2019/10/08 14:58
 * Copyright (C) www.honghh.top
 */

@RestController
@Api(tags = "Oss-aliyun => 文件管理")
@RequestMapping("/oss/aliyun")
public class OssController {
    @Resource
    private OssServiceImpl ossService;

    @ApiOperation(value = "policy => 上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "callback => 上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }

}
