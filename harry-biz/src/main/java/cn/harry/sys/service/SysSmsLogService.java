package cn.harry.sys.service;

import cn.harry.sys.param.SmsParam;
import cn.harry.sys.vo.SmsTemplateDto;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

/**
 * 短信日志表
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
public interface SysSmsLogService {
    /**
     *
     * desc 保存短信发送日志
     *
     * @author honghh
     * date 2019-08-30 14:38
     * @param smsParam
     * @param smsTemplate
     * @param sendSmsResponse
     * @return
     */
    Boolean addSmsLog(SmsParam smsParam, SmsTemplateDto smsTemplate, SendSmsResponse sendSmsResponse);
}

