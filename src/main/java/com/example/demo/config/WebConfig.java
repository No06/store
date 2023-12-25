package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        final String[] add = {
                "/user/**",
                "/cart/**",
                "/product/update", "/product/delete",
                "/order/**",
                "/address/**"
        };
        final String[] exclude = {
                "/user/register",
                "/user/login",
                "/user/info",
                "/product/category/getAll"
        };
        registry.addInterceptor(new JwtInterceptor())
                // 受保护的
                .addPathPatterns(add)
                // 排除的
                .excludePathPatterns(exclude);
    }
}
