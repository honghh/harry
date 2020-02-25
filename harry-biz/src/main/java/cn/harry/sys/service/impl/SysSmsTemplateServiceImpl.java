package cn.harry.sys.service.impl;

import cn.harry.common.exception.ApiException;
import cn.harry.sys.vo.SmsTemplateDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.harry.common.exption.SmsExceptionEnum;
import cn.harry.common.utils.BeanUtils;
import cn.harry.sys.dao.SysSmsTemplateDao;
import cn.harry.sys.entity.SysSmsTemplate;
import cn.harry.sys.service.SysSmsTemplateService;
import org.springframework.stereotype.Service;

/**
 * 短信模板
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Service("sysSmsTemplateService")
public class SysSmsTemplateServiceImpl extends ServiceImpl<SysSmsTemplateDao, SysSmsTemplate> implements SysSmsTemplateService {


    @Override
    public SmsTemplateDto geSmsTemplateByCode(Long code) {
        if (code == null) {
            throw new ApiException(SmsExceptionEnum.MSG_TYPE_ERROR);
        }

        SysSmsTemplate smsTemplate = this.baseMapper.selectOne(new LambdaQueryWrapper<SysSmsTemplate>()
                .eq(SysSmsTemplate::getValue, code)
        );

        return BeanUtils.transform(SmsTemplateDto.class, smsTemplate);
    }


}