package com.mouday.blogapi;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

public class JwtDemo {
    //过期时间  120秒后
    private long expire = 120;

    // 秘钥
    private String secret = "123456";

    @Test
    public void createJwt() {

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire * 1000);

        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withClaim("id", 12) // 增加Payload数据
                .withExpiresAt(expireDate)  // 过期时间
                .sign(algorithm);

        System.out.println(token);
    }

    @Test
    public void decodeJwt() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsImV4cCI6MTYwNzg3MjM5NH0.J5ih6dffc7z3D2ckYDCe61JuhsQJLXjQuDIUGMip94s";

        DecodedJWT jwt = JWT.decode(token);
        System.out.println(jwt.getToken());
        System.out.println(jwt.getHeader());
        System.out.println(jwt.getPayload());
        System.out.println(jwt.getSignature());

        // 获取Payload中的数据
        Map<String, Claim> claims = jwt.getClaims();    //Key is the Claim name
        Integer id = claims.get("id").asInt();
        System.out.println(id);
    }


    @Test
    public void verifyJwt() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTIsImV4cCI6MTYwNzg3MjM5NH0.J5ih6dffc7z3D2ckYDCe61JuhsQJLXjQuDIUGMip94s";

        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance

        try {
            verifier.verify(token);
            System.out.println("验证成功");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
