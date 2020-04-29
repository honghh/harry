package cn.harry.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: BusinessType
 * Description: 业务操作类型
 *
 * @author honghh
 * Date 2020/04/20 09:50
 * Copyright (C) www.honghh.top
 */
@Getter
@AllArgsConstructor
public enum  BusinessType {
    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空数据
     */
    CLEAN,
}
