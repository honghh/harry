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
 * 短信模板
 *
 * @author honghh
 * Date 2019-10-14 10:39:24
 * Copyright (C) www.tech-harry.cn
 */
@Data
@TableName("sys_sms_template")
public class SysSmsTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long value;
    /**
     * 作用
     */
    @ApiModelProperty(value = "作用")
    private String name;
    /**
     * 短信签名
     */
    @ApiModelProperty(value = "短信签名")
    private String signName;
    /**
     * 来源平台
     */
    @ApiModelProperty(value = "来源平台")
    private Integer source;
    /**
     * 短信模板CODE
     */
    @ApiModelProperty(value = "短信模板CODE")
    private String templateCode;
    /**
     * 模板内容
     */
    @ApiModelProperty(value = "模板内容")
    private String content;
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
