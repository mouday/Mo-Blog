package com.mouday.blogapi.component;

import com.mouday.blogapi.pojo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;


/**
 * 发送邮件
 * https://pengshiyu.blog.csdn.net/article/details/109569290
 */
@Component
public class EmailComponent {

    @Value("${spring.mail.username}")
    private String fromUser;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 文本邮件
     */
    public void sendSimpleMail(Email email) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromUser);
        message.setTo(email.getToUser());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());

        mailSender.send(message);
    }

    /**
     * html邮件
     */
    public void sendHtmlMail(Email email) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromUser);
        helper.setTo(email.getToUser());
        helper.setSubject(email.getSubject());
        helper.setText(email.getContent(), true);

        mailSender.send(message);

    }

    /**
     * 发送模板邮件
     */
    public void sendTemplateMail(String toUser,
                                 String subject,
                                 String template,
                                 Map<String, Object> data)
            throws MessagingException {

        Context context = new Context();
        context.setVariables(data);
        String mailContent = templateEngine.process(template, context);

        Email email = new Email();
        email.setToUser(toUser);
        email.setSubject(subject);
        email.setContent(mailContent);

        this.sendHtmlMail(email);
    }
}
