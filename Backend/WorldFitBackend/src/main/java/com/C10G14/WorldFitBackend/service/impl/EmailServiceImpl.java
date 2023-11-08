package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final ResourceLoader resourceLoader;

    @Override
    public void sendHtmlEmail(User user, String subject) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject + " " + user.getName());
            helper.setTo(user.getEmail());
            Resource resource = resourceLoader.getResource("classpath:templates/emailTemplate.html");
            String html = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            html = html.replace("[[Name]]",user.getName());
            helper.setText(html, true);
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
