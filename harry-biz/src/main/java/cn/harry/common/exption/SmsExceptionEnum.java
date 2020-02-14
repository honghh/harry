package cn.harry.common.exption;


import cn.harry.common.api.IErrorCode;

/**
 * ClassName: SmsExceptionEnum
 * Description:
 *
 * @author honghh
 * Date 2019/08/30 11:22
 * Copyright (C) www.tech-harry.cn
 */
public enum SmsExceptionEnum implements IErrorCode {

    /******** 短信模块 sys_sms 10700 *********/
    MESSAGE_TO_MANY(10700, "1分钟之内不能重发2次"),
    MSG_TYPE_ERROR(10701, "短信类型错误"),
    MSG_CODE_ERROR(10702, "验证码错误"),
    MOBILE_ERROR(10703, "手机号有误"),
    PHONE_IS_EMPTY(10705, "手机号为空"),
    VERIFICATION_CODE_FAILED(10706, "验证码验证失败"),
    TEMPLATE_CODE_ERROR(10707, "短信模版未配置"),
    MSG_PARAM_ERROR(10708, "短信参数错误"),

    ;
    private long code;
    private String msg;

    SmsExceptionEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
