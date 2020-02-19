package cn.harry.sys.service.impl;

import cn.harry.sys.entity.SysCountry;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.harry.sys.dao.SysCountryDao;
import cn.harry.sys.service.SysCountryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 世界国旗图标
 *
 * @author honghh
 * Date 2019-10-22 10:38:46
 * Copyright (C) www.honghh.top
 */
@Service("sysCountryService")
public class SysCountryServiceImpl extends ServiceImpl<SysCountryDao, SysCountry> implements SysCountryService {


    @Override
    public List<SysCountry> allList() {
        return list();
    }
}