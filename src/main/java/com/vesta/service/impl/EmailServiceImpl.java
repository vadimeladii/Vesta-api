package com.vesta.service.impl;

import com.vesta.service.EmailService;
import com.vesta.service.TokenService;
import com.vesta.service.dto.MailDto;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${vesta.base.url}")
    private String baseUrl;

    @Value("${vesta.email.from}")
    private String emailFrom;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TokenService tokenService;

    private void sendEmail(MailDto mail) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getText(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(message);
    }

    @Override
    public void sendEmailForgotPassword(String username, String email) {
        MailDto mail = resetPasswordEmailDto(email);

        Map<String, Object> model = new HashMap<>();

        model.put("base_url", baseUrl);
        model.put("token", tokenService.generatedEmailToken(username).getToken());
        model.put("title", mail.getSubject());
        model.put("body", mail.getText());
        mail.setModel(model);

        sendEmail(mail);
    }

    private MailDto resetPasswordEmailDto(String email) {
        MailDto mail = new MailDto();

        VelocityEngine ve = new VelocityEngine();
        Template text = ve.getTemplate("/home/msarpe/Projects/Vesta-api/src/main/resources/templates/ForogtPasswordTemplate.vm");

        mail.setFrom(emailFrom);
        mail.setTo(email);
        mail.setSubject("Password reset request");
        mail.setText(String.valueOf(text));

        return mail;
    }
}
