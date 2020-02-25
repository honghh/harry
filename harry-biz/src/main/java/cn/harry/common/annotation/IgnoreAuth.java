package cn.harry.common.annotation;

import java.lang.annotation.*;

/**
 * 忽略Token验证
 *
 * @author honghh
 * Date 2019/10/08 10:47
 * Copyright (C) www.honghh.top
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
