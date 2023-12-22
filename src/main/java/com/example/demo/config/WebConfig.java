package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/user/info", "/user/get/defaultAddress", "/user/update/defaultAddress")
                .addPathPatterns("/cart/**")
                .addPathPatterns("/product/update", "/product/delete")
                .addPathPatterns("/order/**")
                .addPathPatterns("/address/**");
    }
}
