package cn.harry.common.annotation;

import cn.harry.common.enums.BusinessType;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author honghh
 * Date 2019/10/08 10:47
 * Copyright (C) www.honghh.top
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 模块
     */
     String title() default "";

    /**
     * 功能
     */
     BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
     boolean isSaveRequestData() default true;
}
