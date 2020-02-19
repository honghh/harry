package cn.harry.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: SysConfigResult
 * Description:
 *
 * @author honghh
 * Date 2019/12/04 11:40
 * Copyright (C) www.honghh.top
 */
@Data
public class SysConfigResult {
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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
