package cn.harry.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author honghh
 * Date 2019/10/08 10:47
 * Copyright (C) www.tech-harry.cn
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String MODULE() default "操作模块";
    String REMARK() default "操作日志";
}
