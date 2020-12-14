package com.mouday.blogapi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


public class JwtUtil {
    public static final String secret = "123456";

    public static final int expire = 3600;

    public static String createToken(Integer id, String username) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire * 1000);

        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create().withExpiresAt(expireDate);  // 过期时间

        builder.withClaim("id", id);
        builder.withClaim("username", username);

        return builder.sign(algorithm);
    }


    /**
     * 验证 token
     * @param token
     * @return
     */
    public static boolean verifyToken(String token) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance

        try {
            verifier.verify(token);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static Integer getId(String token){
        DecodedJWT jwt = JWT.decode(token);

        // 获取Payload中的数据
        return jwt.getClaim("id").asInt();
    }

    public static String  getUsername(String token){
        DecodedJWT jwt = JWT.decode(token);

        // 获取Payload中的数据
        return jwt.getClaim("username").asString();
    }
}
