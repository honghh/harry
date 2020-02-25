package cn.harry.sys.service.impl;

import cn.harry.common.utils.BeanUtils;
import cn.harry.common.utils.TreeUtils;
import cn.harry.sys.dao.SysAreaDao;
import cn.harry.sys.entity.SysArea;
import cn.harry.sys.service.SysAreaService;
import cn.harry.sys.vo.SysAreaResult;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 地区表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Service("sysAreaService")
public class SysAreaServiceImpl extends ServiceImpl<SysAreaDao, SysArea> implements SysAreaService {

    @Override
    public List<SysAreaResult> allList() {
        List<SysAreaResult> mainCategoryList = new ArrayList<>();
        TreeUtils treeUtils = new TreeUtils();
        List<SysArea> list = list();
        if (CollectionUtil.isNotEmpty(list)) {
            List<SysAreaResult> results = BeanUtils.transformList(SysAreaResult.class, list);
            mainCategoryList = treeUtils.menuList(results);
        }

        return mainCategoryList;
    }
}