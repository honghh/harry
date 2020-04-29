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


    /******** 菜单管理 menu 100200 *********/
    MENU_SUBMENU_EXISTS(100200, "存在子菜单,不允许删除"),
    MENU_IS_ASSIGNED(100201, "菜单已分配,不允许删除"),

    /******** 字典管理 dict 100300 *********/
    DICT_TYPE_EXISTS(100300, "字典类型已存在,不允许删除"),


    /******** 部门管理 dept 100400 *********/
    DEPT_NOT_EXISTS(100400, "部门已停用或不存在，不允许新增"),
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
