package cn.harry.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: SysParamTypeEnums
 * Description:
 *
 * @author honghh
 * Date 2019/11/25 17:01
 * Copyright (C) www.honghh.top
 */
@Getter
@AllArgsConstructor
public enum SysParamTypeEnums {
    // 系统配置 订单取消原因
    ORDER_CANCEL_REASON("order_cancel_reason", "订单取消原因"),

    BANK("bank", "银行"),

    SHOP_TYPE("shop_type", "店铺类型"),

    CMS_HELP_CATEGORY("help_category", "帮助问题"),
    ;
    private String type;
    private String name;
}
