package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "4ac59271c598d70afaf591f3f55dd22615d41c35a60d9e5d769690f3d569085d";

    // generate a token for a given user
    public String generateToken(User user) {
        // create a JWT builder
        JWTCreator.Builder builder = JWT.create();
        // set the claims
        builder.withClaim("username", user.getUsername());
        builder.withClaim("password", user.getPassword());
        // set the subject
        builder.withSubject(user.getUsername());
        // set the issued at and expiration time
        builder.withIssuedAt(new Date(System.currentTimeMillis()));
        builder.withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)); // 10 hours validity
        // sign the token with the secret key
        return builder.sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // extract the username from a token
    public String extractUsername(String token) {
        return extractClaim(token, "username");
    }

    // extract the expiration date from a token
    public Date extractExpiration(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    // extract a specific claim from a token
    public String extractClaim(String token, String claimName) {
        return JWT.decode(token).getClaim(claimName).asString();
    }

    // check if a token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // validate a token with a user object
    public Boolean validateToken(String token, User user) {
        final String username = extractUsername(token);
        try {
            // verify the token with the secret key and the algorithm
            JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
            // check if the username matches and the token is not expired
            return (username.equals(user.getUsername()) && !isTokenExpired(token));
        } catch (JWTVerificationException e) {
            // handle verification exception
            return false;
        }
    }
}