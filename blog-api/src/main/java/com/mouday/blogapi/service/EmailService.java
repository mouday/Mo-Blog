package com.mouday.blogapi.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouday.blogapi.component.EmailComponent;
import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.mapper.EmailMapper;
import com.mouday.blogapi.pojo.Email;
import com.mouday.blogapi.pojo.User;
import com.mouday.blogapi.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService {

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    EmailComponent emailComponent;


    /**
     * 发送邮件，发送前先保存到历史，发送成功后修改邮件状态
     * @param email
     */
    public void sendMail(Email email){
        email.setSend(false);
        emailMapper.insert(email);

        try {
            emailComponent.sendHtmlMail(email);
        } catch (MessagingException e) {
            throw new BaseException(ResultCode.EMAIL_SEND_ERROR);
        }

        this.updateSendStatusById(email.getId(), true);
    }

    public Email saveEmail(Email email){

        if (email.getId() == null) {
            emailMapper.insert(email);
        } else {
            emailMapper.updateById(email);
        }
        return email;

    }


    public void updateSendStatusById(Integer id, boolean status){
        emailMapper.updateSendStatusById(id, status);
    }

    public IPage<Email> getEmailList(Integer page, Integer size) {
        Page<Email> pager = new Page<>(page, size, true);
        IPage<Email> iPage = emailMapper.selectPage(pager, null);
        return iPage;
    }
}

