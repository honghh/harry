package cn.harry.sys.service;

import cn.harry.sys.entity.SysCountry;

import java.util.List;

/**
 * 世界国旗图标
 *
 * @author honghh
 * Date 2019-10-22 10:38:46
 * Copyright (C) www.honghh.top
 */
public interface SysCountryService {

    /**
     * 获取全部世界国旗图标
     *
     * @return
     */
    List<SysCountry> allList();
}

