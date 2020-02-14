package cn.harry.sys.service.impl;

import cn.harry.sys.service.SmsStrategy;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import cn.harry.config.SmsConfig;
import cn.harry.sys.vo.SmsTemplateDto;
import cn.harry.sys.param.SmsParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: ALiDaYuSmsStrategy
 * Description: 阿里大鱼短信发送策略
 *
 * @author honghh
 * Date 2019/08/30 14:13
 * Copyright (C) www.tech-harry.cn
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "harry.sms.enabled", havingValue = "true")
public class AliDaYuSmsStrategy implements SmsStrategy {
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    @Resource
    SmsConfig smsConfig;

    @Override
    public SendSmsResponse send(SmsParam sendSmsParam, SmsTemplateDto smsTemplate) {
        try {
            return sendSms(sendSmsParam, smsTemplate, smsConfig);
        } catch (ClientException e) {
            log.error("发送短信失败", e);
        }
        return JSON.parseObject("{\"message\":\"短信模块发送发送短信异常 请查看日志\"}", SendSmsResponse.class);
    }


    private static SendSmsResponse sendSms(SmsParam sendSmsParam, SmsTemplateDto smsTemplate, SmsConfig smsConfig) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsConfig.getAccessKeyId(), smsConfig.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        String mobileList = String.join(",", sendSmsParam.getMobileList());
        String templateParam = JSON.toJSONString(sendSmsParam.getParams());

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobileList);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(smsTemplate.getSignName());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsTemplate.getTemplateCode());

        request.setTemplateParam(templateParam);

        //hint 此处可能会抛出异常，注意catch
        return acsClient.getAcsResponse(request);
    }

    /**
     * desc 查明细
     *
     * @param bizId
     * @param smsConfig
     * @return
     * @throws ClientException
     * @author honghh
     * date 2019-08-30 14:56
     */
    public static QuerySendDetailsResponse querySendDetails(String bizId, SmsConfig smsConfig) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsConfig.getAccessKeyId(), smsConfig.getAccessKeySecret());
        DefaultProfile.addEndpoint( "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("phoneNumber");
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        return acsClient.getAcsResponse(request);
    }
}
