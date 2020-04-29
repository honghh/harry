package cn.harry.sys.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 后台用户表
 *
 * @author honghh
 * Date 2019-10-14 10:39:25
 * Copyright (C) www.honghh.top
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    private Long deptId;
    /**
     * 所属部门名称
     */
    @ApiModelProperty(value = "所属部门名称")
    private String deptName;
    /**
     * 所属岗位
     */
    @ApiModelProperty(value = "所属岗位")
    private String postIds;
    /**
     * 登陆用户名
     */
    @ApiModelProperty(value = "登陆用户名")
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String icon;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号")
    private String phone;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String note;

    /**
     * 最后登陆IP
     */
    @ApiModelProperty(value = "最后登陆IP")
    private String loginIp;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date loginTime;
    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private String status;
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

    /**
     * 角色ID列表
     */
    @ApiModelProperty(value = "角色ID列表")
    @TableField(exist = false)
    private List<Long> roleIds;

}
