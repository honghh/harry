package cn.harry.sys.service;

import cn.harry.sys.vo.SmsTemplateDto;

/**
 * 短信模板
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
public interface SysSmsTemplateService {

    /**
     *
     * desc   获取短信模板
     *
     * @author honghh
     * date 2019-08-30 14:28
     * @param value
     * @return
     */
    SmsTemplateDto geSmsTemplateByCode(Long value);
}

