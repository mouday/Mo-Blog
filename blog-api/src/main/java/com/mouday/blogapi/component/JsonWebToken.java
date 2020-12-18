package com.mouday.blogapi.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JsonWebToken {
    // 配置秘钥
    @Value("${json-web-token.secret}")
    private String secret;

    // 有效时间 单位：秒
    @Value("${json-web-token.expire}")
    private int expire;

    public  String createToken(Integer id, String username) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + this.expire * 1000);

        Algorithm algorithm = Algorithm.HMAC256(this.secret);
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
    public boolean verifyToken(String token) {

        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        //Reusable verifier instance
        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            verifier.verify(token);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public Integer getId(String token){
        DecodedJWT jwt = JWT.decode(token);

        // 获取Payload中的数据
        return jwt.getClaim("id").asInt();
    }
}
