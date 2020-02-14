package cn.harry.sys.param;

import lombok.Data;

import java.util.Map;

/**
 * ClassName: SmsCodeParam
 * Description:
 *
 * @author honghh
 * Date 2019/08/30 10:13
 * Copyright (C) www.tech-harry.cn
 */
@Data
public class SmsCodeParam {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 短信类型【非必须】
     */
    private Integer msgType;
    /**
     * 验证码
     */
    private String msgCode;
    /**
     * 参数
     */
    private Map<String, String> params;
}
