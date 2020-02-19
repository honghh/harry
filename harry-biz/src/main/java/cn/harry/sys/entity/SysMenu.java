package cn.harry.sys.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 后台用户权限表
 *
 * @author honghh
 * Date 2019-10-14 10:39:24
 * Copyright (C) www.honghh.top
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 父级权限id
     */
    @ApiModelProperty(value = "父级权限id")
    private Long pid;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 权限值
     */
    @ApiModelProperty(value = "权限值")
    private String value;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;
    /**
     * 前端资源路径
     */
    @ApiModelProperty(value = "前端资源路径")
    private String uri;
    /**
     * 启用状态；0->禁用；1->启用
     */
    @ApiModelProperty(value = "启用状态；0->禁用；1->启用")
    private Integer status;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date createTime;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableField(exist = false)
    private List<?> list;
}
