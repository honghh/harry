package cn.harry.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: SmsConfig
 * Description:
 *
 * @author honghh
 * Date 2019/08/30 08:52
 * Copyright (C) www.tech-harry.cn
 */
@Data
@Component
@ConfigurationProperties(prefix = "harry.sms")
public class SmsConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private boolean enabled;
}
