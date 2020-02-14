package cn.harry.sys.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片验证码
 *
 * @author honghh
 * Date 2019-10-14 10:39:24
 * Copyright (C) www.tech-harry.cn
 */
@Data
@TableName("sys_captcha")
public class SysCaptcha implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid")
    private String uuid;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String code;
    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date expireTime;

}
