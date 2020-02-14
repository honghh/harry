package cn.harry.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: SysUserUpdatePasswordParam
 * Description: 修改密码参数
 *
 * @author honghh
 * Date 2019/10/24 10:00
 * Copyright (C) www.tech-harry.cn
 */
@Data
public class SysUserCreatByMemberParam {
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "手机号码")
    private String username;

    @ApiModelProperty(value = "商家信息id")
    private Long merchantId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 店铺名称(数字、中文，英文(可混合，不可有特殊字符)，可修改)、不唯一
     */
    @ApiModelProperty(value = "店铺名称(数字、中文，英文(可混合，不可有特殊字符)，可修改)、不唯一")
    private String shopName;
}
