package com.lightr.lblog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final long EX_TIME = 1000 * 60 * 60 * 24; // 一天

    //创建token的
    public static String getJwtToken(int id, String secret) {
        Date date = new Date(System.currentTimeMillis() + EX_TIME);
        return JWT.create()
                .withClaim("user_id", id)
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(secret));
    }

    //验证token
    public static boolean VerifyToken(String token, String secret) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    //获取username
    public static int getUserId(String token) {
        try {
            return JWT.decode(token).getClaim("user_id").asInt();
        } catch (Exception exception) {
            return -1;
        }
    }

    public static boolean isNameExist(String token) {
        int id = getUserId(token);
        return id != -1;
    }

    //判断token是否到期
    public static boolean isExpires(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt().getTime() < System.currentTimeMillis();
        } catch (Exception e) {
            return true;
        }

    }

}
