package cn.harry.sys.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置信息表/枚举值表
 *
 * @author honghh
 * Date 2019-11-25 16:36:48
 * Copyright (C) www.tech-harry.cn
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
     * type 如：order_cancel_reason
     */
    @ApiModelProperty(value = "type 如：order_cancel_reason")
    private String paramType;
    /**
     * key
     */
    @ApiModelProperty(value = "key")
    private String paramKey;
    /**
     * name
     */
    @ApiModelProperty(value = "name")
    private String paramName;
    /**
     * 状态   0：禁用   1：启用
     */
    @ApiModelProperty(value = "状态   0：禁用   1：启用")
    private Integer status;
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

}
