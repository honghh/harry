package cn.harry.controller.sys;

import cn.harry.common.api.CommonPage;
import cn.harry.common.api.CommonResult;
import cn.harry.sys.service.OnlineUserService;
import cn.harry.sys.vo.OnlineUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: SysOnlineUserController
 * Description:
 *
 * @author honghh
 * Date 2019/10/15 12:42
 * Copyright (C) www.honghh.top
 */
@RestController
@Api(tags = "Sys-online => 在线用户管理")
@RequestMapping("/sys/online")
public class SysOnlineUserController {
    @Resource
    private OnlineUserService onlineUserService;

    @ApiOperation("list => 根据关键字分页获取用户列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<OnlineUser>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OnlineUser> onlineUsers = onlineUserService.getAll(keyword);
        return CommonResult.success(CommonPage.listToPage(pageNum , pageSize, onlineUsers));

    }

}

