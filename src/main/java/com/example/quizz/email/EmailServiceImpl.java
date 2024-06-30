package com.example.quizz.email;

import com.example.quizz.exception.AppException;
import com.example.quizz.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String gmail;

    @Override
    public String sendMail(String to,String subject, String text) {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(gmail);
            mimeMessageHelper.setTo(to);

            javaMailSender.send(mailMessage);
            return "Sent mail SuccessFully !";
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new AppException(ErrorCode.SEND_MAIL_FAILED);
        }
    }
}
