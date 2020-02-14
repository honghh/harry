package cn.harry.sys.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: SysAreaResult
 * Description:
 *
 * @author honghh
 * Date 2019/09/25 11:48
 * Copyright (C) www.tech-harry.cn
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysAreaResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 地区名称
     *
     * @mbggenerated
     */
    private String areaName;

    /**
     * 上级id
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * 层级
     *
     * @mbggenerated
     */
    private Integer level;
    /**
     * 子目录
     */
    @ApiModelProperty(value = "子目录")
    private List<SysAreaResult> children;

}
