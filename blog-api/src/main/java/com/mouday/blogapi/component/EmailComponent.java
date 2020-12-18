package com.mouday.blogapi.component;

import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * 发送邮件
 * https://pengshiyu.blog.csdn.net/article/details/109569290
 */
@Component
public class Email {

    @Value("${spring.mail.username}")
    private String fromUser;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;


    private

    /**
     * 文本邮件
     *
     * @param toUser
     * @param subject
     * @param content
     */
    public void sendSimpleMail(
            String toUser, String subject,
            String content) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromUser);
        message.setTo(toUser);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }

    /**
     * html邮件
     *
     */
    public void sendHtmlMail(com.mouday.blogapi.pojo.Email email) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromUser);
            helper.setTo(email.getToUser());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);
        } catch (MessagingException e) {
            throw new BaseException(ResultCode.EMAIL_SEND_ERROR);
        }

        mailSender.send(message);

    }

    /**
     * 附件邮件
     *
     * @param toUser
     * @param subject
     * @param content
     * @param filePath 绝对路径
     * @throws MessagingException
     */
    public void sendAttachmentsMail(
            String toUser, String subject,
            String content, String filePath)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromUser);
        helper.setTo(toUser);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource file = new FileSystemResource(new File(filePath));

        // 可以多次添加附件
        helper.addAttachment(file.getFilename(), file);

        mailSender.send(message);
    }

    /**
     * 图片邮件
     *
     * @param toUser
     * @param subject
     * @param content
     * @param resourcePath
     * @param resourceId
     * @throws MessagingException
     */
    public void sendInlineResourceMail(
            String toUser, String subject,
            String content, String resourcePath, String resourceId)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromUser);
        helper.setTo(toUser);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource resource = new FileSystemResource(new File(resourcePath));

        // 可以多次添加图片
        helper.addInline(resourceId, resource);

        mailSender.send(message);
    }

    // /**
    //  * 发送模板邮件
    //  *
    //  * @param toUser
    //  * @param subject
    //  * @param data
    //  * @throws MessagingException
    //  */
    // public void sendTemplateMail(
    //         String toUser, String subject,
    //         String template, final Map<String, Object> data) throws MessagingException {
    //
    //     Context context = new Context();
    //     context.setVariables(data);
    //     String mailContent = templateEngine.process(template, context);
    //
    //     this.sendHtmlMail(toUser, subject, mailContent);
    // }
}
