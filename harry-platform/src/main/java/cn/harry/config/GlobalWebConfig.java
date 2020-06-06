package cn.harry.config;

import cn.harry.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * 全局跨域配置
 *
 * @author honghh
 * Date 2019-10-12 10:55:44
 * Copyright (C) www.honghh.top
 */
@Configuration
@EnableWebMvc
public class GlobalWebConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor vueViewInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(vueViewInterceptor)
                .addPathPatterns("/**")
                // 排除配置
                .excludePathPatterns(
                        "/admin/login",
                        "/captchaImage",
                        "/swagger-resources/**",
                        "*.js",
                        "/**/*.js",
                        "*.css",
                        "/**/*.css",
                        "*.html",
                        "/v2/*",
                        "/**/*.html");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
