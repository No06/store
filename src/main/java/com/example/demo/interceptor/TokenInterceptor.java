package com.example.demo.interceptor;

import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization"); //前端vue将token添加在请求头中
        // Token为空
        if (token == null || token.isEmpty()) {
            response.setStatus(409);
            return false;
        }
        // Token过期
        if (!TokenUtil.verify(token)) {
            response.setStatus(410);
            return false;
        }
        return true;
    }
}
