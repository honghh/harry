package cn.harry.monitor.entity;

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
 * ClassName: SysOperLog
 * Description:操作记录表
 *
 * @author honghh
 * Date 2020/04/20 10:02
 * Copyright (C) www.honghh.top
 */
@Data
@TableName("sys_operation_log")
public class SysOperationLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    @TableId
    @ExcelProperty("ID")
    @ColumnWidth(15)
    private Long id;

    /**
     * 操作模块
     */
    @ExcelProperty("操作模块")
    @ColumnWidth(15)
    @ApiModelProperty(value = "操作模块")
    @TableField(condition = SqlCondition.LIKE)
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */

    @ExcelProperty("业务类型")
    @ColumnWidth(15)
    @ApiModelProperty(value = "业务类型 0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据")
    private Integer businessType;

    /**
     * 请求方法
     */

    @ExcelProperty("请求方法")
    @ColumnWidth(15)
    @ApiModelProperty(value = "请求方法")
    private String method;

    /**
     * 请求方式
     */

    @ExcelProperty("请求方式")
    @ColumnWidth(15)
    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */

    @ExcelProperty("操作类别")
    @ColumnWidth(15)
    @ApiModelProperty(value = "操作类别")
    private Integer operatorType;

    /**
     * 操作人员
     */

    @ExcelProperty("操作人员")
    @ColumnWidth(15)
    @ApiModelProperty(value = "操作人员")
    @TableField(condition = SqlCondition.LIKE)
    private String operName;

    /**
     * 部门名称
     */

    @ExcelProperty("部门名称")
    @ColumnWidth(15)
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**
     * 请求url
     */

    @ExcelProperty("请求地址")
    @ColumnWidth(15)
    @ApiModelProperty(value = "请求地址")
    private String operUrl;

    /**
     * 操作地址
     */

    @ExcelProperty("操作地址")
    @ColumnWidth(15)
    @ApiModelProperty(value = "操作地址")
    private String operIp;

    /**
     * 操作地点
     */

    @ExcelProperty("操作地点")
    @ColumnWidth(15)
    @ApiModelProperty(value = "操作地点")
    private String operLocation;

    /**
     * 请求参数
     */

    @ExcelProperty("请求参数")
    @ColumnWidth(15)
    @ApiModelProperty(value = "请求参数")
    private String operParam;

    /**
     * 返回参数
     */

    @ExcelProperty("返回参数")
    @ColumnWidth(15)
    @ApiModelProperty(value = "返回参数")
    private String jsonResult;

    /**
     * 操作状态（1正常 0异常）
     */

    @ExcelProperty("状态")
    @ColumnWidth(15)
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 错误消息
     */

    @ExcelProperty("错误消息")
    @ColumnWidth(15)
    @ApiModelProperty(value = "错误消息")
    private String errorMsg;


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
