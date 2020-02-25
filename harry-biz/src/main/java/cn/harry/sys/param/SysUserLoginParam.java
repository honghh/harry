package cn.harry.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录参数
 *
 * @author honghh
 * Date 2019/08/30 11:19
 * Copyright (C) www.honghh.top
 */
@Data
public class SysUserLoginParam {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
