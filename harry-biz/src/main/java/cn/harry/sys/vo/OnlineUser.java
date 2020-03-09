package cn.harry.sys.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: OnlineUser
 * Description:
 *
 * @author honghh
 * Date 2020/03/07 16:12
 * Copyright (C) www.honghh.top
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUser {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    /**
     * 岗位
     */
    @ApiModelProperty(value = "岗位")
    private String job;
    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    private String browser;
    /**
     * 登陆IP
     */
    @ApiModelProperty(value = "登陆IP")
    private String ip;
    /**
     * 登陆地点
     */
    @ApiModelProperty(value = "登陆地点")
    private String address;
    /**
     * key
     */
    @ApiModelProperty(value = "key")
    private String key;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date loginTime;


}
