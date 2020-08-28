package cn.harry.sys.entity;

import cn.hutool.core.date.DatePattern;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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
    @ExcelProperty("ID")
    @ColumnWidth(10)
    private Long id;
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    @ExcelProperty("字典名称")
    @ColumnWidth(15)
    @TableField(condition = SqlCondition.LIKE)
    private String name;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    @ExcelProperty("字典类型")
    @ColumnWidth(15)
    @TableField(condition = SqlCondition.LIKE)
    private String type;

    /**
     * 状态（1正常 0停用）
     */
    @ExcelProperty("状态「1正常 0停用」")
    @ColumnWidth(25)
    @ApiModelProperty(value = "状态")
    private String status;
    /**
     * 描述
     */
    @ExcelProperty("描述")
    @ColumnWidth(20)
    @ApiModelProperty(value = "描述")
    private String remark;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @ExcelProperty("修改时间")
    @ColumnWidth(20)
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;
    /**
     * 有效状态：0->无效；1->有效
     */
    @ExcelIgnore
    @TableLogic
    @ApiModelProperty(value = "有效状态：0->无效；1->有效")
    private Integer valid;


    /**
     * 开始时间
     */
    @ExcelIgnore
    @JsonIgnore
    @TableField(exist = false)
    private String beginTime;

    /**
     * 结束时间
     */
    @ExcelIgnore
    @JsonIgnore
    @TableField(exist = false)
    private String endTime;

}
