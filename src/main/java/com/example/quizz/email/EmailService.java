package com.example.quizz.email;

public interface EmailService {
    String sendMail(String to,String subject, String text);
}
