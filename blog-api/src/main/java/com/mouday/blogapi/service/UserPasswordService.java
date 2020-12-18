package com.mouday.blogapi.service;

import com.mouday.blogapi.component.JsonWebToken;
import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.mapper.UserMapper;
import com.mouday.blogapi.mapper.UserPasswordMapper;
import com.mouday.blogapi.pojo.User;
import com.mouday.blogapi.pojo.UserPassword;
import com.mouday.blogapi.result.ResultCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserPasswordMapper userPasswordMapper;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    JsonWebToken jsonWebToken;

    /**
     * 修改密码
     * @param userPassword
     * @return
     */
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

        return jsonWebToken.createToken(user.getId(), user.getName());
    }

}
