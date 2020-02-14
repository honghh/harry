package cn.harry.sys.vo;

import lombok.Data;

/**
 * ClassName: SmsTemplateDto
 * Description:
 *
 * @author honghh
 * Date 2019/08/30 14:12
 * Copyright (C) www.tech-harry.cn
 */
@Data
public class SmsTemplateDto {

    /**
     * 编号
     */
    private Long value;
    /**
     * 作用
     */
    private String name;
    /**
     * 短信签名
     */
    private String signName;
    /**
     * 来源平台
     */
    private Integer source;
    /**
     * 短信模板CODE
     */
    private String templateCode;
    /**
     * 第三方返回结果
     */
    private String content;
}
