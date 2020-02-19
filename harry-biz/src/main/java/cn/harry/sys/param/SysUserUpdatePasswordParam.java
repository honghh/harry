package cn.harry.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: SysUserUpdatePasswordParam
 * Description: 修改密码参数
 *
 * @author honghh
 * Date 2019/10/24 10:00
 * Copyright (C) www.honghh.top
 */
@Data
public class SysUserUpdatePasswordParam {
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}
