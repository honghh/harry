package cn.harry.sys.service;

import cn.harry.sys.param.SmsParam;

/**
 * ClassName: SmsService
 * Description:
 *
 * @author honghh
 * Date 2019/08/30 08:56
 * Copyright (C) www.tech-harry.cn
 */
public interface SmsService {
    /**
     * desc 获取短信验证码
     *
     * @param mobile     手机号
     * @param msgCodType 手机验证码类型
     * @return
     * @author honghh
     * date 2019-08-30 11:37
     */
    String sendSmsCode(String mobile, String msgCodType);

    /**
     * desc 验证短信验证码
     *
     * @param mobile     手机号
     * @param msgCode    验证码
     * @param msgCodType 手机验证码类型
     * @return
     * @author honghh
     * date 2019-08-30 11:38
     */
    boolean verifySmsCode(String mobile, String msgCode, String msgCodType);

    /**
     * desc 发送短信
     *
     * @param smsParam 手机号
     * @return Boolean
     * @author honghh
     * date 2019-08-30 11:37
     */
    Boolean sendSms(SmsParam smsParam);

}
