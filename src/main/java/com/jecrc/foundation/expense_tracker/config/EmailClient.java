package com.jecrc.foundation.expense_tracker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class EmailClient {

  private JavaMailSenderImpl mailSender;

  public void sendMail(String from, String to, String message, String subject) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setSubject(subject);
    mailMessage.setFrom(from);
    mailMessage.setTo(to);
    mailMessage.setText(message);
    mailSender.send(mailMessage);
  }

}
