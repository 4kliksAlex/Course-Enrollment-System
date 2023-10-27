package com.code.dbcourse1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域
        registry.addMapping("/**")
                // FIXME 允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 允许cookie
                .allowCredentials(true)
                // 允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 允许时间
                .maxAge(3600);
    }
}
