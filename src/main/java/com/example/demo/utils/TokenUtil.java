package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.User;

import java.util.Date;

public class TokenUtil {
    // 有效时长
    private static final long EXPIRE_TIME= 1000*60*60*10;
    // 私钥 需随机生成
    private static final String SECRET_KEY = "4ac59271c598d70afaf591f3f55dd22615d41c35a60d9e5d769690f3d569085d";

    // generate a token for a given user
    public static String generateToken(User user) {
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("username", user.getUsername());
        builder.withIssuedAt(new Date(System.currentTimeMillis()));
        builder.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME));
        return builder.sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // extract the username from a token
    public static String extractUsername(String token) {
        return extractClaim(token, "username");
    }

    // extract the expiration date from a token
    public static Date extractExpiration(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    // extract a specific claim from a token
    public static String extractClaim(String token, String claimName) {
        return JWT.decode(token).getClaim(claimName).asString();
    }

    // check if a token is expired
    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static Boolean verify(String token) {
//        final String tokenUsername = extractUsername(token);
        try {
            JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
            return !isTokenExpired(token);
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}