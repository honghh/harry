package cn.harry.sys.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置信息表/枚举值表
 *
 * @author honghh
 * Date 2019-11-25 16:36:48
 * Copyright (C) www.honghh.top
 */
@Data
@TableName("sys_config")
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 系统内置: Y N
     */
    @ApiModelProperty(value = "系统内置: Y N ")
    private String paramType;
    /**
     * key
     */
    @ApiModelProperty(value = "key")
    @TableField(condition = SqlCondition.LIKE)
    private String paramKey;
    /**
     * value
     */
    @ApiModelProperty(value = "value")
    private String paramValue;
    /**
     * name
     */
    @ApiModelProperty(value = "name")
    @TableField(condition = SqlCondition.LIKE)
    private String paramName;
    /**
     * 状态   0：禁用   1：启用
     */
    @ApiModelProperty(value = "状态   0：禁用   1：启用")
    private String status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
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
}
