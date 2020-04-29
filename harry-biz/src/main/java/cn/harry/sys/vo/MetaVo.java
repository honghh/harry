package cn.harry.sys.vo;

import lombok.Data;

/**
 * ClassName: MetaVo
 * Description:
 *
 * @author honghh
 * Date 2020/04/13 12:43
 * Copyright (C) www.honghh.top
 */
@Data
public class MetaVo {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;

    public MetaVo() {
    }

    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
}
