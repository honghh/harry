package cn.harry.common.exception;

import cn.harry.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author honghh
 * Date 2019/07/18 14:03
 * Copyright (C) www.tech-harry.cn
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ApiException.class)
    public CommonResult handleApiException(ApiException e) {
        log.error("自定义ApiException 抛出: {}",e);
        return CommonResult.failed(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed();
    }
}
