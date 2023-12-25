package com.example.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = TokenUtil.getTokenFromRequest(request);
            DecodedJWT decodedJWT = JWT.decode(token);
            Long userId = decodedJWT.getClaim("id").asLong();
            Boolean isAdmin = decodedJWT.getClaim("isAdmin").asBoolean();
            request.setAttribute("userId", userId);
            request.setAttribute("isAdmin", isAdmin);
        } catch (Exception e) {
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            PrintWriter writer = response.getWriter();
            writer.println(e.getMessage());
            writer.close();
            return false;
        }
        return true;
    }
}