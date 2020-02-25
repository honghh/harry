package cn.harry.sys.param;

import cn.harry.common.exception.ApiException;
import cn.harry.common.exption.SmsExceptionEnum;
import cn.harry.sys.enums.SmsTemplateEnums;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SmsParam
 * Description:
 *
 * @author honghh
 * Date 2019/08/30 11:19
 * Copyright (C) www.honghh.top
 */
@Data
public class SmsParam {
    /**
     * 【必填】
     * 发送短信手机号码集合
     */
    private List<String> mobileList;

    /**
     * [非必填]
     * 阿里大鱼的参数集合
     */
    private Map<String, String> params;

    /**
     * 短信模板
     */
    private Integer templateKey;

    private SmsParam() {

    }

    private SmsParam(List<String> mobileList, Map<String, String> params, Integer templateKey) {
        this.mobileList = mobileList;
        this.params = params;
        this.templateKey = templateKey;
    }

    public static SmsParam.SmsParamBuilder builder() {
        return new SmsParam.SmsParamBuilder();
    }

    public static class SmsParamBuilder {

        /**
         * 获取手机号
         */
        private List<String> mobiles = new ArrayList<>();


        /**
         * 参数
         */
        private Map<String, String> params = new HashMap<>();

        /**
         * 模版
         */
        private Integer templateKey;


        public SmsParam.SmsParamBuilder mobile(String mobile) {
            this.mobiles.add(mobile);
            return this;
        }

        public SmsParam.SmsParamBuilder mobiles(List<String> mobiles) {
            this.mobiles.addAll(mobiles);
            return this;
        }

        public SmsParam.SmsParamBuilder param(String key, String value) {
            this.params.put(key, value);
            return this;
        }

        public SmsParam.SmsParamBuilder params(Map<String, String> params) {
            this.params.putAll(params);
            return this;
        }

        public SmsParam.SmsParamBuilder templateKey(SmsTemplateEnums type) {
            this.templateKey = type.getValue();
            return this;
        }

        public SmsParam.SmsParamBuilder templateKey(Integer templateKey) {
            this.templateKey = templateKey;
            return this;
        }

        public SmsParam build() {
            if (this.templateKey == null) {
                throw new ApiException(SmsExceptionEnum.TEMPLATE_CODE_ERROR);
            }

            if (CollectionUtils.isEmpty(mobiles)) {
                throw new ApiException(SmsExceptionEnum.MOBILE_ERROR);
            }
            if (CollectionUtils.isEmpty(params)) {
                throw new ApiException(SmsExceptionEnum.MSG_PARAM_ERROR);
            }
            return new SmsParam(this.mobiles, this.params, this.templateKey);
        }
    }
}
