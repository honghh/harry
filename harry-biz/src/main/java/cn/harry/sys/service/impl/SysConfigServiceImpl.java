package cn.harry.sys.service.impl;

import cn.harry.sys.dao.SysConfigDao;
import cn.harry.sys.entity.SysConfig;
import cn.harry.sys.service.SysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 系统配置信息表/枚举值表
 *
 * @author honghh
 * Date 2019-11-25 16:36:48
 * Copyright (C) www.tech-harry.cn
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {


}