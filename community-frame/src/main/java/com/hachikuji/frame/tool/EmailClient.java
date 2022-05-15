package com.hachikuji.frame.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailClient {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(String to, String subject, String content) throws MessagingException {
        //创建简单邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true);
        //谁发的
        mimeHelper.setFrom(from);
        //谁要接收
        mimeHelper.setTo(to);
        //邮件标题
        mimeHelper.setSubject(subject);
        //邮件内容
        mimeHelper.setText(content,true);
        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

}
