package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public class TokenUtil {
    // 有效时长
    private static final long EXPIRE_TIME= 1000*60*60*10;
    // 私钥 需随机生成ss
    private static final String SECRET_KEY = "4ac59271c598d70afaf591f3f55dd22615d41c35a60d9e5d769690f3d569085d";

    // generate a token for a given user
    public static String generateToken(UserDTO user) {
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", user.getId());
        builder.withClaim("username", user.getUsername());
        builder.withClaim("isAdmin", user.getIsAdmin());
        builder.withIssuedAt(new Date(System.currentTimeMillis()));
        builder.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME));
        return builder.sign(Algorithm.HMAC256(SECRET_KEY));
    }
    public static Date extractExpiration(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    public static String extractClaim(String token, String claimName) {
        return JWT.decode(token).getClaim(claimName).asString();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new JWTVerificationException("请求头错误: " + authHeader);
    }

    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
        if (isTokenExpired(token)) {
            throw new JWTVerificationException("登录已过期");
        }
    }
    public static void verifyFromRequest(HttpServletRequest request) {
        try {
            TokenUtil.verify(getTokenFromRequest(request));
        } catch (JWTVerificationException e) {
            throw e;
        }
    }
}