package cn.harry.sys.service;

import cn.harry.sys.vo.SysAreaResult;

import java.util.List;

/**
 * 地区表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
public interface SysAreaService {

    /**
     * 地区
     *
     * @return
     */
    List<SysAreaResult> allList();
}

