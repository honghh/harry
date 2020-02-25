package cn.harry.common.annotation;

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

    String module() default "操作模块";
    String remark() default "操作日志";
}
