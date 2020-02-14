package cn.harry.sys.dao;

import cn.harry.sys.entity.SysConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置信息表/枚举值表
 *
 * @author honghh
 * Date 2019-11-25 16:36:48
 * Copyright (C) www.tech-harry.cn
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfig> {
	
}
