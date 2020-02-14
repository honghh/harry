package cn.harry.component;


import cn.harry.common.api.CommonResult;
import cn.harry.common.utils.JsonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当访问接口没有权限时，自定义的返回结果
 *
 * @author honghh
 * Date 2019/10/08 10:47
 * Copyright (C) www.tech-harry.cn
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JsonUtil.objectToJson(CommonResult.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
