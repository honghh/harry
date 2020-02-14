package cn.harry.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: SmsCodeTypeEnums
 * Description: 短信验证码类型（作用）
 *
 * @author honghh
 * Date 2019/09/06 11:32
 * Copyright (C) www.tech-harry.cn
 */
@Getter
@AllArgsConstructor
public enum SmsCodeTypeEnums {
    /**
     * 用户注册验证码
     */
    USER_REGISTER(1001, "user_register", "user_register_", "用户注册验证码"),
    /**
     * 用户修改密码验证码
     */
    USER_UPDATE_PASSWORD(1002, "user_password", "user_update_password_", "用户修改密码验证码"),
    ;


    private Integer code;
    private String type;
    private String value;
    private String name;

    public static String getValueByType(String type) {
        for (SmsCodeTypeEnums smsCodeTypeEnums : values()) {
            if (smsCodeTypeEnums.getType().equals(type)) {
                return smsCodeTypeEnums.getValue();
            }
        }
        return null;
    }

    /**
     * 根据type值获取 code
     * @param type
     * @return
     */
    public static Integer getCodeByType(String type) {
        for (SmsCodeTypeEnums smsCodeTypeEnums : values()) {
            if (smsCodeTypeEnums.getType().equals(type)) {
                return smsCodeTypeEnums.getCode();
            }
        }
        return null;
    }
}
