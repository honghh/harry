package cn.harry.sys.enums;

/**
 * 菜单类型
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
