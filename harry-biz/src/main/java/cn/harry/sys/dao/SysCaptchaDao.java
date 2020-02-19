package cn.harry.sys.dao;

import cn.harry.sys.entity.SysCaptcha;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图片验证码
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptcha> {
	
}
