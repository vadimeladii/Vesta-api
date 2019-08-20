package com.vesta.service.impl;

import com.vesta.service.EmailService;
import com.vesta.service.TokenService;
import com.vesta.service.dto.MailDto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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

    @Autowired
    private Configuration freemarkerConf;

    @Override
    public void sendEmailForgotPassword(String username, String email) {
        log.info("method --- sendEmailForgotPassword");

        MailDto mail = new MailDto();

        mail.setFrom(emailFrom);
        mail.setTo(email);
        mail.setSubject("Password reset request");
        mail.setText(getModelTemplateForResetPassword(username));

        sendEmail(mail);
    }

    private void sendEmail(MailDto mail) {
        log.info("method --- sendEmail");

        MimeMessageHelper helper;
        MimeMessage message = mailSender.createMimeMessage();

        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getText(), true);
        } catch (MessagingException e) {
            log.error("The email was not sent");
        }

        mailSender.send(message);
    }


    private String getModelTemplateForResetPassword(String username) {
        Map<String, String> model = new HashMap<>();
        model.put("base_url", baseUrl);
        model.put("token", tokenService.generatedEmailToken(username).getJwtToken());
        return buildText("ForgotPasswordTemplate.ftl", model);
    }

    private String buildText(String path, Map<String, String> params) {
        String text = "";

        try {

            freemarkerConf.setClassForTemplateLoading(this.getClass(), "/templates");
            Template t = freemarkerConf.getTemplate(path);
            text = FreeMarkerTemplateUtils.processTemplateIntoString(t, params);
        } catch (IOException | TemplateException e) {
            log.error("Could not process Template ", e);
        }

        return text;
    }
}
