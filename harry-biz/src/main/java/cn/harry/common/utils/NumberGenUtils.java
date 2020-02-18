package cn.harry.common.utils;

import cn.harry.common.constant.CommonConstant;
import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: NumberGenUtils
 * Description:
 *
 * @author honghh
 * Date 2019/10/30 16:18
 * Copyright (C) www.tech-harry.cn
 */
@Component
public class NumberGenUtils {
    @Resource
    private RedisUtils redisUtils;

    /**
     * 生成18位订单编号:8位日期+2位平台号码+2位支付方式+6位以上自增id
     */
    public String genOrderSn(Integer sourceType, Integer payType) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        sb.append(date);
        sb.append(String.format("%02d", sourceType));
        sb.append(String.format("%02d", payType));
        sb.append(String.format("%06d", redisUtils.increment(CommonConstant.NUMBER_GEN_KEY_ORDER, date)));
        return sb.toString();
    }

    /**
     * 生成16位退货单编号:8位日期+2位平台号码+6位以上自增id
     */
    public String genOrderReturnSn(Integer sourceType) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        sb.append(date);
        sb.append(String.format("%02d", sourceType));
        sb.append(String.format("%06d", redisUtils.increment(CommonConstant.NUMBER_GEN_KEY_ORDER_RETURN, date)));
        return sb.toString();
    }

    /**
     * 获取13位以天为周期的自增编号
     * TYPE + yyMMdd + 时间戳
     *
     * @param type 编号类型(订单：DD 等)
     * @return
     * @author honghh
     * @date 2018/8/1 15:46
     */
    public static String generateCode(String type) {
        StringBuffer sb = new StringBuffer();
        sb.append(type);
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        sb.append(date);
        sb.append(DateUtil.current(true));
        return sb.toString();
    }
}
