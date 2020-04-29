package cn.harry.sys.dao;

import cn.harry.sys.entity.SysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典
 *
 * @author honghh
 * Date 2020-03-16 09:53:38
 * Copyright (C) www.tech-harry.cn
 */
@Mapper
public interface SysDictDao extends BaseMapper<SysDict> {
	
}
