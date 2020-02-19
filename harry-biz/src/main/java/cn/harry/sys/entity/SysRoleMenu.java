package cn.harry.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台用户角色和权限关系表
 *
 * @author honghh
 * Date 2019-10-14 10:39:25
 * Copyright (C) www.honghh.top
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     *
     */
    private Long roleId;
    /**
     *
     */
    private Long menuId;

}
