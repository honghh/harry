package cn.harry.sys.enums;

/**
 * 菜单类型
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
 */
public enum MenuTypeEnums {
    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private int value;

    MenuTypeEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
