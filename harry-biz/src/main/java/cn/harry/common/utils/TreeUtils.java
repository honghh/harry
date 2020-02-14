package cn.harry.common.utils;

import cn.harry.sys.vo.SysAreaResult;
import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TreeUtils
 * Description:
 *
 * @author honghh
 * Date 2019/09/25 11:40
 * Copyright (C) www.tech-harry.cn
 */
public class TreeUtils {
    private final static Long ROOT_ID = 0L;
    private List<SysAreaResult> menuCommon;
    public List<SysAreaResult> list = new ArrayList<SysAreaResult>();

    public List<SysAreaResult> menuList(List<SysAreaResult> menu) {
        this.menuCommon = menu;
        for (SysAreaResult areaResult : menu) {
            SysAreaResult mainCategoryDto = new SysAreaResult();
            if (ROOT_ID.equals(areaResult.getParentId())) {
                BeanUtil.copyProperties(areaResult, mainCategoryDto);
                mainCategoryDto.setChildren(menuChild(areaResult.getId()));
                list.add(mainCategoryDto);
            }
        }
        return list;
    }

    private List<SysAreaResult> menuChild(Long id) {
        List<SysAreaResult> lists = new ArrayList<SysAreaResult>();
        for (SysAreaResult categoryDto : menuCommon) {
            SysAreaResult areaResult = new SysAreaResult();
            if (categoryDto.getParentId().equals(id)) {
                BeanUtil.copyProperties(categoryDto, areaResult);
                areaResult.setChildren(menuChild(categoryDto.getId()));
                lists.add(areaResult);
            }
        }
        return lists;
    }
}
