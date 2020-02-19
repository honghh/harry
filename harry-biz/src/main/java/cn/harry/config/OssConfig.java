package cn.harry.config;

import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: OssConfig
 * Description: 阿里云存储配置
 *
 * @author honghh
 * Date 2019/10/14 11:50
 * Copyright (C) www.honghh.top
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssConfig {

    /**
     * oss对外服务的访问域名
     */
    private String endpoint;

    /**
     * 访问身份验证中用到用户标识
     */
    private String accessKeyId;

    /**
     * 用户用于加密签名字符串和oss用来验证签名字符串的密钥
     */
    private String accessKeySecret;

    /**
     * oss的存储空间
     */
    private String bucketName;

    /**
     * 签名有效期(S)
     */
    private String policyExpire;

    /**
     * 上传文件大小(M)
     */
    private String maxSize;

    /**
     * 文件上传成功后的回调地址
     */
    private String callback;

    /**
     * 上传文件夹路径前缀
     */
    private String dirPrefix;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
