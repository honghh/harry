package cn.harry.sys.service.impl;

import cn.harry.sys.param.SmsParam;
import cn.harry.sys.service.SmsStrategy;
import cn.harry.sys.vo.SmsTemplateDto;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * ClassName: FakeSmsStrategy
 * Description: 伪造发送短信信息
 *
 * @author honghh
 * Date 2019/08/30 14:13
 * Copyright (C) www.honghh.top
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "harry.sms.enabled", havingValue = "false")
public class FakeSmsStrategy implements SmsStrategy {

    @Override
    public SendSmsResponse send(SmsParam smsParam, SmsTemplateDto smsTemplate) {
        return JSON.parseObject("{\"bizId\":\"111111111\",\"code\":\"OK\",\"message\":\"并未真实发送\",\"requestId\":\"111111111\"}", SendSmsResponse.class);
    }
}
