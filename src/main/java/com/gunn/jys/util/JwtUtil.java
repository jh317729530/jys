package com.gunn.jys.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    //过期时间
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    //由uuid随机生成的secret
    private static final String SECRET = "e25395f4646a406f9b1754d394c8450b";

    /**
     *  校验token是否正确
     * @param token
     * @param username
     * @param secret
     * @return
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("username", username).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取token中的用户名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成签名
     * @param username
     * @param secret
     * @return
     */
    public static String sign(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}
