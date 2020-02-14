package cn.harry.component;


import cn.harry.common.utils.IpAddressUtil;
import cn.harry.common.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT登录授权过滤器
 *
 * @author honghh
 * Date 2019/10/08 10:47
 * Copyright (C) www.tech-harry.cn
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        long startTime, endTime;
        Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());

        StringBuffer sbParams = new StringBuffer();
        sbParams.append("?");

        for (String key : params.keySet()) {
            if (null == key || null == params.get(key) || null == params.get(key)[0]) {
                continue;
            }
            sbParams.append(key).append("=").append(params.get(key)[0]).append("&");
        }

        if (sbParams.length() > 1) {
            sbParams = sbParams.delete(sbParams.length() - 1, sbParams.length());
        }

        String fullUrl = request.getRequestURL().toString();

        String authHeader = request.getHeader(jwtTokenUtil.getTokenHeader());
        if (authHeader != null && authHeader.startsWith(jwtTokenUtil.getTokenHead())) {

            // The part after "Bearer "
            String authToken = authHeader.substring(jwtTokenUtil.getTokenHead().length());
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            log.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        endTime = System.currentTimeMillis();
        String requestType = request.getMethod();

        log.info(formMapKey(11, fullUrl, requestType,
                IpAddressUtil.getIpAddr(request), sbParams.toString(), authHeader)
                + ",\"cost\":\"" + (endTime - startTime) + "ms\"");
    }

    private String formMapKey(Object userId, String mothedName, String requestType,
                              String ip, String params, String token) {
        return "\"time\"" + ":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                + "\",\"name\"" + ":\"" + mothedName + "\",\"uid\":\"" + userId
                + "\",\"type\":\"" + requestType + "\",\"ip\":\"" + ip
                + "\",\"token\":\"" + token + "\",\"params\":\"" + params + "\"";
    }
}
