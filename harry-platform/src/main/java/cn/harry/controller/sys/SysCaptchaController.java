package cn.harry.controller.sys;

import cn.harry.common.api.CommonResult;
import cn.harry.sys.service.SysCaptchaService;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: CaptchaController
 * Description:
 *
 * @author honghh
 * Date 2020/04/13 14:02
 * Copyright (C) www.honghh.top
 */
@RestController
@Api(tags = "Sys-captcha => 图形验证码")
public class SysCaptchaController {

    @Resource
    private SysCaptchaService sysCaptchaService;

    /**
     * 生成验证码
     */
    @ApiOperation("captchaImage => 生成验证码")
    @GetMapping("captchaImage")
    public CommonResult<Map<String, Object>> captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 唯一标识
        String uuid = IdUtil.simpleUUID();
        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
        Map<String, Object> map = new HashMap<>();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        ImageIO.write(image, "jpg", stream);

        map.put("uuid", uuid);
        map.put("img", Base64.encode(stream.toByteArray()));
        return CommonResult.success(map);
    }
}
