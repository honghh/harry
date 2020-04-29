package cn.harry.component.aspect;

import cn.harry.common.annotation.DataScope;
import cn.harry.common.constant.CommonConstant;
import cn.harry.common.exception.ApiException;
import cn.harry.common.utils.SecurityUtils;
import cn.harry.common.utils.SysUserUtils;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.enums.DataScopeEnums;
import cn.harry.sys.service.SysDeptService;
import cn.harry.sys.service.SysRoleDeptService;
import cn.harry.sys.service.SysUserRoleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: DataScopeAspect
 * Description:
 *
 * @author honghh
 * Date 2020/04/28 17:19
 * Copyright (C) www.honghh.top
 */

@Aspect
@Component
public class DataScopeAspect {

    @Resource
    private SysDeptService sysDeptService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(cn.harry.common.annotation.DataScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
        Object params = joinPoint.getArgs()[0];

        if (params instanceof Map) {
            SysUser user = SysUserUtils.getSysUser();
            //如果不是超级管理员，则进行数据过滤
            if (!SecurityUtils.isAdmin(user.getId())) {
                // 根据用户ID 获取数据权限标识 如果数据权限包含全部数据 直接返回
                List<Integer> dataScopes = sysUserRoleService.listDataScopesByUserId(user.getId());
                if (!CollectionUtils.isEmpty(dataScopes)) {
                    if (dataScopes.contains(DataScopeEnums.ALL.getKey())) {
                        return;
                    }
                }
                Map map = (Map) params;
                map.put(CommonConstant.SQL_FILTER, this.getSQLFilter(user, joinPoint));
            }
            return;
        }
        throw new ApiException("数据权限接口，只能是Map类型参数，且不能为NULL");
    }

    /**
     * 获取数据过滤的SQL
     *
     * @param user
     * @param point
     * @return
     */
    private String getSQLFilter(SysUser user, JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataScope dataFilter = signature.getMethod().getAnnotation(DataScope.class);
        //获取表的别名
        String tableAlias = dataFilter.tableAlias();
        if (org.apache.commons.lang.StringUtils.isNotBlank(tableAlias)) {
            tableAlias += ".";
        }
        //部门ID列表
        Set<Long> deptIdList = new HashSet<>();
        StringBuilder sqlFilter = new StringBuilder();

        //1. 根据用户ID 获取角色列表
        List<Long> roleIdList = sysUserRoleService.listRoleIdByUserId(user.getId());
        if (roleIdList.size() > 0) {
            // 2.用户角色对应的部门ID列表
            List<Long> idList = sysRoleDeptService.queryDeptIdList(roleIdList);
            deptIdList.addAll(idList);
        }

        //用户子部门ID列表
        if (dataFilter.subDept()) {
            List<Long> subDeptIdList = sysDeptService.getSubDeptIdList(user.getDeptId());
            deptIdList.addAll(subDeptIdList);
        }
        sqlFilter.append(" (");

        if (deptIdList.size() > 0) {
            sqlFilter.append(tableAlias).append(dataFilter.deptId()).append(" in(").append(org.apache.commons.lang.StringUtils.join(deptIdList, ",")).append(")");
        }

        //没有设置本部门数据权限，也能查询本部门数据  user.getDeptId()
        if (dataFilter.user()) {
            if (deptIdList.size() > 0) {
                sqlFilter.append(" or ");
            }
            sqlFilter.append(tableAlias).append(dataFilter.deptId()).append("=").append(user.getDeptId());
        }

        sqlFilter.append(")");

        if ("()".equals(sqlFilter.toString().trim())) {
            return null;
        }

        return sqlFilter.toString();
    }
}
