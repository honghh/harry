package cn.harry.sys.service;

import java.awt.image.BufferedImage;

/**
 * 图片验证码
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.tech-harry.cn
 */
public interface SysCaptchaService {

    /**
     * 获取图片验证码
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);
    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}

