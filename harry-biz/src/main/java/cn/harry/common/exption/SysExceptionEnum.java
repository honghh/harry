package cn.harry.common.exption;


import cn.harry.common.api.IErrorCode;

/**
 * ClassName: UmsExceptionEnum
 * Description:
 *
 * @author honghh
 * Date 2019/08/30 11:22
 * Copyright (C) www.honghh.top
 */
public enum SysExceptionEnum implements IErrorCode {

    /******** 系统管理 member 100100 *********/
    WRONG_USERNAME_OR_PASSWORD(100100, "用户名或密码错误"),

    /******** 微信小程序登陆 100101 *********/
    AUTHORIZATION_WX_FAILURE(100101, "授权失败"),
    ;

    private long code;
    private String msg;

    SysExceptionEnum(long code, String msg) {
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
