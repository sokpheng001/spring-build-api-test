package com.example.api.mbanking.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor

public class MailUtil {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Builder
    @Data
    @AllArgsConstructor
    public static class Meta<T>{
        private String to;
        private String from;
        private String subject;
        private String templateUrl;
        private T data;
    }
    public void sendMail(Meta<?> data) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("data",data.data);
        String html = templateEngine.process(data.templateUrl,context);
        helper.setText(html,true);
        helper.setTo(data.to);
        helper.setFrom(data.from);
        helper.setSubject(data.subject);
        javaMailSender.send(mimeMessage);
    }
}
