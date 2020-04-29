package cn.harry.common.annotation;

import java.lang.annotation.*;

/**
 * ClassName: DataScope
 * Description:
 *
 * @author honghh
 * Date 2020/04/28 17:17
 * Copyright (C) www.honghh.top
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**  表的别名 */
    String tableAlias() default "";

    /**  true：没有本部门数据权限，也能查询本人数据 */
    boolean user() default true;

    /**  true：拥有子部门数据权限 */
    boolean subDept() default false;

    /**  部门ID */
    String deptId() default "dept_id";

    /**  用户ID */
    String userId() default "user_id";
}
