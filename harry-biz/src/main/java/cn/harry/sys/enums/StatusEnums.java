package cn.harry.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: StatusEnums
 * Description:
 *
 * @author honghh
 * Date 2020/04/13 11:18
 * Copyright (C) www.honghh.top
 */
@Getter
@AllArgsConstructor
public enum StatusEnums {
    /**
     * 禁用
     */
    DISABLE("0", "禁用"),
    /**
     * 启用
     */
    ENABLE("1", "启用"),
    ;


    private String key;
    private String name;
}
