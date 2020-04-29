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
 * 数据字典
 *
 * @author honghh
 * Date 2020-03-16 09:53:38
 * Copyright (C) www.tech-harry.cn
 */
@Data
@TableName("sys_dict")
public class SysDict implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    @TableField(condition = SqlCondition.LIKE)
    private String name;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    @TableField(condition = SqlCondition.LIKE)
    private String type;

    /**
     * 状态（1正常 0停用）
     */
    @ApiModelProperty(value = "状态")
    private String status;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remark;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;
    /**
     * 有效状态：0->无效；1->有效
     */
    @TableLogic
    @ApiModelProperty(value = "有效状态：0->无效；1->有效")
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
