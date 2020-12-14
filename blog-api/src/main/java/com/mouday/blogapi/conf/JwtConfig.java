package com.mouday.blogapi.conf;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT的token，区分大小写
 */
@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtConfig {

    private String secret;

    private long expire;

    private String token;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}