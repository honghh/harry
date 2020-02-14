package cn.harry.common.constant;


/**
 * 公共常量
 *
 * @author honghh
 * Date 2019-10-12 10:58:47
 * Copyright (C) www.tech-harry.cn
 */
public class CommonConstant {

    public static final String AJAX_REQUEST_HEADER_KEY = "x-rjft-request";

    public static final String AJAX_REQUEST_HEADER_VALUE = "ajax";

    public static final String AJAX_REQUEST_TOKEN_KEY = "Authorization";

    /**
     * app原生页请求
     */
    public static final String AJAX_NATIVE_HEADER_KEY = "x-rjft-native";

    public static final String AJAX_NATIVE_HEADER_VALUE = "native";


    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN_ID = 1;

    /**
     * 系统菜单最大id
     */
    public static final int SYS_MENU_MAX_ID = 1;
    /**
     * 默认角色
     */
    public static final long DEFAULT_ROLE = 1L;

    /**
     * 匿名用户
     */
    public static final String ANONYMOUS_USER = "anonymousUser";

    /**
     * 订单生成规则
     */
    public static final String NUMBER_GEN_KEY_ORDER = "number_gen_key_order";

    /**
     * 退货单生成规则
     */
    public static final String NUMBER_GEN_KEY_ORDER_RETURN = "number_gen_key_order_return";

    /**
     * 业务类型前缀 【支付宝支付唯一编码前缀】
     */
    public static final String ALI_PAY_PREFIX = "ALI";
    /**
     * 业务类型前缀 【微信支付唯一编码前缀】
     */
    public static final String WEIXIN_PAY_PREFIX = "WX";


}
