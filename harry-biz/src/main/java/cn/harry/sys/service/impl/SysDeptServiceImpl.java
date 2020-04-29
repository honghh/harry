package cn.harry.sys.service.impl;

import cn.harry.common.annotation.DataScope;
import cn.harry.common.constant.CommonConstant;
import cn.harry.common.exception.ApiException;
import cn.harry.common.exption.SysExceptionEnum;
import cn.harry.sys.dao.SysDeptDao;
import cn.harry.sys.entity.SysDept;
import cn.harry.sys.enums.StatusEnums;
import cn.harry.sys.service.SysDeptService;
import cn.harry.sys.vo.TreeSelect;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门表
 *
 * @author honghh
 * Date 2020-03-16 08:51:37
 * Copyright (C) www.tech-harry.cn
 */
@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {

    @Override
    public int create(SysDept sysDept) {
        sysDept.setAncestors(getAncestors(sysDept.getPid()));
        return this.baseMapper.insert(sysDept);
    }

    @Override
    public int updateStatus(Long id, String status) {
        SysDept sysDept = new SysDept();
        sysDept.setId(id);
        sysDept.setStatus(status);
        return this.baseMapper.updateById(sysDept);
    }

    @Override
    public int update(Long id, SysDept sysDept) {
        sysDept.setId(id);
        sysDept.setAncestors(getAncestors(sysDept.getPid()));
        return this.baseMapper.updateById(sysDept);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    @DataScope(deptId = "id")
    public List<SysDept> selectDeptList(Map<String, Object> params) {
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return list(wrapper);
    }

    @Override
    public List<Integer> selectDeptListByRoleId(Long roleId) {
        return this.baseMapper.selectDeptListByRoleId(roleId);
    }

    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts) {
        List<SysDept> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    @Override
    public List<SysDept> buildDeptTree(List<SysDept> depts) {
        List<SysDept> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<>();
        for (SysDept dept : depts) {
            tempList.add(dept.getId());
        }
        for (SysDept dept : depts) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getPid())) {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty()) {
            returnList = depts;
        }
        return returnList;
    }

    @Override
    public SysDept selectById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<Long> getSubDeptIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> ids = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList = queryDeptIdList(deptId);
        getDeptTreeList(subIdList, ids);
        return ids;
    }

    public List<Long> queryDeptIdList(Long parentId) {
        return baseMapper.queryDeptIdList(parentId);
    }

    /**
     * 递归
     */
    private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList) {
        for (Long deptId : subIdList) {
            List<Long> list = queryDeptIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }
            deptIdList.add(deptId);
        }
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysDept> list, SysDept t) {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                for (SysDept n : childList) {
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 得到子节点列表
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
        List<SysDept> tlist = new ArrayList<>();
        for (SysDept n : list) {
            if (n.getPid().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 获取祖级列表
     *
     * @param pid
     * @return
     */
    private String getAncestors(Long pid) {
        // 查询父级菜单 ParentId 信息
        SysDept dept = this.baseMapper.selectById(pid);
        if (dept == null || StatusEnums.DISABLE.getKey().equals(dept.getStatus())) {
            throw new ApiException(SysExceptionEnum.DEPT_NOT_EXISTS);
        }
        return dept.getAncestors() + "," + pid;
    }

}