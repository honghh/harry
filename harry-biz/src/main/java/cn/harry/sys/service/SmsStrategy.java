package cn.harry.sys.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import cn.harry.sys.vo.SmsTemplateDto;
import cn.harry.sys.param.SmsParam;

/**
 * ClassName: SmsStrategy
 * Description:
 *
 * @author honghh
 * Date 2019/08/30 14:06
 * Copyright (C) www.tech-harry.cn
 */
public interface SmsStrategy {

    /**
     * 第三方发送短信策略
     *
     * @param smsParam
     * @param smsTemplate
     * @return 返回第三方发送结果
     */
    SendSmsResponse send(SmsParam smsParam, SmsTemplateDto smsTemplate);
}
