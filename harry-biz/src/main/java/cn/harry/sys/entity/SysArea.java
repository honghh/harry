package cn.harry.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 地区表
 *
 * @author honghh
 * Date 2019-10-14 10:39:24
 * Copyright (C) www.tech-harry.cn
 */
@Data
@TableName("sys_area")
public class SysArea implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     * 地区名称
     */
    @ApiModelProperty(value = "地区名称")
    private String areaName;
    /**
     * 上级id
     */
    @ApiModelProperty(value = "上级id")
    private Long parentId;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private Integer level;

}
