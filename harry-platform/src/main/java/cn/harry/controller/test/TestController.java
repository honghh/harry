package cn.harry.controller.test;

import cn.harry.common.annotation.IgnoreAuth;
import cn.harry.common.api.CommonResult;
import cn.harry.oss.vo.OssPolicyResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController
 * Description:
 *
 * @author honghh
 * Date 2020/05/18 16:09
 * Copyright (C) 洛阳乾发供应链管理有限公司
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @IgnoreAuth
    @GetMapping(value = "/hello")
    public CommonResult<OssPolicyResult> hello() {

        return CommonResult.success();
    }

}
