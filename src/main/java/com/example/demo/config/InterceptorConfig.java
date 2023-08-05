package com.example.demo.config;

//import com.example.demo.interceptor.TokenInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Component
//public class InterceptorConfig implements WebMvcConfigurer {
//    private final TokenInterceptor tokenInterceptor;
//    @Autowired
//    public InterceptorConfig(TokenInterceptor tokenInterceptor) {
//        this.tokenInterceptor = tokenInterceptor;
//    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){  // InterceptorRegistry 为拦截器注册对象
//        registry.addInterceptor(tokenInterceptor)  // 注册自定义拦截器
//                .addPathPatterns()// 拦截的路径
//                .excludePathPatterns("/auth/**"); // 不拦截的路径
//    }
//}
