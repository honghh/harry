package cn.harry.sys.service.impl;

import cn.harry.common.utils.EncryptUtils;
import cn.harry.common.utils.IpAddressUtil;
import cn.harry.common.utils.RedisUtils;
import cn.harry.sys.entity.SysUser;
import cn.harry.sys.entity.SysUserLoginLog;
import cn.harry.sys.service.OnlineUserService;
import cn.harry.sys.service.SysUserLoginLogService;
import cn.harry.sys.vo.OnlineUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * ClassName: OnlineUserServiceImpl
 * Description:
 *
 * @author honghh
 * Date 2020/03/07 16:35
 * Copyright (C) 洛阳乾发供应链管理有限公司
 */
@Slf4j
@Service("onlineUserService")
public class OnlineUserServiceImpl implements OnlineUserService {
    private final static String ONLINE_KEY = "online:token:";
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private SysUserLoginLogService sysUserLoginLogService;

    @Override
    public void save(SysUser sysUser, String token, String expiration, HttpServletRequest request) {
        String job = sysUser.getCompanyName();
        String ip = IpAddressUtil.getIpAddr(request);
        String browser = IpAddressUtil.getBrowser(request);
        String address = IpAddressUtil.getCityInfo(ip);
        OnlineUser onlineUser = null;
        try {
            onlineUser = new OnlineUser(
                    sysUser.getUsername(),
                    sysUser.getNickName(),
                    job,
                    browser,
                    ip,
                    address,
                    EncryptUtils.desEncrypt(token),
                    new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisUtils.set(ONLINE_KEY + token, onlineUser, Long.parseLong(expiration));
        log.info("{},{},{},{},{},{},{},{},{}", ONLINE_KEY + token, sysUser.getId(), sysUser.getUsername(), sysUser.getNickName(), ip, address, browser, new Date(), new Date());

        sysUserLoginLogService.create(new SysUserLoginLog(
                ONLINE_KEY + token,
                sysUser.getId(),
                sysUser.getUsername(),
                sysUser.getNickName(),
                ip,
                address,
                browser,
                new Date(),
                new Date()));
    }

    /**
     * 踢出用户
     *
     * @param key /
     * @throws Exception /
     */
    @Override
    public void kickOut(String key) throws Exception {
        key = ONLINE_KEY + EncryptUtils.desDecrypt(key);
        redisUtils.delete(key);
    }

    /**
     * 退出登录
     *
     * @param token /
     */
    @Override
    public void logout(String token) {
        String key = ONLINE_KEY + token;
        redisUtils.delete(key);
        sysUserLoginLogService.deleteByKey(key);
    }

    @Override
    public void checkLoginOnUser(String username, String igoreToken) {

        List<OnlineUser> onlineUsers = getAll(username);
        if (onlineUsers == null || onlineUsers.isEmpty()) {
            return;
        }
        for (OnlineUser onlineUser : onlineUsers) {
            if (onlineUser.getUsername().equals(username)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                    if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
                        this.kickOut(onlineUser.getKey());
                    } else if (StringUtils.isBlank(igoreToken)) {
                        this.kickOut(onlineUser.getKey());
                    }
                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

    /**
     * 查询全部数据，不分页
     *
     * @param filter /
     * @return /
     */
    @Override
    public List<OnlineUser> getAll(String filter) {
        List<String> keys = redisUtils.scan(ONLINE_KEY + "*");
        Collections.reverse(keys);
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            OnlineUser onlineUser = redisUtils.get(key, OnlineUser.class);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUser.toString().contains(filter)) {
                    onlineUsers.add(onlineUser);
                }
            } else {
                onlineUsers.add(onlineUser);
            }
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }

}
