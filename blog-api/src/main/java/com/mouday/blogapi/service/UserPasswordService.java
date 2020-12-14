package com.mouday.blogapi.service;

import com.mouday.blogapi.conf.JwtConfig;
import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.mapper.UserMapper;
import com.mouday.blogapi.mapper.UserPasswordMapper;
import com.mouday.blogapi.pojo.BaseEntity;
import com.mouday.blogapi.pojo.User;
import com.mouday.blogapi.pojo.UserPassword;
import com.mouday.blogapi.result.ResultCode;
import com.mouday.blogapi.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserPasswordService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserPasswordMapper userPasswordMapper;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Resource
    JwtConfig jwtConfig;

    public int savePasswordById(UserPassword userPassword) {
        userPassword.setPassword(encoder.encode(userPassword.getPassword()));

        // 数据校验
        if(userPassword.getId() == null){
            throw new BaseException(ResultCode.PARAM_NOT_COMPLETE);
        }

        // 用户表要存在该用户
        if(userMapper.selectById(userPassword.getId()) == null){
            throw new BaseException(ResultCode.RECORD_NOT_EXIST);
        }

        // 如果没有密码就添加
        if(userPasswordMapper.selectById(userPassword.getId()) == null){
            return userPasswordMapper.insert(userPassword);
        } else{
            return userPasswordMapper.updateById(userPassword);
        }
    }

    /**
     * 登录逻辑
     * @param userPassword
     * @return
     */
    public String login(UserPassword userPassword) {
        // 检查用户是否存在
        User user = userMapper.selectUserByUsername(userPassword.getUsername());
        if(user == null){
            throw new BaseException(ResultCode.USER_NOT_EXIST);
        }

        // 密码校验
        UserPassword userPasswordData = userPasswordMapper.selectById(user.getId());

        if(!encoder.matches(userPassword.getPassword(), userPasswordData.getPassword())){
            throw new BaseException(ResultCode.USER_LOGIN_ERROR);
        }

        return JwtUtil.createToken(user.getId(), user.getName());
    }


    public String getToken(User user) {
        //过期时间
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtConfig.getExpire() * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(user.getName())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();

    }


}
