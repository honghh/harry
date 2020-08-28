package cn.harry.controller.test;

import cn.harry.common.annotation.IgnoreAuth;
import cn.harry.common.api.CommonResult;
import cn.harry.oss.vo.OssPolicyResult;
import cn.harry.sys.entity.SysDict;
import cn.harry.sys.service.SysDictService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

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

    @Resource
    private SysDictService sysDictService;
    @IgnoreAuth
    @GetMapping(value = "/hello")
    public CommonResult<OssPolicyResult> hello() {

        return CommonResult.success();
    }
    @IgnoreAuth
    @GetMapping("/export")
    public void export(HttpServletResponse response, SysDict sysDict) {
        IPage<SysDict> page = sysDictService.getPage(sysDict, 10, 1);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode("测试", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), SysDict.class).sheet("模板").doWrite(page.getRecords());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
