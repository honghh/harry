package cn.harry.common.utils;

/**
 * Redis所有Keys
 *
 * @author honghh
 * Date 2019/07/18 14:03
 * Copyright (C) www.tech-harry.cn
 */
public class RedisKeys {

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }

    /**
     * desc 获取短信验证码
     *
     * @param key  手机号
     * @param type 类型
     * @return
     * @author honghh
     * date 2019-09-06 10:20
     */
    public static String getMsgCodeKey(String key, String type) {
        return "msg:code:" + type + key;
    }

    /**
     * desc 获取token
     *
     * @return
     * @author honghh
     * date 2019-09-06 10:20
     */
    public static String getTokenByOpenid(String openid) {
        return "token:openid:" + openid;
    }

    /**
     * desc 获取username
     *
     * @param openid
     * @return
     * @author honghh
     * date 2020-01-03 10:23
     */
    public static String getUsernameByOpenid(String openid) {
        return "username:openid:" + openid;
    }
}
