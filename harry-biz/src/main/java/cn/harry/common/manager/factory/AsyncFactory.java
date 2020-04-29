package cn.harry.common.manager.factory;

import cn.harry.common.utils.IpAddressUtil;
import cn.harry.common.utils.SpringUtils;
import cn.harry.monitor.entity.SysOperationLog;
import cn.harry.monitor.service.SysOperationLogService;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * ClassName: AsyncFactory
 * Description: 异步工厂（产生任务用）
 *
 * @author honghh
 * Date 2020/04/20 15:49
 * Copyright (C) www.honghh.top
 */
@Slf4j
public class AsyncFactory {
    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperationLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                String address = IpAddressUtil.getCityInfo(operLog.getOperIp());
                // 远程查询操作地点
                operLog.setOperLocation(address);
                SpringUtils.getBean(SysOperationLogService.class).insertLog(operLog);
            }
        };
    }
}
