package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private final String[] adds = {
			"/user/**",
			"/cart/**",
			"/goods/update", "/goods/delete",
			"/order/**",
			"/address/**",
			"/carousel/save", "/carousel/delete",
	};
	private final String[] excludes = {
			"/user/register",
			"/user/login",
			"/user/checkToken"
	};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                // 受保护的
                .addPathPatterns(adds)
                // 排除的
                .excludePathPatterns(excludes);
    }
}
