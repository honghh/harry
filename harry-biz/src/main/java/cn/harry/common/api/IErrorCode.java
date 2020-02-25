package cn.harry.common.api;

/**
 * 封装API的错误码
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
