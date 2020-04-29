package cn.harry.sys.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_login_log")
public class SysUserLoginLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;

    @ApiModelProperty(value = "key")
    private String keyword;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @TableField(condition = SqlCondition.LIKE)
    private String username;
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 登陆IP
     */
    @ApiModelProperty(value = "登陆IP")
    private String ip;
    /**
     * 登陆地点
     */
    @ApiModelProperty(value = "登陆地点")
    @TableField(condition = SqlCondition.LIKE)
    private String address;
    /**
     * 浏览器登录类型
     */
    @ApiModelProperty(value = "浏览器登录类型")
    private String userAgent;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date modifyTime;
    /**
     * 有效状态：0->无效；1->有效
     */
    @ApiModelProperty(value = "有效状态：0->无效；1->有效")
    @TableLogic
    private Integer valid;

    /** 开始时间 */
    @JsonIgnore
    @TableField(exist = false)
    private String beginTime;

    /** 结束时间 */
    @JsonIgnore
    @TableField(exist = false)
    private String endTime;

    public SysUserLoginLog(String keyword, Long userId, String username, String nickName, String ip, String address, String userAgent, Date createTime, Date modifyTime) {
        this.keyword = keyword;
        this.userId = userId;
        this.username = username;
        this.nickName = nickName;
        this.ip = ip;
        this.address = address;
        this.userAgent = userAgent;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }
}
