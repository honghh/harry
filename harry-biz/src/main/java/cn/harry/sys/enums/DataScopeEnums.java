package cn.harry.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: DataScopeEnums
 * Description:
 *
 * @author honghh
 * Date 2020/04/28 17:21
 * Copyright (C) www.honghh.top
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnums {
    // 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限）
    ALL(1, "所有数据权限"),
    CUSTOMIZE(2, "自定义数据权限"),
    SELF(3, "本部门数据权限"),
    ;
    private Integer key;
    private String name;
}

