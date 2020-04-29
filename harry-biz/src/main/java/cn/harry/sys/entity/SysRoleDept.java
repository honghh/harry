package cn.harry.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色部门关联
 *
 * @author honghh
 * Date 2020-03-16 08:51:37
 * Copyright (C) www.tech-harry.cn
 */
@Data
@TableName("sys_role_dept")
public class SysRoleDept implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     *
     */
    @ApiModelProperty(value = "roleId")
    private Long roleId;
    /**
     *
     */
    @ApiModelProperty(value = "deptId")
    private Long deptId;

}
