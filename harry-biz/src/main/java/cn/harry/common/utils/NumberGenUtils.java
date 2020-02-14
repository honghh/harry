package cn.harry.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.harry.common.constant.CommonConstant;
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

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        sb.append(date);
        sb.append(String.format("%02d", 1));
        sb.append(String.format("%02d", 1));
        sb.append(String.format("%06d", 222222));


        String date2 = new SimpleDateFormat("yyyyMMdd").format(DateUtil.yesterday());
        System.out.println(date2);
        System.out.println(sb.toString());

        System.out.println("=============");
        System.out.println(DateUtil.current(true));
        System.out.println(DateUtil.current(true));
        System.out.println(DateUtil.current(true));
        System.out.println(DateUtil.current(true));
        System.out.println(DateUtil.current(true));


        for (int i = 0; i < 10; i++) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("qf");
            sb2.append(DateUtil.current(true));
            System.out.println(sb2.toString());
        }
        System.out.println(UUID.randomUUID());

        System.out.println(generateCode(CommonConstant.ALI_PAY_PREFIX));

    }
}
