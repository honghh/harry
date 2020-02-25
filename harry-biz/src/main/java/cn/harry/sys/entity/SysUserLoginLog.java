package cn.harry.sys.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户登录日志表
 *
 * @author honghh
 * Date 2019-10-14 10:39:25
 * Copyright (C) www.honghh.top
 */
@Data
@TableName("sys_user_login_log")
public class SysUserLoginLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     *
     */
    private Long adminId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date createTime;
    /**
     *
     */
    private String ip;
    /**
     *
     */
    private String address;
    /**
     * 浏览器登录类型
     */
    @ApiModelProperty(value = "浏览器登录类型")
    private String userAgent;

}
