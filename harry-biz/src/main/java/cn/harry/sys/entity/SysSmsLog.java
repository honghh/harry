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
 * 短信日志表
 *
 * @author honghh
 * Date 2019-10-14 10:39:25
 * Copyright (C) www.tech-harry.cn
 */
@Data
@TableName("sys_sms_log")
public class SysSmsLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 短信记录id
     */
    @TableId
    @ApiModelProperty(value = "短信记录id")
    private Long id;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobiles;
    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String param;
    /**
     * 短信内容
     */
    @ApiModelProperty(value = "短信内容")
    private String content;
    /**
     * 短信类型 10 单发 20 群发
     */
    @ApiModelProperty(value = "短信类型 10 单发 20 群发")
    private Integer type;
    /**
     * 短信类型名称
     */
    @ApiModelProperty(value = "短信类型名称")
    private String typeName;
    /**
     * 模板号
     */
    @ApiModelProperty(value = "模板号")
    private String templateCode;
    /**
     * 结果成功失败 0 失败 1 成功
     */
    @ApiModelProperty(value = "结果成功失败 0 失败 1 成功")
    private Integer status;
    /**
     * 第三方返回结果
     */
    @ApiModelProperty(value = "第三方返回结果")
    private String result;
    /**
     * 来源平台
     */
    @ApiModelProperty(value = "来源平台")
    private Integer source;
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
    private Integer valid;

}
